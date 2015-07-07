package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.gui.IgFilmoteca;
import tsi.lpv.samuelwagner.persistencia.ElencoDAO;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.tipo.Filme;

/** Classe <code>TratadorEventoPesquisarFilme</code> responsável por tratar os eventos 
 *  da classe <code>IgFilmoteca</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida 
 */
public class TratadorEventoPesquisarFilme implements ActionListener {
	private IgFilmoteca igFilmoteca;
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoPesquisarFilme</code>.
	 * @param igFilmoteca referencia da Classe <code>IgFilmoteca</code>.
	 */
	public TratadorEventoPesquisarFilme(IgFilmoteca igFilmoteca) {
		this.igFilmoteca = igFilmoteca;
	}

	//Tratador de eventos do clique do mouse.
	@Override
	public void actionPerformed(ActionEvent event) {
		if(!igFilmoteca.getPesquisarTextField().getText().equalsIgnoreCase("Pesquisar Filme")) pesquisarFilme();
	}
	
	/**
	 * Pesquisa as informações do filme no banco, caso encontrado, carrega os dados para o painel de resultado 
	 * da pesquisa e retorna o resultado para o usuário.
	 */
	public void pesquisarFilme(){
		String nome = igFilmoteca.getPesquisarTextField().getText();
		
		//Verifica se o nome é vazio, caso seja, retorna cancela a pesquisa.
		if(nome.equals("") || nome == null){
			FuncaoAuxiliar.exibirMensagemErro(igFilmoteca, "Campo vazio.", "Pesquisar Filme");
			return;
		}
		
		//Pesquisa o filme no banco de dados.
		Filme filme = FilmeDAO.pesquisarFilme(nome);
		if(filme == null){
			FuncaoAuxiliar.exibirMensagemErro(igFilmoteca, "Filme não cadastrado.", "Pesquisar Filme");
			return;
		}
		//Caso tenha encontrado, pesquisa o restante das informações do filme.
		List<Integer> elenco = ElencoDAO.pesquisElencoArtista(filme.getCodigo());
		
	}
}//class TratadorEventoPesquisarFilme
