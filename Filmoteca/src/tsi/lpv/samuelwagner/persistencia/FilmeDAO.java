package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tsi.lpv.samuelwagner.tipo.Filme;

/**
 * A classe <code>FilmeDAO</code> é responsável pelas informações de manipulação de dados da tabela
 * filme.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class FilmeDAO {
	private static final String INSERT_FILME = "INSERT INTO filme (titulo,duracao,ano,data_lancamento,sinopse,"
			+ "classificacao_etaria,classificacao_imdb,classificacao_pessoal,midia,poster) VALUES (?,?,?,?,?,?,?,?,?,?);";
	private static final String PESQUISA_FILME_TITULO = "SELECT * FROM filme WHERE UPPER(titulo) = UPPER(?);";
	private static final String PESQUISA_FILME_CODIGO = "SELECT * FROM filme WHERE codigo_filme = ?;";
	private static final String PESQUISA_SATISFACAO_DESC ="SELECT * FROM filme ORDER BY classificacao_pessoal DESC ";
	
	/**
	 * Cadastra um filme na tabela filme do banco de dados.
	 * @param filme <code>Filme</code> objeto a ser persistido.
	 */
	public static void cadastrarFilme(Filme filme) {
		//Obtém uma conexão com o banco.
		Connection conn = ConnectionFactory.getConnection();
		try {
			//Cria um objeto para fazer a inserção dos dados.
			PreparedStatement stmt = conn.prepareStatement(INSERT_FILME);
			
			stmt.setString(1, filme.getTitulo());
			stmt.setInt(2, filme.getDuracao());
			stmt.setInt(3, filme.getAno());
			stmt.setDate(4, new Date(filme.getDataLancamento().getTimeInMillis()));
			stmt.setString(5, filme.getSinopse());
			stmt.setString(6, filme.getClassificacaoEtaria());
			stmt.setInt(7, filme.getClassificacaoIMDB());
			stmt.setInt(8, filme.getClassificacaoPessoal());
			stmt.setString(9, filme.getMidia());
			stmt.setBytes(10, filme.getPoster());
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//cadastrarFilme
	
	/**
	 * Obtém o ultimo código cadastrado para um filme na tabela.
	 * @return <code>int</code>.
	 */
	public static int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('filme_codigo_filme_seq');");
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				int codigo = resultSet.getInt(1);
				stmt.close();
				return codigo;
			}else{
				stmt.close();
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}//obterUltimoCodigo
	
	/**
	 * Obtém um filme através do nome especificado por parâmetro.
	 * @param nomeFilme <code>String</code> nome do filme.
	 * @return <code>Filme</code> ou <code>null</code> caso o filme não seja encontrado.
	 */
	public static Filme pesquisarFilme(String nomeFilme){
		
		try {
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(PESQUISA_FILME_TITULO);
			stmt.setString(1, nomeFilme);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
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
				filme.setPoster(resultSet.getBytes(11));
				stmt.close();
			return filme;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}//pesquisarFilme()
	
	/**
	 * Obtém um filme através do código especificado por parâmetro.
	 * 
	 * @param codigo <code>int</code> com o código do filme.
	 * @return <code>Filme</code> ou <code>null</code> caso o filme não seja encontrado.
	 */
	public static Filme pesquisarFilme(int codigo){
		
		try {
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(PESQUISA_FILME_CODIGO);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
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
				filme.setPoster(resultSet.getBytes(11));
				stmt.close();
			return filme;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}//pesquisarFilme()
	
	/**
	 * Obtém os dados dos filmes ordenados de forma decrescente pela classificação pessoal.
	 * @return <code>List</code> com o resultado da pesquisa ou <code>null</code> informando que não encontrou nenhum dado.
	 */
	public static List<Filme> pesquisarFilmeCriterio() {
		
		List<Filme> filmes = new ArrayList<Filme>();
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(PESQUISA_SATISFACAO_DESC);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				do{
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
					filme.setPoster(resultSet.getBytes(11));
					filmes.add(filme);
				}while(resultSet.next());
				stmt.close();
				resultSet.close();
				return filmes;
			}
			else
			{
				stmt.close();
				resultSet.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}//FilmeDAO
