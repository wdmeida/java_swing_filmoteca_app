package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Classe Responsavel das Operações do Banco para obter as relações entre o Artistas e seu filme.
 * @author Samuel
 * @author Wagner
 */
public class ElencoDAO {
	private static final String INSERT_ELENCO = "INSERT INTO elenco(codigo_filme,codigo_artista) VALUES(?,?);";
	private static final String SELECT_ELENCO_FILME = "SELECT codigo_filme FROM elenco WHERE codigo_artista = ?";
	private static final String SELECT_ELENCO_ARTISTA = "SELECT codigo_artista FROM elenco WHERE codigo_filme = ?";
	
	/** Associa o artista ao filme que participa.
	 * @param codigoFilme <code>int</code> código do filme.
	 * @param codigoArtista <code>int</code> código do artista.
	 */
	public static void cadastraElenco(int codigoFilme,int codigoArtista){
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_ELENCO);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoArtista);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Recupera os codigos do filme em qual o artista trabalhou.
	 * @param codigoArtista <code>int</code> código do artista. 
	 * @return um <code>List</code> com os códigos dos Filmes.
	 */
	public static List<Integer> pesquisElencoFilme(int codigoArtista){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ELENCO_FILME);
			stmt.setInt(1, codigoArtista);
			
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
	
	/**Recupera os codigos dos artistas que tabalhou no filme informado..
	 * @param codigoFilme <code>int</code> código do filme. 
	 * @return um <code>List</code> com os códigos dos artistas.
	 */
	public static List<Integer> pesquisElencoArtista(int codigoFilme){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ELENCO_ARTISTA);
			stmt.setInt(1, codigoFilme);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(!resultSet.next()){
				stmt.close();
				return null;
			}
			
			List<Integer> listCodigosFilme = new ArrayList<>();
			
			while(resultSet.next()) listCodigosFilme.add(resultSet.getInt(1));
			
			stmt.close();
			resultSet.close();
			
			return listCodigosFilme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
