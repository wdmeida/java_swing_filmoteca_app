package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

public class TratadorEventoInsereAtor implements ActionListener {
private IgCadastrarFilme igCadastraFilme;
	
	public TratadorEventoInsereAtor(IgCadastrarFilme igCadastraFilme) {
		this.igCadastraFilme = igCadastraFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(igCadastraFilme.getInserirAtorButton() == event.getSource() || igCadastraFilme.getAtorField() == event.getSource())
			insereAutor();
		else
			if(igCadastraFilme.getLimparAtorButton() == event.getSource())
				limparautor();
	}
	
	private void insereAutor() {
		if(!Validador.validaCampoVazio(igCadastraFilme.getAtorField().getText())){
			if(!igCadastraFilme.getAtorArea().getText().equals(""))
				if(Validador.procuraIgualdede(igCadastraFilme.getAtorField().getText(), FuncaoAuxiliar.obtemNomes(
						igCadastraFilme.getAtorArea().getText()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastraFilme, "Ator já Informado.", "Cadastra Filme.");
					igCadastraFilme.getAtorField().setText("");
					return;
				}
				String texto = igCadastraFilme.getAtorArea().getText();
				texto += igCadastraFilme.getAtorField().getText() + "\n";
				igCadastraFilme.getAtorArea().setText(texto);
				igCadastraFilme.getAtorField().setText("");
		}
	}
	
	private void limparautor() {
		if(!igCadastraFilme.getAtorArea().getText().equals("")){
			String[] texto = FuncaoAuxiliar.obtemNomes(igCadastraFilme.getAtorArea().getText());
			igCadastraFilme.getAtorArea().setText(FuncaoAuxiliar.juntaTexto(texto, texto.length-1));
		}
	}

}
