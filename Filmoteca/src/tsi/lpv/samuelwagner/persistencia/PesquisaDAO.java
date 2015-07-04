package tsi.lpv.samuelwagner.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import tsi.lpv.samuelwagner.tipo.Artista;
import tsi.lpv.samuelwagner.tipo.Dados;
import tsi.lpv.samuelwagner.tipo.Filme;

public class PesquisaDAO {

	/*Verifica se isso seria necessario Wagner...
	 * Pois acho q pra acessar o codigo de cada tabela usando as tabelas de indice...
	 * teria que fazer uma pesquisa a tabela diretamente... penso q esse metodo faz isso
	 * Pois n penso outra utilidade para aquelas tabelas...
	 * Sendo q tem metodos q poderia retorna um Array de codigos... ate pq um Ator pode 
	 * fazer varios filmes...
	 */
	/*Retorna o codigo do artista que faz o filme informado pelo codigo*/
	public int pesquisaCodigoElencoArtista(int codigoFilme){
		final String pesquisaSql = "SELECT codigo_artista FROM elenco WHERE codigo_filme = ?;";
		try {
			PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(pesquisaSql);
			preparedStatement.setInt(1, codigoFilme);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/*Retorna o codigo do filme que o artista faz informado pelo codigo*/
	public int pesquisaCodigoElencoFilme(int codigoArtista){
		final String pesquisaSql = "SELECT codigo_filme FROM elenco WHERE codigo_artista = ?;";
		try {
			PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(pesquisaSql);
			preparedStatement.setInt(1, codigoArtista);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//Verifica se isso seria necessario Wagner...
		/*Retorna o codigo do pais que o filme retrata informado pelo codigo*/
		public int pesquisaCodigoPais(int codigoFilme){
			final String pesquisaSql = "SELECT codigo_pais FROM paisfilme WHERE codigo_filme = ?;";
			try {
				PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(pesquisaSql);
				preparedStatement.setInt(1, codigoFilme);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				
				return resultSet.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
		
		/*Retorna o codigo do filme do pais em que e filmado, informado pelo codigo*/
		public int pesquisaCodigoPaisFilme(int codigoPais){
			final String pesquisaSql = "SELECT codigo_filme FROM paisfilme WHERE codigo_pais = ?;";
			try {
				PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(pesquisaSql);
				preparedStatement.setInt(1, codigoPais);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				
				return resultSet.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
	
	
	
	public Dados pesquisa(String nomeTabela,String nomeString){
		final String pesquisaSql = "SELECT * FROM ? WHERE nome = ?;";
		try {
			PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(pesquisaSql);
			preparedStatement.setString(1, nomeTabela);
			preparedStatement.setString(2, nomeString);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			Dados dados = new Dados();
			dados.setNome(resultSet.getString(1));
			dados.setCodigo(resultSet.getInt(2));
			return dados;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Filme pesquisaFilme(int id){
		final String pesquisaSql = "SELECT * FROM filme WHERE codigo_filme = ?;";
		try {
			PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(pesquisaSql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			//Cria Objeto Filme.
			Filme filme= new Filme();
			filme.setCodigo(resultSet.getInt(1));
			filme.setTitulo(resultSet.getString(2));
			filme.setDuracao(resultSet.getInt(3));
			filme.setAno(resultSet.getInt(4));
			Calendar cal = Calendar.getInstance();
			cal.setTime(resultSet.getDate(5));
			filme.setDataLancamento(cal);
			filme.setSinopse(resultSet.getString(6));
			filme.setClassificacaoEtaria(resultSet.getString(7));
			filme.setClassificacaoIMDB(resultSet.getInt(8));
			filme.setClassificacaoPessoal(resultSet.getInt(9));
			filme.setMidia(resultSet.getString(10));
			//filme.setPoster(new F);
			return filme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
