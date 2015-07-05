package tsi.lpv.samuelwagner.funcaoauxiliar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * A classe <code>MetodosConversaoBanco</code> possui os atributos e métodos auxiliares responsáveis por
 * realizar as manipulações das funções do banco de dados.
 * 
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class MetodosConversaoBanco {
	private static final String DIR_TEMP = "tmp";
	
	/**
	 * Converte o objeto <code>File</code> recebido por parâmetro para um array de bytes, para que seja
	 * salvo no banco de dados.
	 * @param file <code>File</code> com o arquivo a ser convetido.
	 * @return <code>byte[]</code> com os dados preparados para serem persistidos.
	 */
	public static byte[] preparaImagemParaBanco(File file){
		try {
			//Converte o objeto file em um array de bytes para poder ser inserido no banco.
			if(file.exists()){

				@SuppressWarnings("resource")
				InputStream imagem = new FileInputStream(file);
				byte[] bytes = new byte[(int)file.length()];
				int offset = 0, numRead = 0;
			
				while(offset < bytes.length && (numRead = imagem.read(bytes, offset, bytes.length - offset)) >= 0)
					offset += numRead;
			
				return bytes;
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}//preparaImagemParaBanco()
	
	/**
	 * Reconstrói os dados em bytes recebidos para um diretório temporário na pasta do arquivo.
	 * @param bytes <code>byte[]</code> com os dados a serem reconstruídos.
	 * @param nome <code>String</code> com o nome do arquivo.
	 * @return <code>File</code> com o endereço do arquivo reconstruído.
	 */
	public static File reconstroiImagemDoBanco(byte[] bytes, String nome){
		File f = new File(DIR_TEMP + File.separator + nome);
		 
		try {
			if(bytes != null){
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(bytes);
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}//reconstroiImagemDoBanco()
}//MetodosConversaoBanco
