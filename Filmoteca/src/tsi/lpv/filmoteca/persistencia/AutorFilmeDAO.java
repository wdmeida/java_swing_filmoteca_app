package tsi.lpv.filmoteca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe <code>AutorFilmeDAO</code> implementa os métodos necessários para manipulação da tabela de referências autorfilme.
 * @author Wagner Almeida.
 * @author Samuel Gonçalves.
 *
 */
public class AutorFilmeDAO {
	private static final String INSERIR_AUTOR_FILME = "INSERT INTO autorfilme (codigo_filme,codigo_autor) VALUES (?,?);";
	private static final String OBTER_AUTOR_FILME = "SELECT codigo_autor FROM autorfilme WHERE codigo_filme = ?;";
	private static final String OBTER_FILMES_AUTOR = "SELECT codigo_filme FROM autorfilme WHERE codigo_autor = ?;";
	
	/**
	 * Cadastra os códigos do autor e do filme na tabela de referência do banco.
	 * @param codigoFilme <code>int</code> código do filme.
	 * @param codigoAutor <code>int</code> código do autor.
	 */
	public static void cadastrarAutorFilme(int codigoFilme, int codigoAutor) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERIR_AUTOR_FILME);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoAutor);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//cadastrarAutorFilme
	
	/**
	 * Obtém a lista de todos os autores de um filme.
	 * @param codigo <code>int</code> com o código do filme que se deseja obter os autores.
	 * @return <code>List</code> com os códigos dos autores que escritos o filme.
	 */
	public static List<Integer> obterAutoresFilme(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_AUTOR_FILME);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next())
				do{
					codigos.add(resultSet.getInt(1));
				}while(resultSet.next()); 
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigos;
	}//obterAutoresFilme()
	
	/**
	 * Obtém a lista de todos os filmes de um determinado autor.
	 * @param codigo <code>int</code> com o código do autor que se deseja obter os filmes.
	 * @return <code>List</code> com os códigos dos filmes que o autor informado escreveu.
	 */
	public static List<Integer> obterFilmesAutor(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_FILMES_AUTOR);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next())
				do{
					 codigos.add(resultSet.getInt(1));
				}while(resultSet.next());
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigos;
	}//obterFilmesAutor()
}//class AutorFilmeDAO
