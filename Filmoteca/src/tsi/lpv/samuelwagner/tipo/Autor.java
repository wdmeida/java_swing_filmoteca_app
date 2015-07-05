package tsi.lpv.samuelwagner.tipo;

/**
 * A classe <code>Autor</code> possui as informações básicas dos autores do filme.
 * 
 * @author Samuel
 * @author Wagner
 */
public class Autor extends Dados {
	/**
	 * Construtor default da classe <code>Autor</code>.
	 */
	public Autor() {
		super();
	}
	
	/**
	 * Construtor sobrecarregado da classe <code>Autor</code>.
	 */
	public Autor(int codigo, String nome) {
		super(codigo,nome);
	}
}//class Autor 
