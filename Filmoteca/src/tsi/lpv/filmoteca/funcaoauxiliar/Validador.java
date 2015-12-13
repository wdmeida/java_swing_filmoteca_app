package tsi.lpv.filmoteca.funcaoauxiliar;

/** Essa Classe e reponsavel implementar métodos que valida dados do programa.
 * @author Samuel e Wagner
 *
 */
public class Validador {
	
	/**
	 * Verifica se a <code>String</code> passada e um número inteiro positivo.
	 * @param numero <code>String</code> que será usada para a validação.
	 * @return Retorna true <code>boolean</code> para número inteiro e false <code>boolean</code>
	 * para outro tipo de caracter.
	 */
	public static boolean validaNumeroInteiro(String numero) {
		try {
			if(Integer.parseInt(numero) <= 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a <code>String</code> passada e um número real Positivo.
	 * @param numero <code>String</code> que será usada para a validação.
	 * @return Retorna true <code>boolean</code> para número real e false <code>boolean</code>
	 * para outro tipo de caracter.
	 */
	public static boolean validaNumeroReal(String numero) {
		try {
			if(Double.parseDouble(numero) <= 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a <code>String</code> passada esta vazia.
	 * @param texto <code>String</code> que será usada para a validação.
	 * @return Retorna true <code>boolean</code> para <code>String</code> vazia false <code>boolean</code>
	 * caso contrario.
	 */
	public static boolean validaCampoVazio(String texto) {
		if(texto.equals(""))
			return true;
		return false;
	}
	
	/**Procura a ocorrencia de um texto em um array de <code>String</code>.
	 * @param texto <code>String</code> a ser comparado.
	 * @param textos array de <code>String</code> a ser comparado.
	 * @return um <code>boolean</code> informando se e ou não igual.
	 */
	public static boolean procuraIgualdede(String texto, String[] textos){
		for(String palavra : textos)
			if(FuncaoAuxiliar.comparaString(texto, palavra))
				return true;
		return false;
	}
}
