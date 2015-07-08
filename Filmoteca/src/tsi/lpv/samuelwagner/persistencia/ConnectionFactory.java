package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A classe <code>ConnectionFactory</code> obtém uma conexão com o banco de dados do aplicativo Filmoteca.
 *  
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class ConnectionFactory {
	private static Connection connection; //Conexão a ser gerada.
	private static final String driverBD = "jdbc:postgresql:", //endereço do banco	
								nomeDB = "filmoteca", //nomeBD
								user = "aluno", //Usuário
								password = "aluno"; //Senha 
	
	/**
	 * O método <code>getConnection</code> verifica se existe uma conexão válida ao banco, caso não houver, cria
	 * uma nova conexão e retorna ao método que requisitou uma conexão. As senhas estão encapsuladas dentro da classe
	 * para evitar que o usuário possa modificá-la.
	 * @return <code>Connection</code>
	 */
	public static Connection getConnection() {
		if(connection != null) return connection;
		else
			try {
				connection = DriverManager.getConnection(driverBD + nomeDB, user, password);
				return connection;
			} catch (SQLException e) {
				System.out.println("Erro ao conectar ao banco de dados");
				throw new RuntimeException();
			}
	}//getConnection
	
	/**
	 * Encerra a conexão com o banco caso esta esteja aperta.
	 */
	public static void closeConnection() {
		try {
			if(connection != null) connection.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}//closeConnection
}//class SGDB
