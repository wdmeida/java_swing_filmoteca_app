package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

public class TratadorEventoInsereDiretor implements ActionListener {
	private IgCadastrarFilme igCadastraFilme;
	
	public TratadorEventoInsereDiretor(IgCadastrarFilme igCadastraFilme) {
		this.igCadastraFilme = igCadastraFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(igCadastraFilme.getInserirDiretorButton() == event.getSource() || igCadastraFilme.getDiretorField() == event.getSource())
			insereDiretor();
		else
			if(igCadastraFilme.getLimparDiretorButton() == event.getSource())
				limparDiretor();
	}
	
	private void insereDiretor() {
		if(!Validador.validaCampoVazio(igCadastraFilme.getDiretorField().getText())){
			if(!igCadastraFilme.getDiretorArea().getText().equals(""))
				if(Validador.procuraIgualdede(igCadastraFilme.getDiretorField().getText(), FuncaoAuxiliar.obtemNomes(
						igCadastraFilme.getDiretorArea().getText()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastraFilme, "Diretor já Informado.", "Cadastra Filme.");
					igCadastraFilme.getDiretorField().setText("");
					return;
				}
				String texto = igCadastraFilme.getDiretorArea().getText();
				texto += igCadastraFilme.getDiretorField().getText() + "\n";
				igCadastraFilme.getDiretorArea().setText(texto);
				igCadastraFilme.getDiretorField().setText("");
		}
	}
	
	private void limparDiretor() {
		if(!igCadastraFilme.getDiretorArea().getText().equals("")){
			String[] texto = FuncaoAuxiliar.obtemNomes(igCadastraFilme.getDiretorArea().getText());
			igCadastraFilme.getDiretorArea().setText(FuncaoAuxiliar.juntaTexto(texto, texto.length-1));
		}
	}
}
