package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

public class TratadorEventoInsereAutor implements ActionListener{
	private IgCadastrarFilme igCadastraFilme;
	
	public TratadorEventoInsereAutor(IgCadastrarFilme igCadastraFilme) {
		this.igCadastraFilme = igCadastraFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(igCadastraFilme.getInserirAutorButton() == event.getSource() || igCadastraFilme.getAutorField() == event.getSource())
			insereAutor();
		else
			if(igCadastraFilme.getLimparAutorButton() == event.getSource())
				limparautor();
	}
	
	private void insereAutor() {
		if(!Validador.validaCampoVazio(igCadastraFilme.getAutorField().getText())){
			if(!igCadastraFilme.getAutorArea().getText().equals(""))
				if(Validador.procuraIgualdede(igCadastraFilme.getAutorField().getText(), FuncaoAuxiliar.obtemNomes(
						igCadastraFilme.getAutorArea().getText()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastraFilme, "Autor já Informado.", "Cadastra Filme.");
					igCadastraFilme.getAutorField().setText("");
					return;
				}
				String texto = igCadastraFilme.getAutorArea().getText();
				texto += igCadastraFilme.getAutorField().getText() + "\n";
				igCadastraFilme.getAutorArea().setText(texto);
				igCadastraFilme.getAutorField().setText("");
		}
	}
	
	private void limparautor() {
		if(!igCadastraFilme.getAutorArea().getText().equals("")){
			String[] texto = FuncaoAuxiliar.obtemNomes(igCadastraFilme.getAutorArea().getText());
			igCadastraFilme.getAutorArea().setText(FuncaoAuxiliar.juntaTexto(texto, texto.length-1));
		}
	}
}
