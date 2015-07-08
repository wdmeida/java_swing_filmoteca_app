package tsi.lpv.samuelwagner.app;

import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tsi.lpv.samuelwagner.gui.IgEsperaInicial;
import tsi.lpv.samuelwagner.gui.IgFilmoteca;

/**Classe <code>FilmotecaApp</code> responsavel por inicar a execução do Aplicativo Darth Movie.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class FilmotecaApp {

	/**
	 * Método main, responsável pela inicialização do aplicativo de Darth Movie.
	 * @param args <code>String</code> argumentos.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		IgFilmoteca filmoteca = new IgFilmoteca();
		new ThreadIniciaAplicativo(new IgEsperaInicial(),filmoteca).start();
	}//testa

	//Classe Responsavel por Gerar o pequeno Atraso para abrir a conexão com o banco.
	private static class ThreadIniciaAplicativo extends Thread{
		private IgEsperaInicial igEspera;
		private IgFilmoteca igFilmoteca;
		
		//Construtor default da classe ThreadIniciaAplicativo
		private ThreadIniciaAplicativo(IgEsperaInicial igEspera, IgFilmoteca igFilmoteca) {
			this.igEspera = igEspera;
			this.igFilmoteca = igFilmoteca;
		}

		@Override
		public void run() {
			Clip clip = null;
			try {  
			    AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream(Thread.currentThread().getClass().getResourceAsStream("/tsi/lpv/samuelwagner/som/StarWarsDarthVaderTheme.wav")));  
			    clip = AudioSystem.getClip();  
			    clip.open(sound);  
			    clip.start();  
			} catch (Exception e) {  
			   e.printStackTrace();
			}  
			//Loop para fazer o ProgressBar avançar.
			for(int i = 0; i<= 100; i++){
				try {
					;;//Seta o valor do ProgressBar
					igEspera.getProgressBar().setValue(i);
					//Provaca o Atraso.
					sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			clip.stop();
			//Fecha a Janela igEspera
			igEspera.dispose();
			//Ativa a janela IgFilmoteca.
			igFilmoteca.setVisible(true);
		}
	}
}
