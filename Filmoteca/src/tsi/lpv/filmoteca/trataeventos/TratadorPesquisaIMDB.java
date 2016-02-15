package tsi.lpv.filmoteca.trataeventos;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import tsi.lpv.filmoteca.funcaoauxiliar.ObtemPoster;
import tsi.lpv.filmoteca.funcaoauxiliar.ObterDadosIMDB;
import tsi.lpv.filmoteca.gui.IgCadastrarFilme;
import tsi.lpv.filmoteca.modelo.DadosIMDB;

/**
 * A classe <code>TratadorPesquisaIMDB</code> é responsável por preencher os informações do filme se estas forem
 * obtidas através da API do IMDB.
 * 
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 * */
public class TratadorPesquisaIMDB {
	private IgCadastrarFilme cadastrarFilme;
	
	/**
	 * Construtor da classe <code>TratadorPesquisaIMDB</code>.
	 * @param cadastrarFilme <code>IgCadastrarFilme</code> com a refêrencia da janela onde serão
	 * preenchidas as informações encontradas.
	 * */
	public TratadorPesquisaIMDB(IgCadastrarFilme cadastrarFilme) {
		this.cadastrarFilme = cadastrarFilme;
	}
	
	/**
	 * Preenche as informações do filme caso sejam encontradas pela pesquisa na API do IMDB.
	 * */
	public void preencherDados(){
		//Obtém o nome do filme e realiza a busca.
		String nomeFilme = cadastrarFilme.getTituloField().getText();
		
		//Verifica se algum filme foi digitado para pesquisa.
		if(nomeFilme == null || nomeFilme.equals("")) return;
		
		try {
			//Realiza a busca e verifica se os dados foram encontrados.
			DadosIMDB dadosIMDB = ObterDadosIMDB.pesquisarDados(nomeFilme);
			if(dadosIMDB == null || dadosIMDB.getTitle() == null) return;
			Double rating;
			try{
				rating = Double.parseDouble(dadosIMDB.getImdbRating());
			}catch(NumberFormatException e){
				rating = 0.0;
			}
			//Seta os atributos encontrados nos campos.
			cadastrarFilme.getTituloField().setText(dadosIMDB.getTitle());
			cadastrarFilme.getDuracaoField().setText(dadosIMDB.getRuntime().replace("min", "").trim());
			cadastrarFilme.getClassificacaoIMDBspinner().setValue(rating.intValue());
			cadastrarFilme.getjYearChooser().setYear(Integer.parseInt(dadosIMDB.getYear()));
			cadastrarFilme.getSinopseEditorPane().setText(dadosIMDB.getPlot());
			cadastrarFilme.getPaisField().setText(dadosIMDB.getCountry());
			insereNomes(dadosIMDB.getActors(),(DefaultListModel<String>)cadastrarFilme.getAtorArea().getModel());
			insereNomes(dadosIMDB.getDirector(),(DefaultListModel<String>)cadastrarFilme.getDiretorArea().getModel());
			insereNomes(dadosIMDB.getWriter(),(DefaultListModel<String>)cadastrarFilme.getAutorArea().getModel());
			byte[] poster = ObtemPoster.obtemPoster(dadosIMDB.getPoster());
			//Verifica se o filme, serie, jogo, documentario, etc contem poster.
			if(poster != null){
				ImageIcon img = new ImageIcon(poster);
				img.setImage(img.getImage().getScaledInstance(208, 305,100));
				cadastrarFilme.getPosterLabel().setIcon(img);
				cadastrarFilme.setPoster(poster);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//preencherDados()
	
	private void insereNomes(String nomes, DefaultListModel<String> listModel){
		//Apaga o dados do listModel
		listModel.clear();
		//Separa os diretores.
		StringTokenizer diretorTok = new StringTokenizer(nomes,",");
		//Adiciona diretor por diretor no jlist removendo o espaços do inicio e fim do nome.
		while(diretorTok.hasMoreTokens())
			listModel.addElement(diretorTok.nextToken().trim());
	}//insereNomes()
}//class TratadorPesquisaIMDB
