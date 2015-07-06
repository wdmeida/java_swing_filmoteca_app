package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

/** Classe <code>TratadorEventoInsereAutor</code> responsavel por tratar os eventos 
 *  da classe <code>IgCadastrarFilme</code>.
 * @author Samuel
 * @author Wagner
 */
public class TratadorEventoInsereAutor implements ActionListener{
	private IgCadastrarFilme igCadastraFilme;
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoInsereAtor</code>.
	 * @param igCadastrarFilme referencia da Classe <code>IgCadastrarFilme</code>.
	 */
	public TratadorEventoInsereAutor(IgCadastrarFilme igCadastrarFilme) {
		this.igCadastraFilme = igCadastrarFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//Verifica qual botao foi clicado pelo usuario.
		if(igCadastraFilme.getInserirAutorButton() == event.getSource() || igCadastraFilme.getAutorField() == event.getSource())
			insereAutor();
		else
			if(igCadastraFilme.getLimparAutorButton() == event.getSource())
				limparAutor();
	}
	
	private void insereAutor() {
		/*Verifica se o campo autorField está vazia caso esteja não ocorre nada, caso não 
		 * adiciona o autor caso ele não esteja na lista.
		 */
		if(!Validador.validaCampoVazio(igCadastraFilme.getAutorField().getText())){
			//se o autorArea está vazio quaso não esteja verifica se o autor se encotra lá.
			if(!igCadastraFilme.getAutorArea().getText().equals(""))
				//Verifica se o autor se encontra caso encontra informa ao usuario que ele está la.
				if(Validador.procuraIgualdede(igCadastraFilme.getAutorField().getText(), FuncaoAuxiliar.obtemPalavras(
						igCadastraFilme.getAutorArea().getText()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastraFilme, "Autor já Informado.", "Cadastra Filme.");
					igCadastraFilme.getAutorField().setText("");
					return;
				}
			//Adiciona o autor ao diretorArea.
			String texto = igCadastraFilme.getAutorArea().getText();
			texto += igCadastraFilme.getAutorField().getText() + "\n";
			igCadastraFilme.getAutorArea().setText(texto);
			igCadastraFilme.getAutorField().setText("");
		}
	}
	
	//Limpa o ultimo Autor cadastrado.
	private void limparAutor() {
		if(!igCadastraFilme.getAutorArea().getText().equals("")){
			String[] texto = FuncaoAuxiliar.obtemPalavras(igCadastraFilme.getAutorArea().getText());
			igCadastraFilme.getAutorArea().setText(FuncaoAuxiliar.juntaPalavra(texto, texto.length-1));
		}
	}
}
