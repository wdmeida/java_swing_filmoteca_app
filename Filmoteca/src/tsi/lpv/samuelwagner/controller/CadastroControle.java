package tsi.lpv.samuelwagner.controller;

import tsi.lpv.samuelwagner.persistencia.ArtistaDAO;
import tsi.lpv.samuelwagner.persistencia.AutorDAO;
import tsi.lpv.samuelwagner.persistencia.AutorFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.ElencoDAO;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.PaisDAO;
import tsi.lpv.samuelwagner.persistencia.PaisFilmeDAO;
import tsi.lpv.samuelwagner.tipo.Artista;
import tsi.lpv.samuelwagner.tipo.Autor;
import tsi.lpv.samuelwagner.tipo.Diretor;
import tsi.lpv.samuelwagner.tipo.Filme;
import tsi.lpv.samuelwagner.tipo.Genero;
import tsi.lpv.samuelwagner.tipo.Pais;

public class CadastroControle {
	
	public static void cadastro(Filme filme, Artista[] artistas,Diretor[] diretores, Autor[] autores, Genero genero,Pais pais) {
		int codigoFilme,codigoArtista,codigoDiretor,codigoAutor,codigoGenero,codigoPais;
		
		codigoFilme = cadastraFilme(filme);
		for(Artista artista : artistas){
			codigoArtista = cadastraArtista(artista);
			cadastraEleco(codigoFilme, codigoArtista);
		}
		
		for(Diretor diretor : diretores){
			codigoDiretor = cadastraDiretor(diretor);
			cadastraDiretorFilme(codigoFilme, codigoDiretor);
		}
		
		for(Autor autor : autores){
			codigoAutor = cadastraAutor(autor);
			cadastraAutorFilme(codigoFilme, codigoAutor);
		}
		
		codigoGenero = cadastraGenero(genero);
		codigoPais = cadastraPais(pais);
		cadastraPaisFilme(codigoFilme, codigoPais);
		cadastraGeneroFilme(codigoFilme, codigoGenero);
		
	}
	
	private static int cadastraFilme(Filme filme) {
		FilmeDAO.cadastrarFilme(filme);
		return FilmeDAO.obterUltimoCodigo();
	}
	
	private static int cadastraArtista(Artista artista) {
		ArtistaDAO.cadastraArtista(artista);
		return ArtistaDAO.obterUltimoCodigo();
	}
	
	private static int cadastraDiretor(Diretor diretor) {
		DiretorDAO.cadastraDiretor(diretor);
		return DiretorDAO.obterUltimoCodigo();
	}
	
	private static int cadastraAutor(Autor autor) {
		AutorDAO.cadastraAutor(autor);
		return AutorDAO.obterUltimoCodigo();
	}
	
	private static int cadastraGenero(Genero genero) {
		GeneroDAO.cadastrarGenero(genero);
		return GeneroDAO.obterUltimoCodigo();
	}
	
	private static int cadastraPais(Pais pais) {
		PaisDAO.cadastraPais(pais);
		return PaisDAO.obterUltimoCodigo();
	}
	
	private static void cadastraEleco(int codigoFilme, int codigoArtista){
		ElencoDAO.cadastraElenco(codigoFilme, codigoArtista);
	}
	
	private static void cadastraDiretorFilme(int codigoFilme, int codigoDiretor){
		DiretorFilmeDAO.cadastrarDiretorFilme(codigoFilme, codigoDiretor);
	}
	
	private static void cadastraPaisFilme(int codigoFilme, int codigoPais){
		PaisFilmeDAO.cadastrarPaisFilme(codigoFilme, codigoPais);
	}
	
	private static void cadastraGeneroFilme(int codigoFilme, int codigoGenero){
		GeneroFilmeDAO.cadastraGeneroFilme(codigoFilme, codigoGenero);
	}
	
	private static void cadastraAutorFilme(int codigoFilme, int codigoAutor){
		AutorFilmeDAO.cadastrarAutorFilme(codigoFilme, codigoAutor);
	}
}
