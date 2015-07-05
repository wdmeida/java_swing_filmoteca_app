package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import tsi.lpv.samuelwagner.controller.CadastroControle;
import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.persistencia.PaisDAO;
import tsi.lpv.samuelwagner.tipo.Artista;
import tsi.lpv.samuelwagner.tipo.Autor;
import tsi.lpv.samuelwagner.tipo.Diretor;
import tsi.lpv.samuelwagner.tipo.Filme;
import tsi.lpv.samuelwagner.tipo.Genero;
import tsi.lpv.samuelwagner.tipo.Pais;

public class TratadorEventoCadastraFilme implements ActionListener {
	private IgCadastrarFilme igCadastrarFilme;
	
	public TratadorEventoCadastraFilme(IgCadastrarFilme igCadastrarFilme) {
		this.igCadastrarFilme = igCadastrarFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(igCadastrarFilme.getCadastrarButton() == event.getSource()){
			validaCadastro();
		}else
			if(igCadastrarFilme.getCancelarButton() == event.getSource())
				igCadastrarFilme.dispose();
	}
	
	private void validaCadastro(){
		if(validaNomeFilme() && validaNomePais() && validaSinopse() && validaNomesAtor() && validaNomesAutor()
				&& validaNomesDiretor() && validaDuracao())
			cadastraFilme();
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
	
	private boolean validaDuracao(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getPaisField().getText()))
			if(Validador.validaNumeroInteiro(igCadastrarFilme.getDuracaoField().getText()))
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
	
	private void cadastraFilme() {
		File poster = new File(igCadastrarFilme.getFotoField().getText());
		Filme filme = new Filme(0,Integer.parseInt(igCadastrarFilme.getDuracaoField().getText()),
				igCadastrarFilme.getjYearChooser().getValue(),
				(int)igCadastrarFilme.getClassificacaoIMDBspinner().getValue(), 
				(int)igCadastrarFilme.getCllassificacaoSpinner().getValue(), 
				igCadastrarFilme.getTituloField().getText(), 
				igCadastrarFilme.getSinopseEditorPane().getText(),
				igCadastrarFilme.getFaixaEtariaComboBox().getItemAt(igCadastrarFilme.getFaixaEtariaComboBox().getSelectedIndex()),
				tipoMidia(),
				igCadastrarFilme.getChooser().getCalendar(),
				poster);
		Artista artistas[] = obtemArtistas();
		Diretor diretores[] = obtemDiretores();
		Autor autores[] = obtemAutores();
		Genero genero = GeneroDAO.pesquisaGenero(igCadastrarFilme.getGeneroComboBox().getItemAt((
				igCadastrarFilme.getGeneroComboBox().getSelectedIndex())));
		if(genero == null)
			genero = new Genero(0, igCadastrarFilme.getGeneroComboBox().getItemAt((
				igCadastrarFilme.getGeneroComboBox().getSelectedIndex())));
		Pais pais = PaisDAO.pesquisaPais(igCadastrarFilme.getPaisField().getText());
		if(pais == null)
			pais = new Pais(0, igCadastrarFilme.getPaisField().getText());
		
		CadastroControle.cadastro(filme, artistas, diretores, autores, genero, pais);
	}
	
	private Artista[] obtemArtistas(){
		String nomes[] = FuncaoAuxiliar.obtemNomes(igCadastrarFilme.getAtorArea().getText());
		LinkedList<Artista> artistas = new LinkedList<>();
		
		for(String nome : nomes)
			artistas.add(new Artista(0, nome));
		
		return artistas.toArray(new Artista[0]);
	}
	
	private Autor[] obtemAutores(){
		String nomes[] = FuncaoAuxiliar.obtemNomes(igCadastrarFilme.getAutorArea().getText());
		LinkedList<Autor> autores = new LinkedList<>();
		
		for(String nome : nomes)
			autores.add(new Autor(0, nome));
		
		return autores.toArray(new Autor[0]);
	}
	
	private Diretor[] obtemDiretores(){
		String nomes[] = FuncaoAuxiliar.obtemNomes(igCadastrarFilme.getDiretorArea().getText());
		LinkedList<Diretor> diretores = new LinkedList<>();
		
		for(String nome : nomes)
			diretores.add(new Diretor(0, nome));
		
		return diretores.toArray(new Diretor[0]);
	}
	
	private String tipoMidia(){
		if(igCadastrarFilme.getDvdRadio().isSelected())
			return "DVD";
		else
			if(igCadastrarFilme.getBluRayRadio().isSelected())
				return "Blu-Ray";
			else
				return "Arquivo Digital";
	}
}
