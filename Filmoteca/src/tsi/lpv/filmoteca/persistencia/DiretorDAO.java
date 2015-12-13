package tsi.lpv.filmoteca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsi.lpv.filmoteca.modelo.Diretor;

/**A classe <code>DiretorDAO</code> implementa as operações necessárias para manipulação dos dados na tabela diretor.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class DiretorDAO {
	private static final String INSERT_DIRETOR = "INSERT INTO diretor (nome) VALUES (?);";
	private static final String SELECT_DIRETOR_CODIGO = "SELECT * FROM diretor WHERE codigo_diretor = ?;";
	private static final String SELECT_DIRETOR_NOME = "SELECT * FROM diretor WHERE UPPER(nome) = UPPER(?);";
	
	/**Cadastra <code>Diretor</code> no Banco de Dados.
	 * @param diretor <code>Diretor</code>.
	 */
	public static void cadastraDiretor(Diretor diretor) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_DIRETOR);
			stmt.setString(1, diretor.getNome());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Pesquisa a o diretor apartir do codigo fornecido.
	 * @param nome <code>Int</code>.
	 * @return um <code>Diretor</code>.
	 */
	public static Diretor pesquisaDiretor(String nome) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_DIRETOR_NOME);
			stmt.setString(1, nome);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				Diretor diretor = new Diretor();
				diretor.setCodigo(resultSet.getInt(1));
				diretor.setNome(resultSet.getString(2));
				stmt.close();
				return diretor;
			}else{
				stmt.close();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**Pesquisa a o diretor apartir do codigo fornecido.
	 * @param codigoDiretor <code>Int</code>.
	 * @return um <code>Diretor</code>.
	 */
	public static Diretor pesquisaDiretor(int codigoDiretor) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_DIRETOR_CODIGO);
			stmt.setInt(1, codigoDiretor);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				Diretor diretor = new Diretor();
				diretor.setCodigo(resultSet.getInt(1));
				diretor.setNome(resultSet.getString(2));
				stmt.close();
				return diretor;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Obtém o ultimo código cadastrado para um diretor na tabela.
	 * @return <code>int</code>
	 */
	public static int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('diretor_codigo_diretor_seq'::regclass);");
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
	}
}//class DiretorDAO
