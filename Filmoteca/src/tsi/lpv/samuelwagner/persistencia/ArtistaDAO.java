package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsi.lpv.samuelwagner.tipo.Artista;

/**A classe <code>ArtistaDAO</code> implementa as operações necessárias para manipulação dos dados na tabela artista.
 * @author Samuel
 * @author Wagner
 */
public class ArtistaDAO {
	private static final String INSERT_ARTISTA = "INSERT INTO artista (nome) VALUES (?);";
	private static final String SELECT_ARTISTA_CODIGO = "SELECT * FROM artista WHERE codigo_artista = ?;";
	private static final String SELECT_ARTISTA_NOME = "SELECT * FROM artista WHERE nome = ?;";
	
	/**Cadastra o Objeto <code>Artista</code> no Banco de Dados.
	 * @param artista <code>Artista</code>.
	 */
	public static void cadastraArtista(Artista artista){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_ARTISTA);
			stmt.setString(1, artista.getNome());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Pesquisa a o artista a partir do código fornecido.
	 * @param nome <code>Int</code>.
	 * @return um <code>Artista</code>.
	 */
	public static Artista pesquisaArtista(String nome){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ARTISTA_NOME);
			stmt.setString(1, nome);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				Artista artista = new Artista();
				artista.setCodigo(resultSet.getInt(1));
				artista.setNome(resultSet.getString(2));
				stmt.close();
				return artista;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**Pesquisa a o artista apartir do codigo fornecido.
	 * @param codigoArtista <code>Int</code>.
	 * @return um <code>Artista</code>.
	 */
	public static Artista pesquisaArtista(int codigoArtista){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ARTISTA_CODIGO);
			stmt.setInt(1, codigoArtista);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				Artista artista = new Artista();
				artista.setCodigo(resultSet.getInt(1));
				artista.setNome(resultSet.getString(2));
				stmt.close();
				return artista;
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
	 * Obtém o ultimo código cadastrado para um artista na tabela.
	 * @return <code>int</code>
	 */
	public static int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('artista_codigo_artista_seq'::regclass);");
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
}
