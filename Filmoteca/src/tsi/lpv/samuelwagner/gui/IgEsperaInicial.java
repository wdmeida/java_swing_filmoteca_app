package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**Classe <code>IgEsperaInicial</code> reponsavel pela criação da Tela inicial do Darth Movie.
 * @author Samuel
 * @author Wagner
 */
public class IgEsperaInicial extends JDialog {
	private Color corBase =  new Color(17, 17, 17);
	private JProgressBar progressBar;
	
	/**
	 * Construtor Default da Classe.
	 */
	public IgEsperaInicial() {
		setUndecorated(true);
		setVisible(true);
		setFocusTraversalPolicyProvider(true);
		setBounds(100, 100, 422, 388);
		setBackground(corBase);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(corBase);
		progressBar.setForeground(Color.BLACK);
		progressBar.setString("Carregando Banco de Dados...");
		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 335, 432, 53);
		getContentPane().add(progressBar);
		
		JPanel darthPanel = new JPanel();
		darthPanel.setBounds(0, 0, 422, 336);
		getContentPane().add(darthPanel);
		darthPanel.setLayout(null);
		
		JLabel darthLabel = new JLabel("");
		darthLabel.setBounds(0, 0, 428, 336);
		darthPanel.add(darthLabel);
		darthLabel.setIcon(new ImageIcon(IgEsperaInicial.class.getResource("/tsi/lpv/samuelwagner/imagens/darth_vader_by_tomasgarcia.jpg")));
	}

	/**Retorna a referência da Barra de Progresso.
	 * @return um <code>ProgressBar</code>.
	 */
	public JProgressBar getProgressBar() {
		return progressBar;
	}

}
