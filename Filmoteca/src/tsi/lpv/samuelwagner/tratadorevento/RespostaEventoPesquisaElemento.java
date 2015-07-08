package tsi.lpv.samuelwagner.tratadorevento;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import tsi.lpv.samuelwagner.controller.PesquisaControle;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgFilmoteca;
import tsi.lpv.samuelwagner.gui.IgMensagem;
import tsi.lpv.samuelwagner.gui.IgResultadoPesquisa;
import tsi.lpv.samuelwagner.persistencia.ArtistaDAO;
import tsi.lpv.samuelwagner.persistencia.AutorDAO;
import tsi.lpv.samuelwagner.persistencia.AutorFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.ElencoDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroFilmeDAO;
import tsi.lpv.samuelwagner.tipo.Artista;
import tsi.lpv.samuelwagner.tipo.Autor;
import tsi.lpv.samuelwagner.tipo.Diretor;
import tsi.lpv.samuelwagner.tipo.Genero;

/**
 * A classe <code>RespostaEventoPesquisaElemento</code> é responsável por tratar os eventos de pesquisa 
 * do aplicativo Filmoteca.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class RespostaEventoPesquisaElemento {
	private IgFilmoteca IgFilmoteca;
	private String nomePesquisa;
	private JButton botaoChamada;
	
	/**
	 * Construtor da classe <code>RespostaEventoPesquisaElemento.
	 * @param igFilmoteca <code>IgFilmoteca</code> com a referência da janela principal.
	 * @param nomePesquisa <code>String</code> com o item a pesquisas.
	 * @param botaoChamada <code>JButton</code> com a referência do botão que disparou o evento.
	 */
	public RespostaEventoPesquisaElemento(IgFilmoteca igFilmoteca, String nomePesquisa, JButton botaoChamada) {
		IgFilmoteca = igFilmoteca;
		this.nomePesquisa = nomePesquisa;
		this.botaoChamada = botaoChamada;
	}
	
	/**
	 * Controla a pesquisa do usuário através da referência do botão clicado.
	 */
	public void pesquisa(){
		if(botaoChamada.equals(IgFilmoteca.getAutorButton())) buscarAutor();
		if(botaoChamada.equals(IgFilmoteca.getAtorButton())) buscarAtor();
		if(botaoChamada.equals(IgFilmoteca.getDiretorButton())) buscarDiretor();
		if(botaoChamada.equals(IgFilmoteca.getGeneroButtono())) buscarGenero();
	}
	
	/**
	 * Busca as informações sobre o ator.
	 */
	private void buscarAtor(){
		if(Validador.validaCampoVazio(nomePesquisa)){
			new IgMensagem(IgFilmoteca, "Campo não pode ser vazio.");
			return;
		}
		else
		{
			//Verifica se o artista existe.
			Artista artista = ArtistaDAO.pesquisaArtista(nomePesquisa);
			if(artista == null) {new IgMensagem(IgFilmoteca, "Artista não cadastrado."); return;}
			else{
				//Obtém a lista de filmes que o artista trabalhou.
				List<Integer> filmesArtista = ElencoDAO.pesquisElencoFilme(artista.getCodigo());
				
				//Obtém os filmes nomes dos filmes.
				String filmes[] = PesquisaControle.obterFilmes(filmesArtista);
				carregarElementosJList(filmes);
			}
		}			
	}
	
	/**
	 * Busca as informações sobre o autor.
	 */
	private void buscarAutor() {
		if(Validador.validaCampoVazio(nomePesquisa)){
			new IgMensagem(IgFilmoteca, "Campo não pode ser vazio.");
			return;
		}
		else
		{
			//Verifica se o autor existe.
			Autor autor = AutorDAO.pesquisaAutor(nomePesquisa);
			if(autor == null) {new IgMensagem(IgFilmoteca, "Autor não cadastrado."); return;}
			else{
				//Obtém a lista de filmes que o autor trabalhou.
				List<Integer> filmesAutor = AutorFilmeDAO.obterFilmesAutor(autor.getCodigo());
				
				//Obtém os filmes nomes dos filmes.
				String filmes[] = PesquisaControle.obterFilmes(filmesAutor);
		
				carregarElementosJList(filmes);
			}
		}			
	}//buscaAutor()
	
	/**
	 * Busca as informações sobre um diretor do filme.
	 */
	private void buscarDiretor(){
		if(Validador.validaCampoVazio(nomePesquisa)){
			new IgMensagem(IgFilmoteca, "Campo não pode ser vazio.");
			return;
		}
		else
		{
			//Verifica se o autor existe.
			Diretor diretor = DiretorDAO.pesquisaDiretor(nomePesquisa);
			if(diretor == null) {new IgMensagem(IgFilmoteca, "Diretor não cadastrado."); return;}
			else{
				//Obtém a lista de filmes que o diretor trabalhou.
				List<Integer> filmesDiretor = DiretorFilmeDAO.obterFilmesDiretor(diretor.getCodigo());
				
				//Obtém os nomes dos filmes.
				String filmes[] = PesquisaControle.obterFilmes(filmesDiretor);
				
				//Exibe no jlist.
				carregarElementosJList(filmes);
			}
		}		
	}
	
	/**
	 * Busca as informações sobre o gênero do filme.
	 */
	private void buscarGenero(){
		if(Validador.validaCampoVazio(nomePesquisa)){
			new IgMensagem(IgFilmoteca, "Campo não pode ser vazio.");
			return;
		}
		else
		{
			//Verifica se o autor existe.
			Genero genero = GeneroDAO.pesquisaGenero(nomePesquisa);
			if(genero == null) {new IgMensagem(IgFilmoteca, "Não há nenhum filme cadastrado nesse gênero."); return;}
			else{
				//Obtém a lista de filmes que o autor trabalhou.
				List<Integer> filmesGenero = GeneroFilmeDAO.pesquisaFilmeGenero(genero.getCodigo());
				
				//Obtém os filmes nomes dos filmes.
				String filmes[] = PesquisaControle.obterFilmes(filmesGenero);
				if(filmes != null)
					carregarElementosJList(filmes);
				else
					new IgMensagem(IgFilmoteca, "Não há nenhum filme cadastrado nesse gênero.");
			}
		}		
	}
	
	/**
	 * Carrega os elementos encontrados para o comboBox e exibe a tela de resultados.
	 * @param filmes <code>String[]</code> com os nomes dos filmes para serem carregados.
	 */
	private void carregarElementosJList(String[] filmes){
		DefaultListModel<String> valores = new DefaultListModel<String>();
		if(filmes != null)
			for(String filme : filmes) valores.addElement(filme);
		
		new IgResultadoPesquisa(IgFilmoteca, "Filmes encontrados", valores);
	}
	
}//class TratadorEventoPesquisaElemento
