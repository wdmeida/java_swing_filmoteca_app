package tsi.lpv.filmoteca.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
/**
 * A classe <code>IgSobre</code> constrói a janela com informações sobre o aplicativo.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgSobre extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe <code>IgSobre</code>.
	 * @param igFilmoteca <code>IgFilmoteca</code> com a referência da janela principal.
	 */
	public IgSobre(IgFilmoteca igFilmoteca) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgSobre.class.getResource("/tsi/lpv/filmoteca/imagens/Darth_Vader_Mask_64.png")));
		//Define as propriedades da janela.
		setTitle("Darth Movies");
		setSize(460, 342);
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(new Color(17,17,17));
		getContentPane().setLayout(null);
		
		//Cria o painel principal da janela.
		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 454, 201);
		panel.setBackground(new Color(17,17,17));
		panel.setBorder(new TitledBorder(null, "Sobre", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		setLocationRelativeTo(igFilmoteca);
		
		//Cria a área de texto para exibição do conteúdo.
		JTextArea sobreTextArea = new JTextArea();
		sobreTextArea.setText("Aplicativo desenvolvido como trabalho final da disciplina de Linguagem de Programa\u00E7\u00E3o Visual, do curso Superior de Tecnologia em Sistemas para Internet, ministrado no Instituto Federal do Sudeste de Minas Gerais - campus Barbacena.\r\n\r\nAlunos:\t Samuel Gon\u00E7alves\r\n\t Wagner Almeida\r\n\r\nProfessor: M\u00E1rlon");
		sobreTextArea.setForeground(Color.WHITE);
		sobreTextArea.setBackground(new Color(17,17,17));
		sobreTextArea.setWrapStyleWord(true);
		sobreTextArea.setLineWrap(true);
		sobreTextArea.setBounds(10, 11, 434, 185);
		panel.add(sobreTextArea);
		
		JLabel lblBancadaNegraAcima = new JLabel("Bancada Negra - TSI 2015");
		lblBancadaNegraAcima.setBounds(135, 276, 178, 27);
		getContentPane().add(lblBancadaNegraAcima);
		lblBancadaNegraAcima.setForeground(Color.WHITE);
		lblBancadaNegraAcima.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(IgSobre.class.getResource("/tsi/lpv/filmoteca/imagens/Cinema-icon.png")));
		lblNewLabel.setBounds(10, 21, 46, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(IgSobre.class.getResource("/tsi/lpv/filmoteca/imagens/Popcorn_from_cinema_24.png")));
		label.setBounds(411, 21, 33, 32);
		getContentPane().add(label);
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgSobre.this.dispose();
			}
		});
		
		setVisible(true);
	}//IgSobre
}//class IgSobre
