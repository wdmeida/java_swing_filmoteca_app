package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**A classe <code>GeneroFilmeDAO</code> contém as operações de inserção e pesquisa de dados na tabela de referência
 * de genero filme.
 * @author Samuel
 * @author Wagner
 */
public class GeneroFilmeDAO {
	private static final String INSERT_GENERO_FILME = "INSERT INTO generofilme(codigo_filme,codigo_genero) VALUES(?,?);";
	private static final String SELECT_GENEROF_FILME = "SELECT codigo_filme FROM generofilme WHERE codigo_genero = ?";
	private static final String SELECT_GENEROF_GENERO = "SELECT codigo_genero FROM generofilme WHERE codigo_filme = ?";
	
	/** Associa o filme ao seu genero.
	 * @param codigoFilme <code>int</code> código do filme.
	 * @param codigoGenero <code>int</code> código do genero.
	 */
	public static void cadastraGeneroFilme(int codigoFilme,int codigoGenero){
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_GENERO_FILME);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoGenero);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Recupera os códigos do filme em relação ao seu gênero.
	 * @param codigoGenero <code>int</code> código do gênero. 
	 * @return um <code>List</code> com os códigos dos Filmes.
	 */
	public static List<Integer> pesquisaFilmeGenero(int codigoGenero){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_GENEROF_FILME);
			stmt.setInt(1, codigoGenero);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(!resultSet.next()){
				stmt.close();
				resultSet.close();
				return null;
			}
			
			List<Integer> listCodigosFilme = new ArrayList<>();
			while(resultSet.next())	listCodigosFilme.add(resultSet.getInt(1));
			
			stmt.close();
			resultSet.close();
			
			return listCodigosFilme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**Recupera os gênero referentes ao filme informado.
	 * @param codigoFilme <code>int</code> código do filme. 
	 * @return um <code>int</code> com o código do genero.
	 */
	public static List<Integer> pesquisaGeneroFilme(int codigoFilme){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_GENEROF_GENERO);
			stmt.setInt(1, codigoFilme);
			
			ResultSet resultSet = stmt.executeQuery();
			if(!resultSet.next()){
				stmt.close();
				resultSet.close();
				return null;
			}
			
			List<Integer> listCodigosFilme = new ArrayList<>();
			while(resultSet.next())	listCodigosFilme.add(resultSet.getInt(1));
			stmt.close();
			resultSet.close();
			
			return listCodigosFilme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
