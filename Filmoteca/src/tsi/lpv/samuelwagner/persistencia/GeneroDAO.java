package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tsi.lpv.samuelwagner.tipo.Genero;

/**A classe <code>GeneroDAO</code> implementa as operações necessárias para manipulação dos dados na tabela genero.
 * @author Samuel
 * @author Wagner
 */
public class GeneroDAO {
	private static final String INSERT_GENERO = "INSERT INTO genero (descricao) VALUES (?);";
	private static final String SELECT_GENERO_CODIGO = "SELECT * FROM genero WHERE codigo_genero = ?;";
	private static final String SELECT_GENERO_DESCRICAO = "SELECT * FROM genero WHERE descricao = ?;";
	private static final String SELECT_GENERO = "SELECT descricao FROM genero";
	/**Cadastra <code>Genero</code> no Banco de Dados.
	 * @param genero <code>Genero</code>.
	 */
	public static void cadastrarGenero(Genero genero){
		//Obtém uma conexão com o banco.
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			//Cria um objeto para fazer a inserção dos dados.
			PreparedStatement stmt = conn.prepareStatement(INSERT_GENERO);
			stmt.setString(1, genero.getNome());
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//cadastrarGenero
	
	/**Pesquisa a o genero apartir do codigo fornecido.
	 * @param codigoGenero <code>Int</code>.
	 * @return um <code>Genero</code>.
	 */
	public static Genero pesquisaGenero(int codigoGenero){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_GENERO_CODIGO);
			stmt.setInt(1, codigoGenero);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				Genero genero = new Genero();
				genero.setCodigo(resultSet.getInt(1));
				genero.setNome(resultSet.getString(2));
				stmt.close();
				return genero;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}//PesquisaGenero
	
	/**Pesquisa a o genero apartir do nome fornecido.
	 * @param descricao <code>String</code>.
	 * @return um <code>Genero</code>.
	 */
	public static Genero pesquisaGenero(String descricao){
		Connection conn = ConnectionFactory.getConnection();
		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_GENERO_DESCRICAO);
			
			stmt.setString(1, descricao);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				Genero genero = new Genero();
				genero.setCodigo(resultSet.getInt(1));
				genero.setNome(resultSet.getString(2));
				stmt.close();
				return genero;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}//PesquisaGenero
	
	/**
	 * Obtém o ultimo código cadastrado para um genero na tabela.
	 * @return <code>int</code>
	 */
	public static int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('genero_codigo_genero_seq'::regclass);");
			
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
	
	/**Obtém todos os nomes de generos cadastrado na tabela.
	 * @return um array de <code>String</code>.
	 */
	public static String[] obtemNomesGenero(){
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_GENERO);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				LinkedList<String> nomesCategoria = new LinkedList<String>();
				do{nomesCategoria.add(resultSet.getString(1));}while(resultSet.next());
				stmt.close();
				resultSet.close();
				nomesCategoria.add("Novo Genero...");
				return nomesCategoria.toArray(new String[0]);
			}else{
				resultSet.close();
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
