package tsi.lpv.filmoteca.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
/**
 * A classe <code>IgSobre</code> constrói a janela de ajuda do aplicativo.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgAjuda extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe <code>IgAjuda</code>.
	 * @param igFilmoteca <code>IgFilmoteca</code> com a referência da janela principal.
	 * @param modulo <code>String</code> com o título do módulo de ajuda que está sendo executado.
	 * @param mensagemAjuda <code>String</code> com a mensagem de ajuda ao usuário que será exibida.
	 */
	public IgAjuda(IgCadastrarFilme igCadastrarFilme, String modulo, String mensagemAjuda) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgAjuda.class.getResource("/tsi/lpv/filmoteca/imagens/Darth_Vader_Mask_64.png")));
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
		panel.setBorder(new TitledBorder(null, modulo, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		setLocationRelativeTo(igCadastrarFilme);
		
		//Cria a área de texto para exibição do conteúdo.
		JTextArea sobreTextArea = new JTextArea();
		sobreTextArea.setText(mensagemAjuda);
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
		lblNewLabel.setIcon(new ImageIcon(IgAjuda.class.getResource("/tsi/lpv/filmoteca/imagens/Cinema-icon.png")));
		lblNewLabel.setBounds(10, 21, 46, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(IgAjuda.class.getResource("/tsi/lpv/filmoteca/imagens/Popcorn_from_cinema_24.png")));
		label.setBounds(411, 21, 33, 32);
		getContentPane().add(label);
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgAjuda.this.dispose();
			}
		});
		
		setVisible(true);
	}//IgSobre
}//class IgSobre
