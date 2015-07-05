package tsi.lpv.samuelwagner.funcaoauxiliar;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;
import java.text.Collator;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
/**
 * A classe <code>FuncaoAuxiliar</code>
 * @author Samuel
 * @author Wagner
 */
public class FuncaoAuxiliar {
	private static String nomeMeses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho",
			"Agosto","Setembro","Outubro","Novembro","Desembro"};
	
	/** Converte um <code>Calendar</code> para <code>String</code>.
	 * @param data <code>Calendar</code>.
	 * @param completa <code>boolean</code>.
	 * @return um <code>String</code> formatada.
	 */
	public static String coverteDataParaString(Calendar data, boolean completa) {
		if(completa)
			return String.format("%02d/%02d/%04d", data.get(Calendar.DAY_OF_MONTH),
												   data.get(Calendar.MONTH)+1,
												   data.get(Calendar.YEAR));
		else
			return String.format("%02d/%04d", data.get(Calendar.MONTH)+1,
					   						  data.get(Calendar.YEAR));

	}//converteDataParaString()
	
	/**
	 * Converte a uma data em <code>String</code> para <code>Calendar</code>.
	 * @param data <code>String</code> com a data a ser convertida.
	 * @return um <code>Calendar</code> com a data ja convertida.
	 */
	public static Calendar converteDataParaCalendar(String data) {
		Calendar dataPesquisa = Calendar.getInstance();
		String aux[] = data.split(" ");
		
		int mes;
		//Obtém o indice da data.
		for(mes = 0; mes < nomeMeses.length; mes++) if(aux[0].equalsIgnoreCase(nomeMeses[mes])) break;		
		dataPesquisa.set(Integer.parseInt(aux[1]), mes, Calendar.DAY_OF_MONTH);
		
		return dataPesquisa;
	}
	
	/**
	 * @param componente
	 * @param Mensagem
	 * @param titulo
	 */
	public static void exibirMensagem(Component componente, String Mensagem, String titulo) {
		showMessageDialog(componente, Mensagem, titulo, INFORMATION_MESSAGE);
	}//exibirMensagem
	
	/**
	 * @param componente
	 * @param Mensagem
	 * @param titulo
	 */
	public static void exibirMensagemErro(Component componente, String Mensagem, String titulo) {
		showMessageDialog(componente, Mensagem, titulo, ERROR_MESSAGE);
	}//exibirMensagemErro
	
	/**
	 * Compara duas String e verifica se são iguais. Caso seja retorna true. Ignora acentuações.
	 * @param textoA <code>String</code> a ser comparada.
	 * @param textoB <code>String</code> a ser comparada.
	 * @return um <code>true</code> caso sejam iguais ou um <code>false</code> caso sejam diferentes.
	 */
	public static boolean comparaString(String textoA, String textoB) {
		 Collator collator = Collator.getInstance (new Locale("pt", "BR"));  
		 collator.setStrength(Collator.PRIMARY); // importante!  
		 return (collator.compare(textoA,textoB))== 0 ? true : false;
	}//comparaString()
	
	public static String[] obtemNomes(String texto) {
		StringTokenizer tokens = new StringTokenizer(texto, "\n\t");
		
		String[] nomes = new String[tokens.countTokens()];
		int indice = 0;
		while(tokens.hasMoreTokens())
			nomes[indice++] = tokens.nextToken();
		return nomes;
	}
	
	public static String juntaTexto(String[] textos, int numero) {
		String texto = "";
		for(int indice = 0; indice < numero;indice++)
			texto += textos[indice] + "\n";
		return texto;
	}
}//class FuncaoAuxiliar
