package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.controller.PesquisaControle;
import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.gui.IgFilmoteca;
import tsi.lpv.samuelwagner.gui.IgMensagem;
import tsi.lpv.samuelwagner.gui.IgResultadoPesquisaFilme;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.persistencia.PaisDAO;
import tsi.lpv.samuelwagner.persistencia.PaisFilmeDAO;
import tsi.lpv.samuelwagner.tipo.Filme;

/** Classe <code>TratadorEventoPesquisarFilme</code> responsável por tratar os eventos 
 *  da classe <code>IgFilmoteca</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida 
 */
public class TratadorEventoPesquisarFilme implements ActionListener {
	private IgFilmoteca igFilmoteca;
	private IgResultadoPesquisaFilme igResultadoPesquisaFilme = new IgResultadoPesquisaFilme(igFilmoteca);
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoPesquisarFilme</code>.
	 * @param igFilmoteca referencia da Classe <code>IgFilmoteca</code>.
	 */
	public TratadorEventoPesquisarFilme(IgFilmoteca igFilmoteca) {
		this.igFilmoteca = igFilmoteca;
	}

	//Tratador de eventos do clique do mouse.
	@Override
	public void actionPerformed(ActionEvent event) {
		if(!igFilmoteca.getPesquisarTextField().getText().equalsIgnoreCase("Pesquisar Filme") && !igFilmoteca.getPesquisarTextField().getText().equals("")) 
			pesquisarFilme();
		else
			new IgMensagem(igFilmoteca, "Digite o nome do filme para pesquisar");
	}
	
	/**
	 * Pesquisa as informações do filme no banco, caso encontrado, carrega os dados para o painel de resultado 
	 * da pesquisa e retorna o resultado para o usuário.
	 */
	public void pesquisarFilme(){
		String nome = igFilmoteca.getPesquisarTextField().getText();
		
		//Verifica se o nome é vazio, caso seja, retorna cancela a pesquisa.
		if(nome.equals("") || nome == null){
			new IgMensagem(igFilmoteca, "Digite o nome do filme para pesquisar.");
			return;
		}
		
		//Pesquisa o filme no banco de dados.
		Filme filme = FilmeDAO.pesquisarFilme(nome);
		if(filme != null) exibeDadosFilme(filme);
		else new IgMensagem(igFilmoteca, "Filme não encontrado.");
	}//pesquisarFilme()
	
	/**
	 * Exibe todos os dados do filme cadastrado.
	 * @param filme <code>Filme</code> objeto com o filme a ser exibido.
	 */
	public void exibeDadosFilme(Filme filme){
		//Configura o nome do filme na tela.
		igResultadoPesquisaFilme.getNomeFilme().setText(filme.getTitulo());
		
		/*//Configura o país.
		int codPais = PaisFilmeDAO.obterPaisFilme(filme.getCodigo());
		igResultadoPesquisaFilme.getPaisLabel().setText("País: " + PaisDAO.pesquisaPais(codPais).getNome());*/
		
		//Configura o ano.
		igResultadoPesquisaFilme.getAnoLabel().setText("Ano: " + Integer.toString(filme.getAno()));
		
		//Configura a duração.
		igResultadoPesquisaFilme.getDuracaoLabel().setText("Duração: " + Integer.toString(filme.getDuracao()) + " minutos");
		
		//Configura a classificação etária.
		igResultadoPesquisaFilme.getEtariaLabel().setText("Classificação etária: " + filme.getClassificacaoEtaria());
		
		//Configura o gênero.
		igResultadoPesquisaFilme.getGeneroLabel().setText("Gênero: " + PesquisaControle.obterGeneros(filme.getCodigo()));
		
		//Configura a sinopse
		igResultadoPesquisaFilme.getSinopseTextArea().setText(filme.getSinopse());
		
		//Configura a mídia
		igResultadoPesquisaFilme.getMidiaTextField().setText(filme.getMidia());
		
		//Configura a classificação IMDB e pessoal.
		igResultadoPesquisaFilme.getImdbTextField().setText(Integer.toString(filme.getClassificacaoIMDB()) + " estrelas");
		igResultadoPesquisaFilme.getPessoalTextField().setText(Integer.toString(filme.getClassificacaoPessoal()) + " estrelas");
		
		//Configura autor, diretor e elenco.
		igResultadoPesquisaFilme.getElencoTextArea().setText(PesquisaControle.obterElenco(filme.getCodigo()));
		igResultadoPesquisaFilme.getAutorTextField().setText(PesquisaControle.obterAutores(filme.getCodigo()));
		igResultadoPesquisaFilme.getDiretorTextField().setText(PesquisaControle.obterDiretores(filme.getCodigo()));
		
		//Configura a data
		igResultadoPesquisaFilme.getLancamentoTextField().setText(FuncaoAuxiliar.coverteDataParaString(filme.getDataLancamento(), true));
		
		//Configura a foto
		//igResultadoPesquisaFilme.getFotoLabel().setIcon());
		
		//Exibe a tela para o usuário.
		igResultadoPesquisaFilme.setVisible(true);
	}//exibeDadosFilme()
	
}//class TratadorEventoPesquisarFilme
