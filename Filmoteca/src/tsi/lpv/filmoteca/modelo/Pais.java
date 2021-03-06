package tsi.lpv.filmoteca.modelo;

/**
 * A classe <code>Pais</code> possui as informa��es b�sicas sobre o pa�s de produ��o do filme.
 * 
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class Pais extends Dados {
	/**
	 * Construtor default da classe <code>Pais</code>.
	 */
	public Pais() {
		super();
	}
	
	/**
	 * Construtor sobrecarregado da classe <code>Pais</code>.
	 * @param codigo <code>int</code> do Pais.
	 * @param nome  <code>String</code> nome do Pais.
	 */
	public Pais(int codigo, String nome) {
		super(codigo,nome);
	}
}//class Pais
