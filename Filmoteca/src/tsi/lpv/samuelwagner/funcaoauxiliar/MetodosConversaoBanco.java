package tsi.lpv.samuelwagner.funcaoauxiliar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
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
	
	/**
	 * Redimenciona uma imagem para um tamanho específico para que possa ser salva no banco.
	 * @param file <code>File</code> com a imagem a ser redimensionada.
	 * @param width <code>int</code> com a largura da imagem.
	 * @param heigth <code>int</code> com a altura da imagem.
	 * @param formato <code>String</code>.
	 * @return <code>File</code> com a nova imagem a ser inserida ou <code>null</code> caso não seja
	 * 			possível redimensionar.
	 */
	public static File redimensionarImagem(File file, int width, int heigth, String formato){
		try {
			//Lê a imagem original para o buffer.
			BufferedImage original = ImageIO.read(file);
			
			//Cria uma imagem redimensionada com altura e largura específicada.
			BufferedImage redimensionada = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
			
			//Redimensiona a imagem original na imagem redimensionada.
			redimensionada.getGraphics().drawImage(original, 0, 0, width, heigth, null);
			new File(DIR_TEMP + File.separator).mkdirs();
			//Salva a imagem em um arquivo.
			ImageIO.write(redimensionada, formato, new File(DIR_TEMP + File.separator + file.getName()));
			return new File(DIR_TEMP + File.separator + file.getName());
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}//redimensionarImagem()
	
	/**
	 * Apaga o conteúdo da pasta temporária criada para redimensionar as imagens.
	 */
	public static void apagarPastaTemporaria(){
		File tmp = new File(DIR_TEMP);
		
		String arquivo[] = tmp.list();
	
		for(String arq : arquivo){
			new File(tmp + File.separator + arq).delete();
		}
	}//apagarPastaTemporaria()
}//MetodosConversaoBanco
