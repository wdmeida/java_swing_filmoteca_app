package tsi.lpv.samuelwagner.tipo;

/**
 * A classe <code>Pais</code> possui as informações básicas sobre o país de produção do filme.
 * 
 * @author Samuel
 * @author Wagner
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
	 */
	public Pais(int codigo, String nome) {
		super(codigo,nome);
	}
}//class Pais
