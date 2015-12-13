package tsi.lpv.filmoteca.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tsi.lpv.filmoteca.modelo.Artista;
import tsi.lpv.filmoteca.modelo.Autor;
import tsi.lpv.filmoteca.modelo.Diretor;
import tsi.lpv.filmoteca.modelo.Filme;
import tsi.lpv.filmoteca.modelo.Genero;
import tsi.lpv.filmoteca.persistencia.ArtistaDAO;
import tsi.lpv.filmoteca.persistencia.AutorDAO;
import tsi.lpv.filmoteca.persistencia.AutorFilmeDAO;
import tsi.lpv.filmoteca.persistencia.DiretorDAO;
import tsi.lpv.filmoteca.persistencia.DiretorFilmeDAO;
import tsi.lpv.filmoteca.persistencia.ElencoDAO;
import tsi.lpv.filmoteca.persistencia.FilmeDAO;
import tsi.lpv.filmoteca.persistencia.GeneroDAO;
import tsi.lpv.filmoteca.persistencia.GeneroFilmeDAO;

/**
 * A classe <code>PesquisaControle</code> possui os métodos responsáveis por realizar as pesquisas nos
 * banco de dados.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 */
public class PesquisaControle {
	
	/**
	 * Obtém uma representaão de em <code>String</code> com todo o elenco do filme.
	 * @param codigoFilme <code>int</code> com o código filme que se deseja obter o elento.
	 * @return <code>String</code> com o elenco cadastrado.
	 */
	public static String obterElenco(int codigoFilme){
		//Obtém o elenco do filme
		List<Integer> elenco = ElencoDAO.pesquisElencoArtista(codigoFilme);
		
		if(elenco == null) return "";
		
		String atores = "";
		
		//Obtém os nomes do elenco.
		Iterator<Integer> itElenco = elenco.iterator();
		while(itElenco.hasNext()){
			Integer codigo = itElenco.next();
			Artista pesquisarArtista = ArtistaDAO.pesquisaArtista(codigo);
			if(pesquisarArtista != null)
				atores += pesquisarArtista.getNome() + ", ";
		}
		return atores;
	}
	
	/**
	 * Obtém uma representaão de em <code>String</code> com todos os diretores do filme.
	 * @param codigoFilme <code>int</code> com o código filme que se deseja obter os diretores.
	 * @return <code>String</code> com os diretores cadastrados.
	 */
	public static String obterDiretores(int codigoFilme){
		List<Integer> diretores = DiretorFilmeDAO.obterDiretorFilme(codigoFilme);
		
		if(diretores == null) return "";
		
		String diretor = "";
		
		Iterator<Integer> itDiretores = diretores.iterator();
		while(itDiretores.hasNext()){
			Integer codigo = itDiretores.next();
			Diretor pesquisaDiretor = DiretorDAO.pesquisaDiretor(codigo);
			if(pesquisaDiretor != null)
				diretor += pesquisaDiretor.getNome() + ", ";
		}
		return diretor;
	}//obterDiretores
	
	/**
	 * Obtém uma representaão de em <code>String</code> com todos os autores do filme.
	 * @param codigoFilme <code>int</code> com o código filme que se deseja obter os autores.
	 * @return <code>String</code> com os autores cadastrados.
	 */
	public static String obterAutores(int codigoFilme){
		List<Integer> autores = AutorFilmeDAO.obterAutoresFilme(codigoFilme);
		
		String autor = "";
		
		Iterator<Integer> itAutores = autores.iterator();
		while(itAutores.hasNext()){
			Integer codigo = itAutores.next();
			Autor pesquisaAutor = AutorDAO.pesquisaAutor(codigo);
			if(pesquisaAutor != null)
				autor += pesquisaAutor.getNome() + ", ";
		}
		return autor;
	}//obterAutores
	
	/**
	 * Obtém uma representaão de em <code>String</code> com os genêros do filme.
	 * @param codigoFilme <code>int</code> com o código filme que se deseja obter os genêros.
	 * @return <code>String</code> com o gênero cadastrado.
	 */
	public static String obterGeneros(int codigoFilme){
		List<Integer> genero = GeneroFilmeDAO.pesquisaGeneroFilme(codigoFilme);
		String generos = "";
		
		if(genero == null) return "";
		
		Iterator<Integer> itGenero = genero.iterator();
		while(itGenero.hasNext()){
			Integer codigo = itGenero.next();
			Genero pesquisarGenero = GeneroDAO.pesquisaGenero(codigo);
			if(pesquisarGenero != null)
				generos += pesquisarGenero.getNome() + ", ";
		}
		return generos;
	}//obterGeneros
	
	/**
	 * Obtém um array com os nomes dos filmes que os códigos foram recebidos por parâmetro.
	 * @param codigoFilmes <code>List</code> com os codigos dos filmes.
	 * @return <code>String[]</code> com os nomes dos filmes.
	 */
	public static String[] obterFilmes(List<Integer> codigoFilmes) {
		List<String> nomesFilmes = new ArrayList<String>();
		
		if(codigoFilmes == null || codigoFilmes.size() == 0) return null;
		Iterator<Integer> itCodigoFilmes = codigoFilmes.iterator();
		while(itCodigoFilmes.hasNext()){
			Integer codigo = itCodigoFilmes.next();
			Filme filme = FilmeDAO.pesquisarFilme(codigo);
			if(filme != null)
				nomesFilmes.add(filme.getTitulo());
		}
		return nomesFilmes.toArray(new String[0]);
	}//obterFilmes
}//classPesquisaControle
