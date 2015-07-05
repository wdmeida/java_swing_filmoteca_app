package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.persistencia.PaisDAO;

public class TratadorEventoCadastraFilme implements ActionListener {
	private IgCadastrarFilme igCadastrarFilme;
	
	public TratadorEventoCadastraFilme(IgCadastrarFilme igCadastrarFilme) {
		this.igCadastrarFilme = igCadastrarFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(igCadastrarFilme.getCadastrarButton() == event.getSource()){
			cadastraFilme();
		}else
			if(igCadastrarFilme.getCancelarButton() == event.getSource())
				igCadastrarFilme.dispose();
	}
	
	public void cadastraFilme(){
		if(validaNomeFilme() && validaNomePais() && validaSinopse() && validaNomesAtor() && validaNomesAutor()
				&& validaNomesDiretor())
			;
		else
			FuncaoAuxiliar.exibirMensagemErro(igCadastrarFilme, "Erro", "Cadastra Filme");
	}
	
	private boolean validaNomeFilme(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getTituloField().getText()))
			if(FilmeDAO.pesquisarFilme(igCadastrarFilme.getTituloField().getText()) == null)
				return true;
			else return false;
		else return false;
	}
	
	private boolean validaNomePais(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getPaisField().getText()))
			if(PaisDAO.pesquisaPais(igCadastrarFilme.getPaisField().getText()) == null)
				return true;
			else return false;
		else return false;
	}
	
	private boolean validaSinopse(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getSinopseEditorPane().getText()))
			return true;
		else return false;
	}
	
	private boolean validaNomesDiretor(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getDiretorArea().getText()))
			return true;
		else return false;
	}
	
	private boolean validaNomesAutor(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getAutorArea().getText()))
			return true;
		else return false;
	}
	
	private boolean validaNomesAtor(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getAtorArea().getText()))
			return true;
		else return false;
	}
}
