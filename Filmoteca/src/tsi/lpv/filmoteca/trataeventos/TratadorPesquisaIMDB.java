package tsi.lpv.filmoteca.trataeventos;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;

import tsi.lpv.filmoteca.funcaoauxiliar.ObterDadosIMDB;
import tsi.lpv.filmoteca.gui.IgCadastrarFilme;
import tsi.lpv.filmoteca.modelo.DadosIMDB;

public class TratadorPesquisaIMDB {
	private IgCadastrarFilme cadastrarFilme;
	
	public TratadorPesquisaIMDB(IgCadastrarFilme cadastrarFilme) {
		this.cadastrarFilme = cadastrarFilme;
	}
	
	public void preencherDados(){
		//Obtém o nome do filme e realiza a busca.
		String nomeFilme = cadastrarFilme.getTituloField().getText();
		
		System.out.println(nomeFilme);
		try {
			//Realiza a busca e verifica se os dados foram encontrados.
			DadosIMDB dadosIMDB = ObterDadosIMDB.pesquisarDados(nomeFilme);
			if(dadosIMDB == null) return;
			
			//Seta os atributos encontrados nos campos.
			cadastrarFilme.getTituloField().setText(dadosIMDB.getTitle());
			cadastrarFilme.getDuracaoField().setText(dadosIMDB.getRuntime());
			//cadastrarFilme.getClassificacaoIMDBspinner().setValue(Integer.parseInt(dadosIMDB.getImdbRating()));
			//cadastrarFilme.getjYearChooser().setYear(Integer.parseInt(dadosIMDB.getYear()));
			cadastrarFilme.getSinopseEditorPane().setText(dadosIMDB.getPlot());
			cadastrarFilme.getPaisField().setText(dadosIMDB.getCountry());
			insereAtores(dadosIMDB.getActors());
			insereDiretor(dadosIMDB.getDirector());
			insereAutor(dadosIMDB.getWriter());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void insereDiretor(String diretores){
		//Obtem o listModel do JList
		DefaultListModel<String> listModel = (DefaultListModel<String>)cadastrarFilme.getDiretorArea().getModel();
		//Apaga o dados do listModel
		listModel.clear();
		//Separa os diretores.
		StringTokenizer diretorTok = new StringTokenizer(diretores,",");
		//Adiciona diretor por diretor no jlist removendo o espaços do inicio e fim do nome.
		while(diretorTok.hasMoreTokens())
			listModel.addElement(diretorTok.nextToken().trim());
	}
	
	private void insereAutor(String autores){
		//Obtem o listModel do JList
		DefaultListModel<String> listModel = (DefaultListModel<String>)cadastrarFilme.getAutorArea().getModel();
		//Apaga o dados do listModel
		listModel.clear();
		//Separa os autores.
		StringTokenizer autorTok = new StringTokenizer(autores,",");
		//Adiciona autor por autor no jlist removendo o espaços do inicio e fim do nome.
		while(autorTok.hasMoreTokens())
			listModel.addElement(autorTok.nextToken().trim());
	}
	
	private void insereAtores(String atores){
		//Obtem o listModel do JList
		DefaultListModel<String> listModel = (DefaultListModel<String>)cadastrarFilme.getAtorArea().getModel();
		//Apaga o dados do listModel
		listModel.clear();
		//Separa os atores.
		StringTokenizer atoresTok = new StringTokenizer(atores,",");
		//Adiciona ator por ator no jlist removendo o espaços do inicio e fim do nome.
		while(atoresTok.hasMoreTokens())
			listModel.addElement(atoresTok.nextToken().trim());
	}
}
