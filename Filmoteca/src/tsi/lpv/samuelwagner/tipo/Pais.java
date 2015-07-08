package tsi.lpv.samuelwagner.tipo;

/**
 * A classe <code>Pais</code> possui as informações básicas sobre o país de produção do filme.
 * 
 * @author Samuel Gonçalves
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
