package tsi.lpv.filmoteca.trataeventos;

import java.io.IOException;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
