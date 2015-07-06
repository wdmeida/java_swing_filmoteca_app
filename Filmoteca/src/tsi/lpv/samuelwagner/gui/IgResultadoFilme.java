package tsi.lpv.samuelwagner.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import javax.swing.JList;
/**
 * A classe <code>IgResultadoFilme</code> constrói a janela gráfica responsável por exibir os resultados da busca 
 * por um filme, caso este esteja cadastrado no banco de dados.
 * 
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 */
public class IgResultadoFilme extends JDialog {
	private Color corBase = new Color(17,17,17);
	private Color corLista = new Color(114,124,115);
	private JLabel anoLabel;
	private JLabel duracaoLabel;
	private JLabel etariaLabel;
	private JLabel generoLabel;
	private JLabel nomeFilme;
	private JPanel fotoPanel;
	private JTextArea sinopseTextArea;
	/**
	 * Construtor da classe <code>IgResultadoFilme</code>.
	 */
	public IgResultadoFilme() {
		//Define o tamanho da janela.
		setSize(781, 529);
		
		getContentPane().setBackground(corBase);
		
		//Define o título da janela.
		setTitle("Filme");
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgResultadoFilme.this.dispose();
			}
		});
		
		//Define a janela como não redimensionável.
		setResizable(false);
		
		//Define a janela como modal
		setModal(true);
		getContentPane().setLayout(null);
		
		fotoPanel = new JPanel();
		fotoPanel.setBorder(new TitledBorder(null, "Imagem", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		
		fotoPanel.setBackground(corBase);
		fotoPanel.setBounds(10, 11, 135, 200);
		getContentPane().add(fotoPanel);
		
		nomeFilme = new JLabel("T\u00EDtulo");
		nomeFilme.setForeground(Color.WHITE);
		nomeFilme.setFont(new Font("Tahoma", Font.BOLD, 14));
		nomeFilme.setBounds(155, 11, 175, 22);
		getContentPane().add(nomeFilme);
		
		JPanel adicionalPanel = new JPanel();
		adicionalPanel.setBackground(corBase);
		adicionalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informa\u00E7\u00F5es adicionais", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		adicionalPanel.setBounds(10, 222, 755, 268);
		getContentPane().add(adicionalPanel);
		adicionalPanel.setLayout(null);
		
		JPanel dataLancamentoPanel = new JPanel();
		dataLancamentoPanel.setBounds(10, 31, 735, 28);
		dataLancamentoPanel.setBackground(corLista);
		adicionalPanel.add(dataLancamentoPanel);
		dataLancamentoPanel.setLayout(null);
		
		JLabel dataLabel = new JLabel("Data de Lan\u00E7amento:");
		dataLabel.setBounds(10, 7, 103, 14);
		dataLancamentoPanel.add(dataLabel);
		dataLabel.setForeground(Color.WHITE);
		
		JPanel midiaPanel = new JPanel();
		midiaPanel.setBackground(corBase);
		midiaPanel.setBounds(10, 58, 735, 28);
		adicionalPanel.add(midiaPanel);
		midiaPanel.setLayout(null);
		
		JLabel midiaLabel = new JLabel("M\u00EDdia:");
		midiaLabel.setBounds(10, 7, 28, 14);
		midiaPanel.add(midiaLabel);
		midiaLabel.setForeground(Color.WHITE);
		
		JPanel pessoalPanel = new JPanel();
		pessoalPanel.setBackground(corLista);
		pessoalPanel.setBounds(10, 86, 735, 28);
		adicionalPanel.add(pessoalPanel);
		pessoalPanel.setLayout(null);
		
		JLabel pessoalLabel = new JLabel("Classifica\u00E7\u00E3o Pessoal:");
		pessoalLabel.setBounds(10, 7, 116, 14);
		pessoalPanel.add(pessoalLabel);
		pessoalLabel.setForeground(Color.WHITE);
		
		JPanel imdbPanel = new JPanel();
		imdbPanel.setBackground(corBase);
		imdbPanel.setBounds(10, 114, 735, 28);
		adicionalPanel.add(imdbPanel);
		imdbPanel.setLayout(null);
		
		JLabel imdbLabel = new JLabel(" IMDB:");
		imdbLabel.setBounds(10, 7, 32, 14);
		imdbPanel.add(imdbLabel);
		imdbLabel.setForeground(Color.WHITE);
		
		JPanel direcaoPanel = new JPanel();
		direcaoPanel.setBackground(corLista);
		direcaoPanel.setBounds(10, 142, 735, 28);
		adicionalPanel.add(direcaoPanel);
		direcaoPanel.setLayout(null);
		
		JLabel direcaoLabel = new JLabel("Dire\u00E7\u00E3o:");
		direcaoLabel.setBounds(10, 7, 49, 14);
		direcaoPanel.add(direcaoLabel);
		direcaoLabel.setForeground(Color.WHITE);
		
		JPanel autorPanel = new JPanel();
		autorPanel.setBackground(corBase);
		autorPanel.setBounds(10, 168, 735, 28);
		adicionalPanel.add(autorPanel);
		autorPanel.setLayout(null);
		
		JLabel autorLabel = new JLabel("Autor:");
		autorLabel.setBounds(10, 7, 49, 14);
		autorPanel.add(autorLabel);
		autorLabel.setForeground(Color.WHITE);
		
		JPanel elencoPanel = new JPanel();
		elencoPanel.setBackground(corLista);
		elencoPanel.setBounds(10, 196, 735, 61);
		adicionalPanel.add(elencoPanel);
		elencoPanel.setLayout(null);
		
		JLabel lblElenco = new JLabel("Elenco:");
		lblElenco.setForeground(Color.WHITE);
		lblElenco.setBounds(10, 11, 46, 14);
		elencoPanel.add(lblElenco);
		
		JPanel sinopsePanel = new JPanel();
		sinopsePanel.setBackground(corBase);
		sinopsePanel.setBorder(new TitledBorder(null, "Sinopse", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		sinopsePanel.setBounds(155, 66, 610, 145);
		getContentPane().add(sinopsePanel);
		sinopsePanel.setLayout(null);
		
		JScrollPane sinopseScrollPane = new JScrollPane();
		sinopseScrollPane.setBounds(10, 17, 590, 117);
		sinopsePanel.add(sinopseScrollPane);
		
		sinopseTextArea = new JTextArea();
		sinopseTextArea.setForeground(Color.WHITE);
		sinopseTextArea.setBackground(corBase);
		sinopseTextArea.setEditable(false);
		sinopseScrollPane.setViewportView(sinopseTextArea);
		
		anoLabel = new JLabel("Ano:");
		anoLabel.setBounds(155, 41, 46, 14);
		getContentPane().add(anoLabel);
		anoLabel.setForeground(Color.WHITE);
		
		duracaoLabel = new JLabel("Dura\u00E7\u00E3o:");
		duracaoLabel.setBounds(240, 41, 64, 14);
		getContentPane().add(duracaoLabel);
		duracaoLabel.setForeground(Color.WHITE);
		
		etariaLabel = new JLabel("Classifica\u00E7\u00E3o Et\u00E1ria:");
		etariaLabel.setBounds(360, 41, 102, 14);
		getContentPane().add(etariaLabel);
		etariaLabel.setForeground(Color.WHITE);
		
		generoLabel = new JLabel("G\u00EAnero:");
		generoLabel.setForeground(Color.WHITE);
		generoLabel.setBounds(573, 41, 46, 14);
		getContentPane().add(generoLabel);
		
		//Define a janela como visivel.
		setVisible(true);
	}//IgResultadoFilme()
}//class IgResultadoFilme
