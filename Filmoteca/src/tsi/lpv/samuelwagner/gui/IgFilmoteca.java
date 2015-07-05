package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
/**
 * A classe <code>IgFilmoteca</code> é a responsável por construir a janela gráfica principal.
 * @author Wagner Almeida
 * @author Samuel Gonçalves.
 *
 */
public class IgFilmoteca extends JFrame {
	private Color corBase =  new Color(17, 17, 17);
	private Color corFonte = new Color(237,255,40);
	private Color corMenu = new Color(148,151,151);
	private Color corSubMenu = new Color(124,61,139);
	private JTextField pesquisarTextField;
	private JPanel exibicaoPanel;
	
	/**
	 * Construtor da classe IgFilmoteca.
	 */
	public IgFilmoteca() {
		//Define o nome do app
		super("Filmoteca Darth Vader");
		
		//Muda o icone da barra de títulos da janela. Cria um objeto url e passa o endereço da imagem.
		URL url = this.getClass().getResource("/tsi/lpv/samuelwagner/imagens/Darth_Vader_Mask_64.png");
	
		//Cria um objeto imagem, Obtém a referência da imagem da barra e seta a nova imagem.
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		
		//Define a nova imagem para a janela.
		this.setIconImage(imagemTitulo);
		
		//Muda a cor do painel.
		getContentPane().setBackground(corBase);
		getContentPane().setForeground(corFonte);
		getContentPane().setLayout(null);
		
		//Cria o painel lateral.
		JPanel panel = new JPanel();
		panel.setBackground(corBase);
		panel.setBounds(0, 108, 153, 305);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Cria o botão de cadastrar filme.
		JButton cadastrarFilmeButton = new JButton("Cadastrar Filme");
		cadastrarFilmeButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/video.png")));
		cadastrarFilmeButton.setBackground(Color.BLACK);
		cadastrarFilmeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cadastrarFilmeButton.setForeground(Color.WHITE);
		cadastrarFilmeButton.setToolTipText("Cadastrar Filmes");
		cadastrarFilmeButton.setBounds(0, 11, 153, 38);
		panel.add(cadastrarFilmeButton);
		
		//Cria o botão de procurar diretor.
		JButton diretorButton = new JButton("Buscar Diretor");
		diretorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/director_sit.png")));
		diretorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		diretorButton.setForeground(Color.WHITE);
		diretorButton.setBackground(Color.BLACK);
		diretorButton.setBounds(0, 158, 153, 38);
		panel.add(diretorButton);
		
		//Cria o botão de buscar por ator.
		JButton atorButton = new JButton("Buscar Ator");
		atorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/actordark.png")));
		atorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		atorButton.setBackground(Color.BLACK);
		atorButton.setForeground(Color.WHITE);
		atorButton.setBounds(0, 60, 153, 38);
		panel.add(atorButton);
		
		//Cria botão de busca por autor.
		JButton autorButton = new JButton("Buscar Autor");
		autorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/author.png")));
		autorButton.setBackground(Color.BLACK);
		autorButton.setForeground(Color.WHITE);
		autorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		autorButton.setBounds(0, 109, 153, 38);
		panel.add(autorButton);
		
		//Criar botão de busca por genêro.
		JButton btnGenro = new JButton("Filmes G\u00EAnero");
		btnGenro.setBounds(0, 207, 153, 38);
		panel.add(btnGenro);
		btnGenro.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/Hollywood_sign_24.png")));
		btnGenro.setBackground(Color.BLACK);
		btnGenro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGenro.setForeground(Color.WHITE);
		btnGenro.setToolTipText("Pesquisar por g\u00EAneros");
		
		//Criar botão de exibir por filmes melhor classificados.
		JButton rankingButton = new JButton("Ver Preferidos");
		rankingButton.setBounds(0, 256, 153, 38);
		panel.add(rankingButton);
		rankingButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/keditbookmarks.png")));
		rankingButton.setBackground(Color.BLACK);
		rankingButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		rankingButton.setForeground(Color.WHITE);
		rankingButton.setToolTipText("Visualizar filmes na ordem de prefer\u00EAncia.");
		
		//Cria o painel pra barra de pesquisa.
		JPanel pesquisarPanel = new JPanel();
		pesquisarPanel.setBackground(corSubMenu);
		pesquisarPanel.setBounds(0, 21, 839, 38);
		getContentPane().add(pesquisarPanel);
		pesquisarPanel.setLayout(null);
		
		//Instância o objeto responsável por conter os dados da pesquisa.
		pesquisarTextField = new JTextField();
		
		//Registra o tratador de eventos do jtextField.
		pesquisarTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pesquisarTextField.setText("");
			}
		});
		
		pesquisarTextField.setText("Pesquisar Filme");
		pesquisarTextField.setToolTipText("Pesquisar Filme");
		pesquisarTextField.setBounds(655, 9, 121, 20);
		pesquisarPanel.add(pesquisarTextField);
		pesquisarTextField.setColumns(10);
		
		//Cria o botão de pesquisa.
		JButton pesquisarButton = new JButton("");
		pesquisarButton.setBounds(786, 4, 43, 30);
		pesquisarPanel.add(pesquisarButton);
		pesquisarButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/Film_strip_24.png")));
		pesquisarButton.setBackground(corSubMenu);
		pesquisarButton.setBorderPainted(false);
		
		//Instância o painel de exibição.
		exibicaoPanel = new JPanel();
		exibicaoPanel.setBackground(corBase);
		exibicaoPanel.setBorder(new LineBorder(corMenu, 1, true));
		exibicaoPanel.setBounds(161, 70, 668, 387);
		getContentPane().add(exibicaoPanel);
		
		//Define a tela como não redimensionável.
		setResizable(false);
		
		setLocationByPlatform(true);
		
		//Define o tamanho da janela.
		setSize(845, 517);
		
		//Define a ação a ser tomada quando o botão fechar for apertado.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar principalMenuBar = new JMenuBar();
		principalMenuBar.setBackground(corMenu);
		setJMenuBar(principalMenuBar);
		
		JMenu arquivoMenu = new JMenu("Arquivo");
		arquivoMenu.setBackground(corBase);
		arquivoMenu.setForeground(Color.WHITE);
		principalMenuBar.add(arquivoMenu);
		
		JMenuItem apagarMenuItem= new JMenuItem("Apagar banco de dados");
		apagarMenuItem.setForeground(Color.WHITE);
		apagarMenuItem.setBackground(corBase);
		arquivoMenu.add(apagarMenuItem);
		
		//Define a tela como vísivel.
		setVisible(true);
	}//IgFilmoteca

	/**
	 * Obtém a referência da caixa de texto de pesquisa.
	 * @return <code>JTextField</code>
	 */
	public JTextField getPesquisarTextField() {
		return pesquisarTextField;
	}

	/**
	 * Retorna a referência do painel de exibição da tela principal onde serão realizadas as operações.
	 * @return <code>JPanel</code>
	 */
	public JPanel getExibicaoPanel() {
		return exibicaoPanel;
	}
}//class IgFilmoteca
