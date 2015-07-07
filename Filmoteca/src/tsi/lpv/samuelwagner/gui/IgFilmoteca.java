package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.tipo.Filme;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoPesquisarFilme;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private Color corLista = new Color(114,124,115);
	private JTextField pesquisarTextField;
	private static JButton buscarFilmeButton;
	private JPanel exibicaoPanel;
	private JList <String> resultadoJList;
	private JScrollPane resultadoScrollPane;
	private JButton atorButton;
	private JButton autorButton;
	private JButton diretorButton;
	private JButton generoButton;
	private JButton rankingButton;
	
	/**
	 * Construtor da classe <code>IgFilmoteca</code> responsável pela interface gráfica do aplicativo Filmoteca.
	 */
	@SuppressWarnings("unchecked")
	public IgFilmoteca() {
		//Define o nome do app
		setTitle("Darth Movies");
		
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
		cadastrarFilmeButton.setMnemonic(KeyEvent.VK_C);
		cadastrarFilmeButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/video.png")));
		cadastrarFilmeButton.setBackground(Color.BLACK);
		cadastrarFilmeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cadastrarFilmeButton.setForeground(corSubMenu);
		cadastrarFilmeButton.setToolTipText("Cadastrar Filmes");
		cadastrarFilmeButton.setBounds(0, 11, 153, 38);
		panel.add(cadastrarFilmeButton);
		
		//Registra o tratador de eventos do botão cadastrar filme.
		cadastrarFilmeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarFilme(IgFilmoteca.this,true);
			}
		});
		
		//Cria o botão de procurar diretor.
		diretorButton = new JButton("Buscar Diretor");
		diretorButton.setMnemonic(KeyEvent.VK_D);
		diretorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/director_sit.png")));
		diretorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		diretorButton.setForeground(corSubMenu);
		diretorButton.setBackground(Color.BLACK);
		diretorButton.setBounds(0, 158, 153, 38);
		panel.add(diretorButton);
		
		//Registra o tratador de eventos do botão do diretor.
		diretorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes Diretor", "Nome do Diretor: ", IgFilmoteca.this, IgFilmoteca.this.diretorButton);
			}
		});
		
		//Cria o botão de buscar por ator.
		atorButton = new JButton("Buscar Ator");		
		atorButton.setMnemonic(KeyEvent.VK_A);
		atorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/actordark.png")));
		atorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		atorButton.setBackground(Color.BLACK);
		atorButton.setForeground(corSubMenu);
		atorButton.setBounds(0, 60, 153, 38);
		panel.add(atorButton);
		
		//Registra o tratador de eventos do artista.
		atorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes Artista", "Nome do artista: ", IgFilmoteca.this, IgFilmoteca.this.atorButton);
			}
		});
		
		//Cria botão de busca por autor.
		autorButton = new JButton("Buscar Autor");
		autorButton.setMnemonic(KeyEvent.VK_B);
		autorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/author.png")));
		autorButton.setBackground(Color.BLACK);
		autorButton.setForeground(corSubMenu);
		autorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		autorButton.setBounds(0, 109, 153, 38);
		panel.add(autorButton);
		
		//Registra o tratador de eventos do autor.
		autorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes Autor", "Nome do autor: ", IgFilmoteca.this, IgFilmoteca.this.autorButton);
			}
		});
		
		//Criar botão de busca por genêro.
		generoButton = new JButton("Filmes G\u00EAnero");
		generoButton.setMnemonic(KeyEvent.VK_G);
		generoButton.setBounds(0, 207, 153, 38);
		panel.add(generoButton);
		generoButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/Hollywood_sign_24.png")));
		generoButton.setBackground(Color.BLACK);
		generoButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		generoButton.setForeground(corSubMenu);
		generoButton.setToolTipText("Pesquisar por g\u00EAneros");
		
		//Registra o tratador de eventos do botão de busca de gêneros de filmes.
		generoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes pelo Gênero", "Gênero: ", IgFilmoteca.this, IgFilmoteca.this.generoButton);
			}
		});
		
		//Criar botão de exibir por filmes melhor classificados.
		rankingButton = new JButton("Ver Preferidos");
		rankingButton.setMnemonic(KeyEvent.VK_V);
		rankingButton.setBounds(0, 256, 153, 38);
		panel.add(rankingButton);
		rankingButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/keditbookmarks.png")));
		rankingButton.setBackground(Color.BLACK);
		rankingButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		rankingButton.setForeground(corSubMenu);
		rankingButton.setToolTipText("Visualizar filmes na ordem de prefer\u00EAncia.");

		//Registra o tratador de eventos do botão de exibição de classificação.
		rankingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					exibirClassificacao();		
			}
		});
		
		//Cria o painel pra barra de pesquisa.
		JPanel pesquisarPanel = new JPanel();
		pesquisarPanel.setBackground(corSubMenu);
		pesquisarPanel.setBounds(0, 11, 839, 48);
		getContentPane().add(pesquisarPanel);
		pesquisarPanel.setLayout(null);
		
		//Instância o objeto responsável por conter os dados da pesquisa.
		pesquisarTextField = new JTextField();
		pesquisarTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					new TratadorEventoPesquisarFilme(IgFilmoteca.this, pesquisarTextField.getText()).pesquisarFilme();
			}
		});
		
		//Registra o tratador de eventos do jtextField relativo ao teclado.
		pesquisarTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pesquisarTextField.setText("");
			}
		});
		
		//Cria o campo de pesquisa de texto.
		pesquisarTextField.setText("Pesquisar Filme");
		pesquisarTextField.setToolTipText("Pesquisar Filme");
		pesquisarTextField.setBounds(573, 11, 121, 26);
		pesquisarPanel.add(pesquisarTextField);
		pesquisarTextField.setColumns(10);
		
		//Cria o botão de procura de filme.
		buscarFilmeButton = new JButton("Buscar");
		buscarFilmeButton.setMnemonic(KeyEvent.VK_U);
		buscarFilmeButton.setOpaque(false);
		buscarFilmeButton.setBorder(null);
		buscarFilmeButton.setForeground(corSubMenu);
		buscarFilmeButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/search.png")));
		buscarFilmeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		buscarFilmeButton.setBounds(708, 9, 121, 30);
		pesquisarPanel.add(buscarFilmeButton);
		
		//Registra o tratador de eventos da pesquisa.
		buscarFilmeButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TratadorEventoPesquisarFilme(IgFilmoteca.this, pesquisarTextField.getText()).pesquisarFilme();
			}
		});
		
		//Instância o painel de exibição.
		exibicaoPanel = new JPanel();
		exibicaoPanel.setBackground(corBase);
		exibicaoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Darth Movies", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		exibicaoPanel.setBounds(161, 70, 668, 387);
		getContentPane().add(exibicaoPanel);
		exibicaoPanel.setLayout(null);
		
		//Cria o resultado ScroopPane.
		resultadoScrollPane = new JScrollPane();
		resultadoScrollPane.setBounds(10, 37, 648, 295);
		exibicaoPanel.add(resultadoScrollPane);
		
		//Cria o resultadoJList.
		resultadoJList = new JList<String>();
		resultadoJList.setCellRenderer(new Cores());
		resultadoJList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		resultadoJList.setBackground(corBase);
		resultadoJList.setForeground(Color.WHITE);
		resultadoJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultadoScrollPane.setViewportView(resultadoJList);
		
		//Cria o botão detalhes do filme.
		JButton detalhesButton = new JButton("Detalhes");
		detalhesButton.setMnemonic(KeyEvent.VK_E);
		detalhesButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/movie47.png")));
		detalhesButton.setBackground(Color.BLACK);
		detalhesButton.setForeground(corSubMenu);
		detalhesButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		//Registra o tratador de eventos do botão de detalhes
		detalhesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirDetalhes();
			}
		});
		detalhesButton.setToolTipText("Selecione um filme da pesquisa e clique em detalhes para ver mais informa\u00F5es do filme.");
		detalhesButton.setBounds(520, 340, 138, 36);
		exibicaoPanel.add(detalhesButton);
		
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
		
		JPopupMenu jPopupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Atualiza Filme");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgCadastraAtorAutorDiretor(IgFilmoteca.this);
			}
		});
		
		
		getContentPane().addMouseListener(new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON3)
				jPopupMenu.show(getContentPane(), e.getX(), e.getY());
			}
		});
		
		getContentPane().add(jPopupMenu);
		exibicaoPanel.add(jPopupMenu);
		jPopupMenu.add(menuItem);
		
		//Define a tela como vísivel.
		setLocationRelativeTo(null);
		setVisible(false);
	}//IgFilmoteca
	
	/**
	 * Exibe os filmes em ordem descrescente.
	 */
	private void exibirClassificacao() {
		//Obtém os filmes
		List<Filme> filmes = FilmeDAO.pesquisarFilmeCriterio();
		
		//Verifica se existem filmes.
		if(filmes == null) {new IgMensagem(this, "Não há filmes cadastrados");}
		else{
			List<String> dadosFilmes = new ArrayList<String>();
			Iterator<Filme> it = filmes.iterator();
			while(it.hasNext()){
				Filme filme = it.next();
				dadosFilmes.add(String.format("Título: %-45s Classificação Pessoal: %-30s Classificação IMDB: %-30s", filme.getTitulo(),filme.getClassificacaoPessoal(),filme.getClassificacaoIMDB()));
			}
			new IgClassificacaoPessoalFilmes(this, dadosFilmes.toArray(new String[0]));
		}
	}
	/**
	 * Obtém a referência da caixa de texto de pesquisa.
	 * @return <code>JTextField</code>
	 */
	public JTextField getPesquisarTextField() {
		return pesquisarTextField;
	}

	/**
	 * Obtém a referência do painel de exibição da tela principal onde serão realizadas as operações.
	 * @return <code>JPanel</code>
	 */
	public JPanel getExibicaoPanel() {
		return exibicaoPanel;
	}
	
	/**
	 * Obtém a referência do botão de pesquisar filme da tela principal.
	 * @return <code>JButton</code>
	 */
	public static JButton getBuscarFilmeButton() {
		return buscarFilmeButton;
	}
	
	/**
	 * Obtém a referência da lista onde serão exibidos os resultados das pesquisas.
	 * @return <code>JList</code>
	 */
	public JList <String> getResultadoJList() {
		return resultadoJList;
	}

	/**
	 * Obém a referência do painel rolável onde serão exibidos os resultados das pesquisa.
	 * @return <code>JSCrollPane</code>
	 */
	public JScrollPane getResultadoScrollPane() {
		return resultadoScrollPane;
	}

	/**
	 * Obtém a referência do botão de pesquisar ator da tela principal.
	 * @return <code>JButton</code>
	 */
	public JButton getAtorButton() {
		return atorButton;
	}

	/**
	 * Obtém a referência do botão de pesquisar autor da tela principal.
	 * @return <code>JButton</code>
	 */
	public JButton getAutorButton() {
		return autorButton;
	}

	/**
	 * Obtém a referência do botão de pesquisar diretor da tela principal.
	 * @return <code>JButton</code>
	 */
	public JButton getDiretorButton() {
		return diretorButton;
	}

	/**
	 * Obtém a referência do botão de pesquisar genero da tela principal.
	 * @return <code>JButton</code>
	 */
	public JButton getGeneroButtono() {
		return generoButton;
	}
	
	@SuppressWarnings("rawtypes")
	public class Cores extends JLabel implements ListCellRenderer{
		  public Cores(){
		    setOpaque(true);
		  }
		  
		  //Obtém a referência do objeto a ser modificado.
		  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		     
		    setText(value.toString());
		    setForeground(Color.WHITE);
		    resultadoJList.setFont(new Font("Tahoma", Font.BOLD, 14));
		    if(!isSelected){
		      if(index % 2 == 0)
		        setBackground(corLista);
		      else
		        setBackground(corBase);
		    }
		    else
		      setBackground(list.getSelectionBackground());

		    return this;
		  } 
	}
	
	private void exibirDetalhes(){
		if(resultadoJList.getSelectedIndex() == -1) new IgMensagem(IgFilmoteca.this, "Você deve selecionar um item retornado da pesquisa");
		else{
			new TratadorEventoPesquisarFilme(IgFilmoteca.this, resultadoJList.getSelectedValue()).pesquisarFilme();
		}
	}
}//class IgFilmoteca
