package tsi.lpv.filmoteca.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
/**
 * A classe <code>IgMensagem</code> constrói a janela gráfica responsável por exibir mensagens do usuário.
 * 
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 */
public class IgMensagem extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color corBase = new Color(17,17,17);
	/**
	 * Construtor da classe <code>IgMensagem</code>.
	 * @param componente <code>Component</code> com a referência da janela sobre a qual a janela de mensagem sera exibida.
	 * @param mensagem <code>String</code> com a mensagem a ser exibida.
	 */
	public IgMensagem(Component componente, String mensagem) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgMensagem.class.getResource("/tsi/lpv/filmoteca/imagens/Darth_Vader_Mask_64.png")));
		//Define o tamanho da janela.
		setSize(385, 186);
		
		//Define a cor do painel.
		getContentPane().setBackground(corBase);
		
		//Define o título da janela.
		setTitle("Darth Movie");
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgMensagem.this.dispose();
			}
		});
		
		//Define a janela como não redimensionável.
		setResizable(false);
		
		//Define a janela como modal
		setModal(true);
		getContentPane().setLayout(null);
		
		//Cria o painel de exibição da mensagem.
		JPanel mensagemPanel = new JPanel();
		mensagemPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Darth Movie Informa", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
		mensagemPanel.setBackground(corBase);
		mensagemPanel.setBounds(10, 11, 359, 139);
		getContentPane().add(mensagemPanel);
		mensagemPanel.setLayout(null);
		
		//Cria o botão de confirmação para fechar a caixa de mensagem.
		JButton btnOk = new JButton("OK");
		btnOk.setMnemonic(KeyEvent.VK_O);
		btnOk.setIcon(new ImageIcon(IgMensagem.class.getResource("/tsi/lpv/filmoteca/imagens/video.png")));
		btnOk.setForeground(Color.BLACK);
		btnOk.setBackground(Color.WHITE);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IgMensagem.this.dispose();
			}
		});
		
		btnOk.setBounds(122, 96, 114, 32);
		mensagemPanel.add(btnOk);
		
		//Cria o label de mensagem
		JLabel mensagemLabel = new JLabel(mensagem);
		mensagemLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		mensagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mensagemLabel.setForeground(Color.WHITE);
		mensagemLabel.setBounds(10, 57, 339, 16);
		mensagemPanel.add(mensagemLabel);
		
		//Cria o icone da janela.
		JLabel dartMalLabel = new JLabel("");
		dartMalLabel.setIcon(new ImageIcon(IgMensagem.class.getResource("/tsi/lpv/filmoteca/imagens/Cinema-icon.png")));
		dartMalLabel.setBounds(10, 11, 38, 40);
		mensagemPanel.add(dartMalLabel);
		
		//Cria o icone do batman
		JLabel batmanLabel = new JLabel("");
		batmanLabel.setIcon(new ImageIcon(IgMensagem.class.getResource("/tsi/lpv/filmoteca/imagens/Film_strip_24.png")));
		batmanLabel.setBounds(318, 11, 31, 23);
		mensagemPanel.add(batmanLabel);
		
		//Cria o icone da foto.
		JLabel iconFoto = new JLabel("");
		iconFoto.setIcon(new ImageIcon(IgMensagem.class.getResource("/tsi/lpv/filmoteca/imagens/keditbookmarks.png")));
		iconFoto.setBounds(37, 84, 38, 32);
		mensagemPanel.add(iconFoto);
		
		JLabel fotoLabel = new JLabel("");
		fotoLabel.setIcon(new ImageIcon(IgMensagem.class.getResource("/tsi/lpv/filmoteca/imagens/Theater_ticket_24.png")));
		fotoLabel.setBounds(287, 96, 38, 20);
		mensagemPanel.add(fotoLabel);
		
		//Define o posicionamento da janela.
		setLocationRelativeTo(componente);
		
		//Define a janela como visivel.
		setVisible(true);
	}//IgMensagem
}//class IgMensagem
