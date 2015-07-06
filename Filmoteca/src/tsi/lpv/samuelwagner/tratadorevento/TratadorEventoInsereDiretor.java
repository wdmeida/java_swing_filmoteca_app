package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

/** Classe <code>TratadorEventoInsereDiretor</code> responsavel por tratar os eventos 
 *  da classe <code>IgCadastrarFilme</code>.
 * @author Samuel
 * @author Wanger
 */
public class TratadorEventoInsereDiretor implements ActionListener {
	private IgCadastrarFilme igCadastraFilme;
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoInsereAtor</code>.
	 * @param igCadastrarFilme referencia da Classe <code>IgCadastrarFilme</code>.
	 */
	public TratadorEventoInsereDiretor(IgCadastrarFilme igCadastrarFilme) {
		this.igCadastraFilme = igCadastrarFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//Verifica qual botao foi clicado pelo usuario.
		if(igCadastraFilme.getInserirDiretorButton() == event.getSource() || igCadastraFilme.getDiretorField() == event.getSource())
			insereDiretor();
		else
			if(igCadastraFilme.getLimparDiretorButton() == event.getSource())
				limparDiretor();
	}
	
	//Insere Diretor informado pelo usuario quaso não esteja adicionado.
	private void insereDiretor() {
		/*Verifica se o campo diretorField está vazia caso esteja não ocorre nada, caso não 
		 * adiciona o diretor caso ele não esteja na lista.
		 */
		if(!Validador.validaCampoVazio(igCadastraFilme.getDiretorField().getText())){
			//se o diretorArea está vazio quaso não esteja verifica se o diretor se encotra lá.
			if(!igCadastraFilme.getDiretorArea().getText().equals(""))
				//Verifica se o diretor se encontra caso encontra informa ao usuario que ele está la.
				if(Validador.procuraIgualdede(igCadastraFilme.getDiretorField().getText(), FuncaoAuxiliar.obtemPalavras(
						igCadastraFilme.getDiretorArea().getText()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastraFilme, "Diretor já Informado.", "Cadastra Filme.");
					igCadastraFilme.getDiretorField().setText("");
					return;
				}
				//Adiciona o diretor ao diretorArea.
				String texto = igCadastraFilme.getDiretorArea().getText();
				texto += igCadastraFilme.getDiretorField().getText() + "\n";
				igCadastraFilme.getDiretorArea().setText(texto);
				igCadastraFilme.getDiretorField().setText("");
		}
	}
	
	//Limpa o ultimo diretor cadastrado.
	private void limparDiretor() {
		//Verifica se diretorArea esta vazio caso esteja apaga o ultimo diretor.
		if(!igCadastraFilme.getDiretorArea().getText().equals("")){
			String[] texto = FuncaoAuxiliar.obtemPalavras(igCadastraFilme.getDiretorArea().getText());
			igCadastraFilme.getDiretorArea().setText(FuncaoAuxiliar.juntaPalavra(texto, texto.length-1));
		}
	}
}
