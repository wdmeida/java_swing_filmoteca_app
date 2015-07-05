package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe <code>DiretorFilmeDAO</code> contém as operações de inserção e pesquisa de dados na tabela de referência
 * de diretor filme.
 * 
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 */
public class DiretorFilmeDAO {
	private static final String INSERIR_DIRETOR_FILME = "INSERT INTO diretorfilme (codigo_filme,codigo_diretor) VALUES (?,?);";
	private static final String OBTER_FILMES_DIRETOR = "SELECT codigo_filme FROM diretorfilme WHERE codigo_diretor = ?;";
	private static final String OBTER_DIRETOR_FILME = "SELECT codigo_diretor FROM diretorfilme WHERE codigo_filme = ?;";
	
	/**
	 * Cadastra os códigos do filme e do diretor na tabela de referência do banco.
	 * @param codigoFilme <code>int</code> código do filme.
	 * @param codigoDiretor <code>int</code> código do diretor.
	 */
	public static void cadastrarDiretorFilme(int codigoFilme, int codigoDiretor) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERIR_DIRETOR_FILME);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoDiretor);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//cadastrarDiretorFilme
	
	/**
	 * Obtém a lista de todos os filmes de um determinado diretor.
	 * @param codigo <code>int</code> com o código do diretor que se deseja obter os filmes.
	 * @return <code>List</code> com os códigos dos filmes que foram produzidos por um determinado diretor.
	 */
	public static List<Integer> obterFilmesDiretor(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_FILMES_DIRETOR);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) codigos.add(resultSet.getInt(1));
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigos;
	}//obterFilmesDiretor()
	
	/**
	 * Obtém a lista de todos os diretores de um determinado filme.
	 * @param codigo <code>int</code> com o código do filme que se deseja obter o diretor.
	 * @return <code>List</code> com os códigos dos diretores que fazem parte deste filme.
	 */
	public static List<Integer> obterDiretorFilme(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_DIRETOR_FILME);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) codigos.add(resultSet.getInt(1));
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigos;
	}//obterFilmesDiretor()
}//class DiretorFilmeDAO
