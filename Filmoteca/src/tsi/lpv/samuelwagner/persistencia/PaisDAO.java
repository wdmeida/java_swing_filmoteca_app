package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsi.lpv.samuelwagner.tipo.Pais;

/**A classe <code>PaisDAO</code> implementa as operações necessárias para manipulação dos dados na tabela pais.
 * @author Samuel
 * @author Wagner
 */
public class PaisDAO {
	private static final String INSERT_PAIS = "INSERT INTO pais (nome) VALUES (?);";
	private static final String SELECT_PAIS_CODIGO = "SELECT * FROM pais WHERE codigo_pais = ?;";
	private static final String SELECT_PAIS_NOME = "SELECT * FROM pais WHERE nome = ?;";
	
	/**Cadastra <code>Pais</code> no Banco de Dados.
	 * @param pais <code>Pais</code>.
	 */
	public static void cadastraPais(Pais pais) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_PAIS);
			stmt.setString(1, pais.getNome());
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Pesquisa a o pais apartir do codigo fornecido.
	 * @param codigoPais <code>Int</code>.
	 * @return um <code>Pais</code>.
	 */
	public static Pais pesquisaPais(int codigoPais){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_PAIS_CODIGO);
			stmt.setInt(1, codigoPais);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				Pais pais = new Pais();
				pais.setCodigo(resultSet.getInt(1));
				pais.setNome(resultSet.getString(2));
				stmt.close();
				return pais;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**Pesquisa a o pais apartir do codigo fornecido.
	 * @param nome <code>Int</code>.
	 * @return um <code>Pais</code>.
	 */
	public static Pais pesquisaPais(String nome){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_PAIS_NOME);
			stmt.setString(1, nome);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				Pais pais = new Pais();
				pais.setCodigo(resultSet.getInt(1));
				pais.setNome(resultSet.getString(2));
				stmt.close();
				return pais;
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
	 * Obtém o ultimo código cadastrado para um pais na tabela.
	 * @return <code>int</code>
	 */
	public static int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('pais_codigo_pais_seq'::regclass);");
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
