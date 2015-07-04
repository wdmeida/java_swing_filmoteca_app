package tsi.lpv.samuelwagner.tipo;
/**
 * A classe abstrata <code>Dados</code> possui os atributos e métodos que são comuns as outras classes
 * da filmoteca. Ela é definida para que as demais operações do aplicativo sejam executadas de forma
 * polimórfica.
 * 
 * @author Samuel
 * @author Wagner
 */
public /*abstract*/ class Dados {
	private int codigo;
	private String nome;
	
	/**
	 * Construtor default da classe abstrata dados.
	 */
	public Dados() {}
	
	/**
	 * Obtém um <code>int</code> contendo o código inserido.
	 * @return codigo <code>int</code>.
	 */
	public int getCodigo() {
		return codigo;
	}//getCodigo()
	
	/**
	 * Atribui um o código.
	 * @param codigo <code>int</code> com o código.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}//setCodigo()
	
	/**
	 * Obtém um <code>String</code> com o nome.
	 * @return nome <code>String</code>
	 */
	public String getNome() {
		return nome;
	}//getNome()
	
	/**
	 * Atribui o nome.
	 * @param nome <code>String</code> com o nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}//setNome()
}//abstract class Dados
