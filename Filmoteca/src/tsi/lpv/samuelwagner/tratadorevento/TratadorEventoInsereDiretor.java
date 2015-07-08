package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

/** Classe <code>TratadorEventoInsereDiretor</code> responsável por tratar os eventos 
 *  da classe <code>IgCadastrarFilme</code>.
 * @author Samuel Gonçalves
 * @author Wanger Almeida
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
			if(igCadastraFilme.getDiretorArea().getModel().getSize() >= 1)
				//Verifica se o diretor se encontra caso encontra informa ao usuario que ele está la.
				if(Validador.procuraIgualdede(igCadastraFilme.getDiretorField().getText(), FuncaoAuxiliar.obtemPalavras(
						igCadastraFilme.getDiretorArea()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastraFilme, "Diretor já Informado.", "Cadastra Filme.");
					igCadastraFilme.getDiretorField().setText("");
					return;
				}
			try{
				DefaultListModel<String> listModel = (DefaultListModel<String>)igCadastraFilme.getDiretorArea().getModel();
				listModel.addElement(igCadastraFilme.getDiretorField().getText());
				igCadastraFilme.getDiretorField().setText("");
			}catch(ClassCastException e){
				e.printStackTrace();
			}
		}
	}
	
	//Limpa o ultimo Diretor cadastrado.
		private void limparDiretor() {
			if(igCadastraFilme.getDiretorArea().getModel().getSize() >= 1){
				try{
					DefaultListModel<String> listModel = (DefaultListModel<String>)igCadastraFilme.getDiretorArea().getModel();
					listModel.remove(listModel.getSize() - 1);
				}catch(ClassCastException e){
					e.printStackTrace();
				}
			}
		}
}
