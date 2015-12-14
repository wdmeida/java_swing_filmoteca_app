package tsi.lpv.filmoteca.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import tsi.lpv.filmoteca.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.filmoteca.persistencia.GeneroDAO;
import tsi.lpv.filmoteca.trataeventos.TratadorEventoCadastraFilme;
import tsi.lpv.filmoteca.trataeventos.TratadorEventoInsereAtor;
import tsi.lpv.filmoteca.trataeventos.TratadorEventoInsereAutor;
import tsi.lpv.filmoteca.trataeventos.TratadorEventoInsereDiretor;
import tsi.lpv.filmoteca.trataeventos.TratadorEventoInserirImagem;
import tsi.lpv.filmoteca.trataeventos.TratadorPesquisaIMDB;

/**
 * A classe <code>IgCadastraFilme</code> constrói a interface gráfica responsável pelo cadastro de um novo 
 * filme e seus atributos.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class IgCadastrarFilme extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tituloField;
	private JTextField paisField;
	private JTextField atorField;
	private JTextField autorField;
	private JTextField diretorField;
	private JTextField fotoField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton cadastrarButton;
	private JButton cancelarButton;
	private JComboBox<String> generoComboBox;
	private JComboBox<String> faixaEtariaComboBox;
	private JDateChooser chooser;
	private JYearChooser jYearChooser;
	private JEditorPane sinopseEditorPane;
	private JButton inserirFotoButton;
	private JButton limparFotoButton;
	private JRadioButton dvdRadio;
	private JRadioButton arquivoDigitalRadio;
	private JRadioButton bluRayRadio;
	private JList<String> autorArea;
	private JList<String> atorArea;
	private JButton inserirDiretorButton;
	private JButton limparDiretorButton;
	private JButton limparAutorButton;
	private JButton inserirAutorButton;
	private JButton inserirAtorButton;
	private JButton limparAtorButton;
	private JLabel posterLabel;
	private JTextField duracaoField;
	private JSpinner cllassificacaoSpinner;
	private JSpinner classificacaoIMDBspinner;
	private JList<String> diretorArea;
	private JLabel informaLabel;
	private JLabel inforFilmeRepitido;
	private JLabel erroDiretorlabel;
	private JLabel erroAutorlabel;
	private JLabel erroAtorlabel;
	
	/**Construtor da classe <code>IgCadastrarFilme</code>.
	 * @param igFilmoteca <code>IgFilmoteca</code>.
	 * @param cadastraAtivo <code>boolean</code>.
	 */
	public IgCadastrarFilme(IgFilmoteca igFilmoteca, boolean cadastraAtivo) {
		//Define a proriedades da Janela.
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarFilme.class.getResource("/tsi/lpv/filmoteca/imagens/movie61.png")));
		getContentPane().setBackground(new Color(87, 87, 87));
		setBackground(new Color(87, 87, 87));
		setTitle("Cadastrar Filme");
		setSize(485, 462);
		getContentPane().setLayout(null);
		
		//Cria o panel para ficar na parte Sul da Janela.
		JPanel panelSul = new JPanel();
		panelSul.setBackground(new Color(87, 87, 87));
		panelSul.setBounds(0, 399, 479, 35);
		getContentPane().add(panelSul);
		panelSul.setLayout(null);
		
		//Cria um Label para ter a hora do sistem.
		JLabel dataAtualLabel = new JLabel(obtemData());
		dataAtualLabel.setBounds(10, 9, 241, 19);
		panelSul.add(dataAtualLabel);
		
		//Cria e adiciona o botao cadastra ao panelSul e define o atalho mnemonic.
		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(230, 7, 91, 23);
		cadastrarButton.setMnemonic(KeyEvent.VK_C);
		panelSul.add(cadastrarButton);
		
		//Cria e adiciona o botao cancelar ao panelSul e define o atalho mnemonic.
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(375, 7, 91, 23);
		cancelarButton.setMnemonic(KeyEvent.VK_N);
		panelSul.add(cancelarButton);
		
		//Cria um JTabbedPane e define suas propriedades e adiciona a janela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBackground(new Color(87, 87, 87));
		tabbedPane.setBounds(0, 0, 479, 399);
		getContentPane().add(tabbedPane);
		
		//Cria a aba Filme e difine suas propriedades.
		tabbedPane.addTab("Filme", new ImageIcon(IgCadastrarFilme.class.getResource("/tsi/lpv/filmoteca/imagens/movie47.png")), criaAbaFilme(), "F");
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_F);
		
		//Cria a aba Pôster e difine suas propriedades.
		tabbedPane.addTab("P\u00F4ster", null, criaAbaPoster(), null);
		tabbedPane.setDisplayedMnemonicIndexAt(1, 1);
		tabbedPane.setMnemonicAt(1, 80);
		
		//Cria a aba Diretor e difine suas propriedades.
		tabbedPane.addTab("Diretor", new ImageIcon(IgCadastrarFilme.class.getResource("/tsi/lpv/filmoteca/imagens/directors.png")), criaAbaDiretor(), null);
		tabbedPane.setDisplayedMnemonicIndexAt(2, 2);
		tabbedPane.setMnemonicAt(2, 68);
		
		//Cria a aba Autor e difine suas propriedades.
		tabbedPane.addTab("Autor", null, criaAbaAutor(), null);
		tabbedPane.setDisplayedMnemonicIndexAt(3, 2);
		tabbedPane.setMnemonicAt(3, 65);
		
		//Cria a aba Ator e difine suas propriedades.
		tabbedPane.addTab("Ator", new ImageIcon(IgCadastrarFilme.class.getResource("/tsi/lpv/filmoteca/imagens/star-shape.png")), criaAbaArtista(), null);
		tabbedPane.setMnemonicAt(4, 84);
		
		//Cria e difine o tratador do Botao cadastrar e cancelar.
		TratadorEventoCadastraFilme eventoCadastraFilme = new TratadorEventoCadastraFilme(this,cadastraAtivo);
		cadastrarButton.addActionListener(eventoCadastraFilme);
		cancelarButton.addActionListener(eventoCadastraFilme);
		
		//Define o tratador da Janela quando ela for finalizada.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				IgCadastrarFilme.this.dispose();
			}
		});
		
		//Define as propriedades da Janela.
		setLocationRelativeTo(igFilmoteca);
		setResizable(false);
		setModal(true);
		if(cadastraAtivo)
			setVisible(true);
	}//IgCadastrarFilme()
	
	//Cria a Aba Filme.
	private JPanel criaAbaFilme(){
		
		//Cria um Painel e um CardLayout para Aba de filme.
		CardLayout cardLayout = new CardLayout();
		JPanel filmePanel = new JPanel(cardLayout);
		filmePanel.setBackground(new Color(87, 87, 87));
		//Cria as Janelas do CardLayout.
		criaParte1DaAbaFilme(filmePanel,cardLayout);
		criaParte2DaAbaFilme(filmePanel,cardLayout);
		//Retorna o Painel construido.
		return filmePanel;
	}
	
	//Cria a primeira Janela do CardLayout.
	private void criaParte1DaAbaFilme(JPanel filmePanel,CardLayout cardLayout){
		//Cria o Painel e define suas propriedades.
		JPanel filmePanel1 = new JPanel();
		filmePanel1.setLayout(null);
		filmePanel1.setBackground(new Color(87,87,87));
		
		//Cria painel de Titulos define suas propriedades e adiciona ao painel principal.
		JPanel tituloPanel = new JPanel();
		tituloPanel.setBounds(4, 11, 221, 54);
		tituloPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Titulo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tituloPanel.setBackground(new Color(87, 87, 87));
		tituloPanel.setLayout(null);
		filmePanel1.add(tituloPanel);
		
		
		tituloField = new JTextField();
		tituloField.setBounds(10, 21, 195, 20);
		tituloPanel.add(tituloField);
		tituloField.setColumns(10);
		
		//Adiciona o evento de foco ao campo titulo para buscar as informações do filme na api do imdb.
		tituloField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				new TratadorPesquisaIMDB(IgCadastrarFilme.this).preencherDados();
			}
		});
		
		JPanel paisPanel = new JPanel();
		paisPanel.setBounds(230, 11, 221, 54);
		paisPanel.setBorder(new TitledBorder(null, "Pais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paisPanel.setBackground(new Color(87, 87, 87));
		filmePanel1.add(paisPanel);
		paisPanel.setLayout(null);
		
		paisField = new JTextField();
		paisField.setBounds(10, 21, 195, 20);
		paisPanel.add(paisField);
		paisField.setColumns(10);
		
		JPanel generoPanel = new JPanel();
		generoPanel.setBounds(230, 76, 221, 54);
		generoPanel.setBorder(new TitledBorder(null, "Genero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		generoPanel.setBackground(new Color(87, 87, 87));
		filmePanel1.add(generoPanel);
		generoPanel.setLayout(null);
		String[] generos = GeneroDAO.obtemNomesGenero();
		
		if(generos == null){
			generos = new String[]{"Novo Genero..."};
		}
		
		generoComboBox = new JComboBox<String>(generos);
		generoComboBox.setSize(195, 21);
		generoComboBox.setLocation(10, 18);
		generoComboBox.setSelectedIndex(0);
		generoPanel.add(generoComboBox);
		
		generoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				verificaComandoComboBox();
			}
		});
		
		//Cria painel de duração define suas propriedades e adiciona ao painel principal.
		JPanel duracaoPanel = new JPanel();
		filmePanel1.add(duracaoPanel);
		duracaoPanel.setBorder(new TitledBorder(null, "Dura\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		duracaoPanel.setBounds(4, 141, 221, 54);
		duracaoPanel.setBackground(new Color(87, 87, 87));
		duracaoPanel.setLayout(null);

		duracaoField = new JTextField();
		duracaoField.setBounds(55, 17, 121, 20);
		duracaoField.setColumns(10);
		duracaoPanel.add(duracaoField);
		
		//Cria painel de ano define suas propriedades e adiciona ao painel principal.
		JPanel anoPanel = new JPanel();
		anoPanel.setBounds(230, 141, 221, 54);
		anoPanel.setBorder(new TitledBorder(null, "Ano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		anoPanel.setBackground(new Color(87, 87, 87));
		anoPanel.setLayout(null);
		filmePanel1.add(anoPanel);
		
		jYearChooser = new JYearChooser();
		jYearChooser.setSize(97, 20);
		jYearChooser.setLocation(21, 19);
		anoPanel.add(jYearChooser);
		
		//Cria painel de ano lançamento define suas propriedades e adiciona ao painel principal.
		JPanel anoLacamentoPanel = new JPanel(null);
		anoLacamentoPanel.setBounds(230, 207, 216, 54);
		anoLacamentoPanel.setBackground(new Color(87, 87, 87));
		anoLacamentoPanel.setBorder(new TitledBorder(null, "Ano de Lan\u00E7amento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		filmePanel1.add(anoLacamentoPanel);
		
		Calendar cal = Calendar.getInstance();
		chooser = new JDateChooser(cal.getTime());
		chooser.setSize(101, 20);
		chooser.setLocation(60, 20);
		anoLacamentoPanel.add(chooser);
		
		//Cria painel de faixa Etaria define suas propriedades e adiciona ao painel principal.
		JPanel faixaEtariaPanel = new JPanel();
		faixaEtariaPanel.setBounds(4, 76, 221, 54);
		faixaEtariaPanel.setBorder(new TitledBorder(null, "Faixa Etaria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		faixaEtariaPanel.setBackground(new Color(87, 87, 87));
		filmePanel1.add(faixaEtariaPanel);
		faixaEtariaPanel.setLayout(null);
		
		faixaEtariaComboBox = new JComboBox<String>(new String[] {"Livre", " inadequado para menores de 10", " inadequado para menores de 12", " inadequado para menores de 14", " inadequado para menores de 16", " inadequado para menores de 18"});
		faixaEtariaComboBox.setSelectedIndex(0);
		faixaEtariaComboBox.setBounds(10, 21, 195, 22);
		faixaEtariaPanel.add(faixaEtariaComboBox);
		
		JPanel midiaPanel = new JPanel();
		midiaPanel.setBackground(new Color(87, 87, 87));
		midiaPanel.setBorder(new TitledBorder(null, "M\u00EDdia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		midiaPanel.setBounds(4, 207, 221, 74);
		filmePanel1.add(midiaPanel);
		midiaPanel.setLayout(null);
		
		dvdRadio = new JRadioButton("DVD");
		buttonGroup.add(dvdRadio);
		dvdRadio.setBackground(new Color(87, 87, 87));
		dvdRadio.setBounds(6, 17, 66, 23);
		dvdRadio.setSelected(true);
		midiaPanel.add(dvdRadio);
		
		bluRayRadio = new JRadioButton("Blu-Ray");
		buttonGroup.add(bluRayRadio);
		bluRayRadio.setBackground(new Color(87, 87, 87));
		bluRayRadio.setBounds(100, 17, 109, 23);
		midiaPanel.add(bluRayRadio);
		
		arquivoDigitalRadio = new JRadioButton("Arquivo Digital");
		buttonGroup.add(arquivoDigitalRadio);
		arquivoDigitalRadio.setBackground(new Color(87, 87, 87));
		arquivoDigitalRadio.setBounds(6, 43, 109, 23);
		midiaPanel.add(arquivoDigitalRadio);
		
		JButton btnProximo = new JButton("Proximo");
		btnProximo.setBounds(375, 320, 89, 23);
		btnProximo.setMnemonic(KeyEvent.VK_X);
		filmePanel1.add(btnProximo);
		
		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(filmePanel, "Segundo");
			}
		});
		
		informaLabel = new JLabel("");
		informaLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		informaLabel.setBounds(4, 287, 447, 30);
		informaLabel.setForeground(new Color(255, 102, 0));
		filmePanel1.add(informaLabel);
		
		inforFilmeRepitido = new JLabel("");
		inforFilmeRepitido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		inforFilmeRepitido.setBounds(4, 313, 447, 30);
		inforFilmeRepitido.setForeground(new Color(255, 102, 0));
		filmePanel1.add(inforFilmeRepitido);
		
		filmePanel.add(filmePanel1, "Primeiro");
	}		
	
	/**
	 * Cria a segunda aba de cadastro.
	 * @param filmePanel <code>JPanel</code>
	 * @param cardLayout <code>CardLayout</code>
	 */
	private void criaParte2DaAbaFilme(JPanel filmePanel,CardLayout cardLayout){
		JPanel filmePanel2 = new JPanel();
		filmePanel2.setLayout(null);
		filmePanel2.setBackground(new Color(87, 87, 87));
		
		JPanel classificacaoIMDBpanel = new JPanel();
		filmePanel2.add(classificacaoIMDBpanel);
		classificacaoIMDBpanel.setBorder(new TitledBorder(null, "Classifica\u00E7\u00E3o IMDB", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		classificacaoIMDBpanel.setBounds(252, 10, 199, 50);
		classificacaoIMDBpanel.setBackground(new Color(87, 87, 87));
		classificacaoIMDBpanel.setLayout(null);
		
		classificacaoIMDBspinner = new JSpinner();
		classificacaoIMDBspinner.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		classificacaoIMDBspinner.setBounds(58, 21, 87, 18);
		classificacaoIMDBpanel.add(classificacaoIMDBspinner);
		
		JPanel classificacaoPessoalPanel = new JPanel();
		filmePanel2.add(classificacaoPessoalPanel);
		classificacaoPessoalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Classifica\u00E7\u00E3o Pessoal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		classificacaoPessoalPanel.setBounds(8, 10, 199, 50);
		classificacaoPessoalPanel.setBackground(new Color(87, 87, 87));
		classificacaoPessoalPanel.setLayout(null);
		
		cllassificacaoSpinner = new JSpinner();
		cllassificacaoSpinner.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		cllassificacaoSpinner.setBounds(53, 21, 87, 18);
		classificacaoPessoalPanel.add(cllassificacaoSpinner);
		
		JPanel sinopsePanel = new JPanel();
		filmePanel2.add(sinopsePanel);
		sinopsePanel.setBorder(new TitledBorder(null, "Sinopse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sinopsePanel.setBackground(new Color(87, 87, 87));
		sinopsePanel.setBounds(10, 71, 441, 253);
		sinopsePanel.setLayout(null);
		
		JScrollPane sinopseScrollPane = new JScrollPane();
		sinopseScrollPane.setBounds(10, 14, 416, 228);
		sinopseScrollPane.setBackground(new Color(87, 87, 87));
		sinopsePanel.add(sinopseScrollPane);
		
		sinopseEditorPane = new JEditorPane();
		sinopseEditorPane.setBackground(Color.WHITE);
		sinopseScrollPane.setViewportView(sinopseEditorPane);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(375, 335, 89, 23);
		btnAnterior.setMnemonic(KeyEvent.VK_E);
		filmePanel2.add(btnAnterior);
		
		btnAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(filmePanel, "Primeiro");
			}
		});
		
		filmePanel.add(filmePanel2, "Segundo");
	}
	
	/**
	 * Cria um painél para os dados do diretor.
	 * @return <code>JPanel</code>
	 */
	private JPanel criaAbaDiretor(){
		JPanel diretorPanel = new JPanel();
		diretorPanel.setBackground(new Color(87, 87, 87));
		diretorPanel.setLayout(null);
		
		JPanel diretorCadastroPanel = new JPanel();
		diretorCadastroPanel.setBorder(new TitledBorder(null, "Diretores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		diretorCadastroPanel.setBackground(new Color(87, 87, 87));
		diretorCadastroPanel.setBounds(10, 11, 208, 347);
		diretorPanel.add(diretorCadastroPanel);
		diretorCadastroPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 188, 313);
		diretorCadastroPanel.add(scrollPane);
		DefaultListModel<String> model = new DefaultListModel<>();
		diretorArea = new JList<>(model);
		scrollPane.setViewportView(diretorArea);
		
		JPanel cadastraDiretorPanel = new JPanel();
		cadastraDiretorPanel.setBorder(new TitledBorder(null, "Cadastra Diretor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cadastraDiretorPanel.setBackground(new Color(87, 87, 87));
		cadastraDiretorPanel.setBounds(228, 262, 236, 62);
		diretorPanel.add(cadastraDiretorPanel);
		cadastraDiretorPanel.setLayout(null);
		
		diretorField = new JTextField();
		diretorField.setBounds(10, 25, 216, 20);
		cadastraDiretorPanel.add(diretorField);
		diretorField.setColumns(10);
		
		inserirDiretorButton = new JButton("Inserir");
		inserirDiretorButton.setBounds(228, 335, 91, 23);
		diretorPanel.add(inserirDiretorButton);
		
		limparDiretorButton = new JButton("Limpar");
		limparDiretorButton.setBounds(373, 335, 91, 23);
		diretorPanel.add(limparDiretorButton);
		
		JPanel informacaoPanel = new JPanel();
		informacaoPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacaoPanel.setBackground(new Color(87, 87, 87));
		informacaoPanel.setBounds(228, 11, 236, 200);
		diretorPanel.add(informacaoPanel);
		informacaoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblInformeODiretores = new JLabel("Informe o Diretor(es) do Filme:");
		informacaoPanel.add(lblInformeODiretores);

		JLabel lblOCadastroDo = new JLabel("O cadastro do diretor e não obrigatorio.");
		informacaoPanel.add(lblOCadastroDo);

		JLabel lblComoCadastraO = new JLabel("Como Cadastra o Diretor:");
		informacaoPanel.add(lblComoCadastraO);

		JLabel lblDigitaAO = new JLabel("Digita a o nome do diretor na caixa ");
		informacaoPanel.add(lblDigitaAO);

		JLabel lblDeDialogo = new JLabel("de Dialogo.");
		informacaoPanel.add(lblDeDialogo);

		JLabel lblAperteInserirOu = new JLabel("Aperte Inserir ou ALT+I.");
		informacaoPanel.add(lblAperteInserirOu);

		JLabel lblParaApagarO = new JLabel("Para apagar o ultimo diretor.");
		informacaoPanel.add(lblParaApagarO);
		
		JLabel lblCliqueEmLipar = new JLabel("Clique em Lipar ou ALT+L");
		informacaoPanel.add(lblCliqueEmLipar);
		TratadorEventoInsereDiretor eventoInsereDiretor = new TratadorEventoInsereDiretor(IgCadastrarFilme.this);
		
		inserirDiretorButton.setMnemonic(KeyEvent.VK_I);
		limparDiretorButton.setMnemonic(KeyEvent.VK_L);
		
		erroDiretorlabel = new JLabel("");
		erroDiretorlabel.setBounds(228, 222, 236, 23);
		erroDiretorlabel.setForeground(new Color(255, 102, 0));
		diretorPanel.add(erroDiretorlabel);
		
		diretorField.addActionListener(eventoInsereDiretor);
		inserirDiretorButton.addActionListener(eventoInsereDiretor);
		limparDiretorButton.addActionListener(eventoInsereDiretor);
		
		return diretorPanel;
	}
	
	/**
	 * Cria uma nova aba para inserir no painel principal.
	 * @return <code>JPanel</code>
	 */
	private JPanel criaAbaAutor(){
		JPanel autorPanel = new JPanel();
		autorPanel.setBackground(new Color(87, 87, 87));
		autorPanel.setLayout(null);
		
		JPanel autorCadastroPanel = new JPanel();
		autorCadastroPanel.setBorder(new TitledBorder(null, "Autores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		autorCadastroPanel.setBackground(new Color(87, 87, 87));
		autorCadastroPanel.setBounds(10, 11, 208, 347);
		autorPanel.add(autorCadastroPanel);
		autorCadastroPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 188, 313);
		autorCadastroPanel.add(scrollPane);
		DefaultListModel<String> model = new DefaultListModel<>();
		autorArea = new JList<>(model);
		scrollPane.setViewportView(autorArea);
		
		JPanel cadastraAutorPanel = new JPanel();
		cadastraAutorPanel.setBorder(new TitledBorder(null, "Cadastra Autor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cadastraAutorPanel.setBackground(new Color(87, 87, 87));
		cadastraAutorPanel.setBounds(228, 262, 236, 62);
		autorPanel.add(cadastraAutorPanel);
		cadastraAutorPanel.setLayout(null);
		
		autorField = new JTextField();
		autorField.setBounds(10, 25, 216, 20);
		cadastraAutorPanel.add(autorField);
		autorField.setColumns(10);
		
		inserirAutorButton = new JButton("Inserir");
		inserirAutorButton.setMnemonic(KeyEvent.VK_I);
		inserirAutorButton.setBounds(228, 335, 91, 23);
		autorPanel.add(inserirAutorButton);
		
		limparAutorButton = new JButton("Limpar");
		limparAutorButton.setMnemonic(KeyEvent.VK_L);
		limparAutorButton.setBounds(373, 335, 91, 23);
		autorPanel.add(limparAutorButton);
		
		TratadorEventoInsereAutor eventoInsereAutor = new TratadorEventoInsereAutor(IgCadastrarFilme.this);
		autorField.addActionListener(eventoInsereAutor);
		inserirAutorButton.addActionListener(eventoInsereAutor);
		limparAutorButton.addActionListener(eventoInsereAutor);
		
		JPanel informacaoPanel = new JPanel();
		informacaoPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacaoPanel.setBackground(new Color(87, 87, 87));
		informacaoPanel.setBounds(228, 11, 236, 200);
		autorPanel.add(informacaoPanel);
		informacaoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblInformeOAutores = new JLabel("Informe o Autor(es) do Filme:");
		informacaoPanel.add(lblInformeOAutores);

		JLabel lblOCadastroDo_1 = new JLabel("O cadastro do autor e não e obrigatorio.");
		informacaoPanel.add(lblOCadastroDo_1);

		JLabel lblComoCadastraO_1 = new JLabel("Como Cadastra o Autor:");
		informacaoPanel.add(lblComoCadastraO_1);

		JLabel lblDigitaAO_1 = new JLabel("Digita a o nome do autor na caixa ");
		informacaoPanel.add(lblDigitaAO_1);

		JLabel lblDeAutor = new JLabel("de Autor.");
		informacaoPanel.add(lblDeAutor);

		JLabel label_5 = new JLabel("Aperte Inserir ou ALT+I.");
		informacaoPanel.add(label_5);

		JLabel lblParaApagarO_1 = new JLabel("Para apagar o ultimo autor.");
		informacaoPanel.add(lblParaApagarO_1);
		
		JLabel label = new JLabel("Clique em Lipar ou ALT+L");
		informacaoPanel.add(label);
		
		erroAutorlabel = new JLabel("");
		erroAutorlabel.setBounds(228, 222, 236, 23);
		erroAutorlabel.setForeground(new Color(255, 102, 0));
		autorPanel.add(erroAutorlabel);
		
		return autorPanel;
	}
	
	/**
	 * Cria uma nova aba para inserir no painel principal.
	 * @return <code>JPanel</code>
	 */
	private JPanel criaAbaArtista(){
		JPanel artistaPanel = new JPanel();
		artistaPanel.setBackground(new Color(87, 87, 87));
		artistaPanel.setLayout(null);
		
		JPanel artistaCadastroPanel = new JPanel();
		artistaCadastroPanel.setBorder(new TitledBorder(null, "Atores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		artistaCadastroPanel.setBackground(new Color(87, 87, 87));
		artistaCadastroPanel.setBounds(10, 11, 208, 347);
		artistaPanel.add(artistaCadastroPanel);
		artistaCadastroPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 188, 313);
		artistaCadastroPanel.add(scrollPane);
		DefaultListModel<String> model = new DefaultListModel<>();
		atorArea = new JList<>(model);
		scrollPane.setViewportView(atorArea);
		
		JPanel cadastraArtistasPanel = new JPanel();
		cadastraArtistasPanel.setBorder(new TitledBorder(null, "Cadastra Ator", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cadastraArtistasPanel.setBackground(new Color(87, 87, 87));
		cadastraArtistasPanel.setBounds(228, 262, 236, 62);
		artistaPanel.add(cadastraArtistasPanel);
		cadastraArtistasPanel.setLayout(null);
		
		atorField = new JTextField();
		atorField.setBounds(10, 25, 216, 20);
		cadastraArtistasPanel.add(atorField);
		atorField.setColumns(10);
		
		inserirAtorButton = new JButton("Inserir");
		inserirAtorButton.setMnemonic(KeyEvent.VK_I);
		inserirAtorButton.setBounds(228, 335, 91, 23);
		artistaPanel.add(inserirAtorButton);
		
		limparAtorButton = new JButton("Limpar");
		limparAtorButton.setMnemonic(KeyEvent.VK_L);
		limparAtorButton.setBounds(373, 335, 91, 23);
		artistaPanel.add(limparAtorButton);
		
		JPanel informacaoPanel = new JPanel();
		informacaoPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacaoPanel.setBackground(new Color(87, 87, 87));
		informacaoPanel.setBounds(228, 11, 236, 200);
		artistaPanel.add(informacaoPanel);
		
		JLabel lblInformeOAtores = new JLabel("Informe o Ator(es) do Filme:");
		lblInformeOAtores.setBounds(10, 25, 204, 21);
		informacaoPanel.add(lblInformeOAtores);

		JLabel lblOCadastroDo_2 = new JLabel("O cadastro do ator e não obrigatorio.");
		lblOCadastroDo_2.setBounds(20, 46, 204, 21);
		informacaoPanel.add(lblOCadastroDo_2);

		JLabel lblComoCadastraO_2 = new JLabel("Como Cadastra o Ator");
		lblComoCadastraO_2.setBounds(10, 71, 204, 21);
		informacaoPanel.add(lblComoCadastraO_2);

		JLabel lblDigitaAO_2 = new JLabel("Digita a o nome do ator na caixa ");
		lblDigitaAO_2.setBounds(20, 92, 204, 21);
		informacaoPanel.add(lblDigitaAO_2);

		JLabel label_4 = new JLabel("de Dialogo.");
		label_4.setBounds(20, 107, 194, 21);
		informacaoPanel.add(label_4);

		JLabel label_5 = new JLabel("Aperte Inserir ou ALT+I.");
		label_5.setBounds(20, 124, 194, 29);
		informacaoPanel.add(label_5);

		JLabel lblParaApagarO_2 = new JLabel("Para apagar o ultimo ator.");
		lblParaApagarO_2.setBounds(20, 145, 194, 27);
		informacaoPanel.add(lblParaApagarO_2);
		
		JLabel label = new JLabel("Clique em Lipar ou ALT+L");
		informacaoPanel.add(label);
		
		erroAtorlabel = new JLabel("");
		erroAtorlabel.setBounds(228, 222, 236, 23);
		erroAtorlabel.setForeground(new Color(255, 102, 0));
		artistaPanel.add(erroAtorlabel);
		
		TratadorEventoInsereAtor eventoInsereAtor = new TratadorEventoInsereAtor(IgCadastrarFilme.this);
		atorField.addActionListener(eventoInsereAtor);
		inserirAtorButton.addActionListener(eventoInsereAtor);
		limparAtorButton.addActionListener(eventoInsereAtor);
		
		return artistaPanel;
	}
	
	/**
	 * Cria uma nova aba para inserir no painel principal.
	 * @return <code>JPanel</code>
	 */
	private JPanel criaAbaPoster(){
		JPanel posterPanel = new JPanel();
		posterPanel.setBackground(new Color(87, 87, 87));
		posterPanel.setLayout(null);
		
		JPanel posterImagemPanel = new JPanel();
		posterImagemPanel.setBorder(new TitledBorder(null, "P\u00F4ster", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		posterImagemPanel.setBackground(new Color(87, 87, 87));
		posterImagemPanel.setBounds(10, 11, 228, 331);
		posterPanel.add(posterImagemPanel);
		posterImagemPanel.setLayout(null);
		
		posterLabel = new JLabel("");
		posterLabel.setBounds(10, 15, 208, 305);
		posterImagemPanel.add(posterLabel);
		
		JPanel informacoePanel = new JPanel();
		informacoePanel.setBackground(new Color(87, 87, 87));
		informacoePanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacoePanel.setBounds(248, 11, 216, 212);
		posterPanel.add(informacoePanel);
		informacoePanel.setLayout(null);
		
		JLabel lblSelecioneOPster = new JLabel("Selecione o P\u00F4ster do filme:");
		lblSelecioneOPster.setBounds(10, 22, 196, 20);
		informacoePanel.add(lblSelecioneOPster);
		
		JLabel lblOPsteNo = new JLabel("O P\u00F4ste n\u00E3o e de preenchimento");
		lblOPsteNo.setBounds(10, 47, 196, 20);
		informacoePanel.add(lblOPsteNo);
		
		JLabel lblObrigatorio = new JLabel("obrigatorio.");
		lblObrigatorio.setBounds(10, 64, 70, 20);
		informacoePanel.add(lblObrigatorio);
		
		JLabel lblComoSelecionarO = new JLabel("Como selecionar o P\u00F4ster:");
		lblComoSelecionarO.setBounds(10, 95, 196, 20);
		informacoePanel.add(lblComoSelecionarO);
		
		JLabel lblCliqueNoBoto = new JLabel("Clique no bot\u00E3o Inserir ou utilize");
		lblCliqueNoBoto.setBounds(20, 114, 186, 20);
		informacoePanel.add(lblCliqueNoBoto);
		
		JLabel lblOAlti = new JLabel(" o ALT+I.");
		lblOAlti.setBounds(20, 132, 186, 20);
		informacoePanel.add(lblOAlti);
		
		JLabel lblDepoisESelecionar = new JLabel("Depois e selecionar a");
		lblDepoisESelecionar.setBounds(20, 159, 186, 20);
		informacoePanel.add(lblDepoisESelecionar);
		
		JLabel lblImagemEConfimar = new JLabel("Imagem e confimar");
		lblImagemEConfimar.setBounds(20, 181, 186, 20);
		informacoePanel.add(lblImagemEConfimar);
		
		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(new Color(87, 87, 87));
		fotoPanel.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fotoPanel.setBounds(254, 239, 210, 61);
		posterPanel.add(fotoPanel);
		fotoPanel.setLayout(null);
		
		fotoField = new JTextField();
		fotoField.setEditable(false);
		fotoField.setBounds(10, 23, 190, 20);
		fotoPanel.add(fotoField);
		fotoField.setColumns(10);
		
		inserirFotoButton = new JButton("Inserir");
		inserirFotoButton.setBounds(254, 319, 91, 23);
		posterPanel.add(inserirFotoButton);
		
		limparFotoButton = new JButton("Limpar");
		limparFotoButton.setBounds(373, 319, 91, 23);
		posterPanel.add(limparFotoButton);
		
		inserirFotoButton.setMnemonic(KeyEvent.VK_I);
		limparFotoButton.setMnemonic(KeyEvent.VK_L);
		
		TratadorEventoInserirImagem eventoInserirImagem = new TratadorEventoInserirImagem(IgCadastrarFilme.this);
		inserirFotoButton.addActionListener(eventoInserirImagem);
		limparFotoButton.addActionListener(eventoInserirImagem);
		
		return posterPanel;
	}

	/**Retorna a Referencia do tituloField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getTituloField() {
		return tituloField;
	}

	/**Retorna a Referencia do paisField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getPaisField() {
		return paisField;
	}

	/**Retorna a Referencia do atorField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getTextField() {
		return atorField;
	}

	/**Retorna a Referencia do fotoField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getFotoField() {
		return fotoField;
	}

	/**Retorna a Referencia do cadastrarButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getCadastrarButton() {
		return cadastrarButton;
	}

	/**Retorna a Referencia do cancelarButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getCancelarButton() {
		return cancelarButton;
	}

	/**Retorna a Referencia do generoComboBox
	 * @return um <code>JComboBox</code>.
	 */
	public JComboBox<String> getGeneroComboBox() {
		return generoComboBox;
	}

	/**Retorna a Referencia do faixaEtariaComboBox
	 * @return um <code>JComboBox</code>.
	 */
	public JComboBox<String> getFaixaEtariaComboBox() {
		return faixaEtariaComboBox;
	}

	/**Retorna a Referencia do chooser
	 * @return um <code>JDateChooser</code>.
	 */
	public JDateChooser getChooser() {
		return chooser;
	}

	/**Retorna a Referencia do jYearChooser
	 * @return um <code>JYearChooser</code>.
	 */
	public JYearChooser getjYearChooser() {
		return jYearChooser;
	}

	/**Retorna a Referencia do sinopseEditorPane
	 * @return um <code>JEditorPane</code>.
	 */
	public JEditorPane getSinopseEditorPane() {
		return sinopseEditorPane;
	}
	
	/**Retorna a Referencia do inserirFotoButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getInserirFotoButton() {
		return inserirFotoButton;
	}

	/**Retorna a Referencia do limparFotoButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getLimparFotoButton() {
		return limparFotoButton;
	}
	
	/**Retorna a Referencia do dvdRadio
	 * @return um <code>JRadioButton</code>.
	 */
	public JRadioButton getDvdRadio() {
		return dvdRadio;
	}

	/**Retorna a Referencia do arquivoDigitalRadio
	 * @return um <code>JRadioButton</code>.
	 */
	public JRadioButton getArquivoDigitalRadio() {
		return arquivoDigitalRadio;
	}

	/**Retorna a Referencia do bluRayRadio
	 * @return um <code>JRadioButton</code>.
	 */
	public JRadioButton getBluRayRadio() {
		return bluRayRadio;
	}

	/**Retorna a Referencia do diretorArea
	 * @return um <code>JList</code>.
	 */
	public JList<String> getDiretorArea() {
		return diretorArea;
	}

	/**Retorna a Referencia do autorArea
	 * @return um <code>JList</code>.
	 */
	public JList<String> getAutorArea() {
		return autorArea;
	}

	/**Retorna a Referencia do atorArea
	 * @return um <code>JList</code>.
	 */
	public JList<String> getAtorArea() {
		return atorArea;
	}
	
	/**Retorna a Referencia do inserirDiretorButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getInserirDiretorButton() {
		return inserirDiretorButton;
	}

	/**Retorna a Referencia do limparDiretorButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getLimparDiretorButton() {
		return limparDiretorButton;
	}
	
	/**Retorna a Referencia do diretorField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getDiretorField() {
		return diretorField;
	}
	
	/**Retorna a Referencia do autorField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getAutorField() {
		return autorField;
	}

	/**Retorna a Referencia do limparAutorButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getLimparAutorButton() {
		return limparAutorButton;
	}

	/**Retorna a Referencia do inserirAutorButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getInserirAutorButton() {
		return inserirAutorButton;
	}

	/**Retorna a Referencia do atorField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getAtorField() {
		return atorField;
	}

	/**Retorna a Referencia do inserirAtorButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getInserirAtorButton() {
		return inserirAtorButton;
	}

	/**Retorna a Referencia do limparAtorButton
	 * @return um <code>JButton</code>.
	 */
	public JButton getLimparAtorButton() {
		return limparAtorButton;
	}
	
	/**Retorna a Referencia do posterLabel
	 * @return um <code>JLabel</code>.
	 */
	public JLabel getPosterLabel() {
		return posterLabel;
	}

	/**Retorna a Referencia do cllassificacaoSpinner
	 * @return um <code>JSpinner</code>.
	 */
	public JSpinner getCllassificacaoSpinner() {
		return cllassificacaoSpinner;
	}

	/**Retorna a Referencia do classificacaoIMDBspinner
	 * @return um <code>JSpinner</code>.
	 */
	public JSpinner getClassificacaoIMDBspinner() {
		return classificacaoIMDBspinner;
	}

	/**Retorna a Referencia do duracaoField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getDuracaoField() {
		return duracaoField;
	}

	/**Retorna a Referencia do informaLabel
	 * @return um <code>JLabel</code>.
	 */
	public JLabel getInformaLabel() {
		return informaLabel;
	}

	/**Retorna a Referencia do inforFilmeRepitido
	 * @return um <code>JLabel</code>.
	 */
	public JLabel getInforFilmeRepitido() {
		return inforFilmeRepitido;
	}
	
	/**Retorna a Referencia do erroDiretorlabel
	 * @return um <code>JLabel</code>.
	 */
	public JLabel getErroDiretorlabel() {
		return erroDiretorlabel;
	}
	
	/**Retorna a Referencia do erroAutorlabel
	 * @return um <code>JLabel</code>.
	 */
	public JLabel getErroAutorlabel() {
		return erroAutorlabel;
	}

	/**Retorna a Referencia do erroAtorlabel
	 * @return um <code>JLabel</code>.
	 */
	public JLabel getErroAtorlabel() {
		return erroAtorlabel;
	}

	private void verificaComandoComboBox(){
		final String NOVO_GENERO = "Novo Genero...";
		if(FuncaoAuxiliar.comparaString(NOVO_GENERO, generoComboBox.getItemAt(generoComboBox.getSelectedIndex())))
			new IgCadastraGenero(IgCadastrarFilme.this);
	}
	
	/**
	 * Obtém um objeto string formatado com a data atual.
	 * @return <code>String</code> com a data formatada.
	 */
	private String obtemData() {
		Date data = new Date();
					
		data.setTime(Calendar.getInstance().getTimeInMillis());
		return String.format("%1$tA, %1$te de %1$tB de %1$tY.",data);	
	}//obtemData()
}//class IgCadastrarFilme
