package tsi.lpv.filmoteca.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tsi.lpv.filmoteca.trataeventos.RespostaEventoPesquisaElemento;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
/**
 * A classe <code>IgPesquisa</code> é responsável por construir a interface gráfica de pesquisa do aplicativo
 * Filmoteca.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgTelaPesquisa extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField pesquisaTextField;
	private Color corBase = new Color(17,17,17);

	/**
	 * Construtor da classe <code>IgPesquisar</code> responsável por construir a interface gráfica da janela de pesquisa.
	 * @param tipoPesquisa <code>String</code> com o tipo da pesquisa.
	 * @param mensagem <code>String</code> com a mensagem da pesquisa.
	 * @param igFilmoteca <code>IgFilmoteca</code> com a referência da tela principal.
	 * @param botaoChamada <code>JButton</code> com a referência do botão que disparou o evento.
	 */
	public IgTelaPesquisa(String tipoPesquisa, String mensagem, IgFilmoteca igFilmoteca, JButton botaoChamada) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgTelaPesquisa.class.getResource("/tsi/lpv/filmoteca/imagens/Cinema-icon.png")));
		setSize(443,222);
		setTitle(tipoPesquisa);
		
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(corBase);
		
		setLocationRelativeTo(igFilmoteca);
		
		//Cria o painel de pesquisa.
		JPanel pesquisaPanel = new JPanel();
		pesquisaPanel.setBackground(corBase);
		pesquisaPanel.setBorder(new TitledBorder(null, tipoPesquisa, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pesquisaPanel.setBounds(10, 11, 418, 172);
		getContentPane().add(pesquisaPanel);
		pesquisaPanel.setLayout(null);
		
		//Cria o rótulo da pesquisa.
		JLabel pesquisaLabel = new JLabel(mensagem);
		pesquisaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		pesquisaLabel.setForeground(Color.WHITE);
		pesquisaLabel.setBounds(10, 63, 120, 14);
		pesquisaPanel.add(pesquisaLabel);
		
		setModal(true);
		
		//Cria o campo de texto para realizar a pesquisa.
		pesquisaTextField = new JTextField();
		pesquisaTextField.setBounds(140, 60, 268, 20);
		pesquisaPanel.add(pesquisaTextField);
		pesquisaTextField.setColumns(10);
		
		//Cria o botão pesquisar e atribui suas propriedades.
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setMnemonic(KeyEvent.VK_P);
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPesquisar.setIcon(new ImageIcon(IgTelaPesquisa.class.getResource("/tsi/lpv/filmoteca/imagens/Watching_a_video_on_a_tablet_16.png")));
		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setBackground(Color.BLACK);
		btnPesquisar.setBorder(null);
		btnPesquisar.setBounds(178, 128, 110, 33);
		pesquisaPanel.add(btnPesquisar);
		
		//Registra o tratador de eventos do botão pesquisar.
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RespostaEventoPesquisaElemento(igFilmoteca, pesquisaTextField.getText(), botaoChamada).pesquisa();
				if(!pesquisaTextField.getText().equals(""))
					IgTelaPesquisa.this.dispose();
			}
		});
		
		//Cria o botão cancelar.
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setIcon(new ImageIcon(IgTelaPesquisa.class.getResource("/tsi/lpv/filmoteca/imagens/Film_reel_circular_shape_16.png")));
		btnCancelar.setBackground(Color.BLACK);
		btnCancelar.setBorder(null);
		btnCancelar.setBounds(298, 128, 110, 33);
		pesquisaPanel.add(btnCancelar);
		
		//Registra o tratador de eventos do botão pesquisar.
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IgTelaPesquisa.this.dispose();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 110, 398, 2);
		pesquisaPanel.add(separator);
		
		//Cria os label para inserir as imagens.
		JLabel popLabel = new JLabel("");
		popLabel.setIcon(new ImageIcon(IgTelaPesquisa.class.getResource("/tsi/lpv/filmoteca/imagens/Popcorn_from_cinema_24.png")));
		popLabel.setBounds(360, 11, 48, 38);
		pesquisaPanel.add(popLabel);
		
		JLabel cineLabel = new JLabel("");
		cineLabel.setIcon(new ImageIcon(IgTelaPesquisa.class.getResource("/tsi/lpv/filmoteca/imagens/3d_boy.png")));
		cineLabel.setBounds(10, 128, 39, 33);
		pesquisaPanel.add(cineLabel);
		
		JLabel ticketLabel = new JLabel("");
		ticketLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ticketLabel.setIcon(new ImageIcon(IgTelaPesquisa.class.getResource("/tsi/lpv/filmoteca/imagens/Cinema_date_day_calendar_page_24.png")));
		ticketLabel.setBounds(10, 11, 32, 41);
		pesquisaPanel.add(ticketLabel);
		
		//Registra o tratador de eventos da janela de pesquisa.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgTelaPesquisa.this.dispose();
			}
		});
		
		setVisible(true);
	}
}//class IgPesquisa
