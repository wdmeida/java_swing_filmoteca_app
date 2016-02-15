package tsi.lpv.filmoteca.funcaoauxiliar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A classe <code>ObtemPoster</code> é responsável por receber o endereço da imagem e retorna-lá em um
 * array de bytes para que possa ser armazenada no banco de dados da aplicação.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class ObtemPoster {
	
	/**
	 * Obtém a imagem convertida em um array de bytes através de sua url.
	 * @param urlPoster <code>String</code> com o endereço da imagem.
	 * @return um array de <code>byte</code> com os dados da imagem.
	 */
	public static byte[] obtemPoster(String urlPoster){
		//Atribui a url de conexão para buscar o poster do filme.
		URL url;
			try {
				url = new URL(urlPoster);
			
		
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				InputStream inputStream = url.openStream ();
				byte[] poster = new byte[80000]; // Or whatever size you want to read in at a time.
				int n;
	
				while ( (n = inputStream.read(poster)) > 0 ) byteArrayOutputStream.write(poster, 0, n);
				 
				return byteArrayOutputStream.toByteArray();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}//obtemPoster()
}//class ObtemPoster
