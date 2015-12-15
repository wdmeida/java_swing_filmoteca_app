package tsi.lpv.filmoteca.funcaoauxiliar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * A classe <code>ObtemPoster</code>
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class ObtemPoster {
	
	public static byte[] obtemPoster(String urlPoster){
		//Atribui a url de conexão para buscar o poster do filme.
				URL url;
				try {
					url = new URL(urlPoster);
				
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					InputStream is = url.openStream ();
					byte[] poster = new byte[80000]; // Or whatever size you want to read in at a time.
					int n;
	
					while ( (n = is.read(poster)) > 0 ) baos.write(poster, 0, n);
					 
					return baos.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
	}
}
