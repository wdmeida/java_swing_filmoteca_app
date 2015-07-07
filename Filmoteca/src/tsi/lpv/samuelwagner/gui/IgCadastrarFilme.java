package tsi.lpv.samuelwagner.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoCadastraFilme;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoInsereAtor;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoInsereAutor;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoInsereDiretor;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoInserirImagem;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

/**
 * @author Samuel
 * @author Wagner
 */
public class IgCadastrarFilme extends JDialog {
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
	private JTextArea autorArea;
	private JTextArea atorArea;
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
	private JTextArea diretorArea;

	public IgCadastrarFilme(IgFilmoteca igFilmoteca) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarFilme.class.getResource("/tsi/lpv/samuelwagner/imagens/movie61.png")));
		getContentPane().setBackground(new Color(87, 87, 87));
		setBackground(new Color(87, 87, 87));
		setTitle("Cadastrar Filme");
		
		setSize(470, 418);
		getContentPane().setLayout(null);
		
		JPanel panelSul = new JPanel();
		panelSul.setBackground(new Color(87, 87, 87));
		panelSul.setBounds(0, 356, 461, 35);
		getContentPane().add(panelSul);
		panelSul.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(obtemData());
		lblNewLabel.setBounds(10, 9, 241, 19);
		panelSul.add(lblNewLabel);
		
		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(241, 7, 83, 23);
		panelSul.add(cadastrarButton);
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(339, 7, 77, 23);
		panelSul.add(cancelarButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		
		tabbedPane.setBackground(new Color(87, 87, 87));
		tabbedPane.setBounds(0, 0, 461, 354);
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Filme", new ImageIcon(IgCadastrarFilme.class.getResource("/tsi/lpv/samuelwagner/imagens/movie47.png")), criaAbaFilme(), "F");
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_F);
		
		tabbedPane.addTab("P\u00F4ster", null, criaAbaPoster(), null);
		tabbedPane.setDisplayedMnemonicIndexAt(1, 1);
		tabbedPane.setMnemonicAt(1, 80);
		
		tabbedPane.addTab("Diretor", new ImageIcon(IgCadastrarFilme.class.getResource("/tsi/lpv/samuelwagner/imagens/directors.png")), criaAbaDiretor(), null);
		tabbedPane.setDisplayedMnemonicIndexAt(2, 2);
		tabbedPane.setMnemonicAt(2, 68);
		
		tabbedPane.addTab("Autor", null, criaAbaAutor(), null);
		tabbedPane.setDisplayedMnemonicIndexAt(3, 2);
		tabbedPane.setMnemonicAt(3, 65);
		
		tabbedPane.addTab("Ator", new ImageIcon(IgCadastrarFilme.class.getResource("/tsi/lpv/samuelwagner/imagens/star-shape.png")), criaAbaArtista(), null);
		tabbedPane.setMnemonicAt(4, 84);
		
		TratadorEventoCadastraFilme eventoCadastraFilme = new TratadorEventoCadastraFilme(this);
		cadastrarButton.addActionListener(eventoCadastraFilme);
		cancelarButton.addActionListener(eventoCadastraFilme);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				IgCadastrarFilme.this.dispose();
			}
		});
		
		setLocationRelativeTo(igFilmoteca);
		setResizable(false);
		setModal(true);
		setVisible(true);
	}//IgCadastrarFilme()
	
	private JPanel criaAbaFilme(){

		CardLayout cardLayout = new CardLayout();
		JPanel filmePanel = new JPanel(cardLayout);
		filmePanel.setBackground(new Color(87, 87, 87));
		
		criaParte1DaAbaFilme(filmePanel,cardLayout);
		criaParte2DaAbaFilme(filmePanel,cardLayout);
		
		return filmePanel;
	}
	
	private void criaParte1DaAbaFilme(JPanel filmePanel,CardLayout cardLayout){
		
		JPanel filmePanel1 = new JPanel();
		filmePanel1.setLayout(null);
		filmePanel1.setBackground(new Color(87,87,87));
		
		JPanel tituloPanel = new JPanel();
		tituloPanel.setBounds(4, 11, 221, 54);
		tituloPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Titulo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tituloPanel.setBackground(new Color(87, 87, 87));
		filmePanel1.add(tituloPanel);
		tituloPanel.setLayout(null);
		
		tituloField = new JTextField();
		tituloField.setBounds(10, 21, 195, 20);
		tituloPanel.add(tituloField);
		tituloField.setColumns(10);
		
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
		
		JPanel duracaoPanel = new JPanel();
		filmePanel1.add(duracaoPanel);
		duracaoPanel.setBorder(new TitledBorder(null, "Dura\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		duracaoPanel.setBounds(4, 141, 221, 54);
		duracaoPanel.setBackground(new Color(87, 87, 87));
		duracaoPanel.setLayout(null);
		
		duracaoField = new JTextField();
		duracaoField.setBounds(55, 17, 121, 20);
		duracaoPanel.add(duracaoField);
		duracaoField.setColumns(10);
		
		JPanel anoPanel = new JPanel();
		anoPanel.setBounds(230, 141, 221, 54);
		anoPanel.setBorder(new TitledBorder(null, "Ano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		anoPanel.setBackground(new Color(87, 87, 87));
		filmePanel1.add(anoPanel);
		anoPanel.setLayout(null);
		
		jYearChooser = new JYearChooser();
		jYearChooser.setSize(97, 20);
		jYearChooser.setLocation(21, 19);
		
		anoPanel.add(jYearChooser);
		
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
		btnProximo.setBounds(357, 279, 89, 23);
		btnProximo.setMnemonic(KeyEvent.VK_X);
		filmePanel1.add(btnProximo);
		
		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(filmePanel, "Segundo");
			}
		});
		
		filmePanel.add(filmePanel1, "Primeiro");
	}
	
	private void criaParte2DaAbaFilme(JPanel filmePanel,CardLayout cardLayout){
		JPanel filmePanel2 = new JPanel();
		filmePanel2.setLayout(null);
		filmePanel2.setBackground(new Color(87, 87, 87));
		
		JPanel classificacaoIMDBpanel = new JPanel();
		filmePanel2.add(classificacaoIMDBpanel);
		classificacaoIMDBpanel.setBorder(new TitledBorder(null, "Classifica\u00E7\u00E3o IMDB", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		classificacaoIMDBpanel.setBounds(213, 10, 238, 50);
		classificacaoIMDBpanel.setBackground(new Color(87, 87, 87));
		classificacaoIMDBpanel.setLayout(null);
		
		classificacaoIMDBspinner = new JSpinner();
		classificacaoIMDBspinner.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		classificacaoIMDBspinner.setBounds(53, 21, 87, 18);
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
		sinopsePanel.setBounds(10, 71, 436, 193);
		sinopsePanel.setLayout(null);
		
		JScrollPane sinopseScrollPane = new JScrollPane();
		sinopseScrollPane.setBounds(10, 14, 416, 168);
		sinopseScrollPane.setBackground(new Color(87, 87, 87));
		sinopsePanel.add(sinopseScrollPane);
		
		sinopseEditorPane = new JEditorPane();
		sinopseEditorPane.setBackground(Color.WHITE);
		sinopseScrollPane.setViewportView(sinopseEditorPane);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(357, 290, 89, 23);
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
	
	private JPanel criaAbaDiretor(){
		JPanel diretorPanel = new JPanel();
		diretorPanel.setBackground(new Color(87, 87, 87));
		diretorPanel.setLayout(null);
		
		JPanel diretorCadastroPanel = new JPanel();
		diretorCadastroPanel.setBorder(new TitledBorder(null, "Diretores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		diretorCadastroPanel.setBackground(new Color(87, 87, 87));
		diretorCadastroPanel.setBounds(10, 11, 208, 307);
		diretorPanel.add(diretorCadastroPanel);
		diretorCadastroPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 188, 273);
		diretorCadastroPanel.add(scrollPane);
		
		diretorArea = new JTextArea();
		scrollPane.setViewportView(diretorArea);
		
		JPanel cadastraDiretorPanel = new JPanel();
		cadastraDiretorPanel.setBorder(new TitledBorder(null, "Cadastra Diretor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cadastraDiretorPanel.setBackground(new Color(87, 87, 87));
		cadastraDiretorPanel.setBounds(228, 222, 214, 62);
		diretorPanel.add(cadastraDiretorPanel);
		cadastraDiretorPanel.setLayout(null);
		
		diretorField = new JTextField();
		diretorField.setBounds(10, 25, 188, 20);
		cadastraDiretorPanel.add(diretorField);
		diretorField.setColumns(10);
		
		inserirDiretorButton = new JButton("Inserir");
		inserirDiretorButton.setBounds(228, 295, 91, 23);
		diretorPanel.add(inserirDiretorButton);
		
		limparDiretorButton = new JButton("Limpar");
		limparDiretorButton.setBounds(338, 295, 91, 23);
		diretorPanel.add(limparDiretorButton);
		
		JPanel informacaoPanel = new JPanel();
		informacaoPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacaoPanel.setBackground(new Color(87, 87, 87));
		informacaoPanel.setBounds(228, 11, 214, 200);
		diretorPanel.add(informacaoPanel);
		TratadorEventoInsereDiretor eventoInsereDiretor = new TratadorEventoInsereDiretor(IgCadastrarFilme.this);
		
		inserirDiretorButton.setMnemonic(KeyEvent.VK_I);
		limparDiretorButton.setMnemonic(KeyEvent.VK_L);
		
		diretorField.addActionListener(eventoInsereDiretor);
		inserirDiretorButton.addActionListener(eventoInsereDiretor);
		limparDiretorButton.addActionListener(eventoInsereDiretor);
		
		return diretorPanel;
	}
	
	private JPanel criaAbaAutor(){
		JPanel autorPanel = new JPanel();
		autorPanel.setBackground(new Color(87, 87, 87));
		autorPanel.setLayout(null);
		
		JPanel autorCadastroPanel = new JPanel();
		autorCadastroPanel.setBorder(new TitledBorder(null, "Autores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		autorCadastroPanel.setBackground(new Color(87, 87, 87));
		autorCadastroPanel.setBounds(10, 11, 208, 307);
		autorPanel.add(autorCadastroPanel);
		autorCadastroPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 188, 273);
		autorCadastroPanel.add(scrollPane);
		
		autorArea = new JTextArea();
		autorArea.setEditable(false);
		autorArea.setLineWrap(true);
		scrollPane.setViewportView(autorArea);
		
		JPanel cadastraAutorPanel = new JPanel();
		cadastraAutorPanel.setBorder(new TitledBorder(null, "Cadastra Autor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cadastraAutorPanel.setBackground(new Color(87, 87, 87));
		cadastraAutorPanel.setBounds(228, 222, 214, 62);
		autorPanel.add(cadastraAutorPanel);
		cadastraAutorPanel.setLayout(null);
		
		autorField = new JTextField();
		autorField.setBounds(10, 25, 188, 20);
		cadastraAutorPanel.add(autorField);
		autorField.setColumns(10);
		
		inserirAutorButton = new JButton("Inserir");
		inserirAutorButton.setMnemonic(KeyEvent.VK_I);
		inserirAutorButton.setBounds(228, 295, 91, 23);
		autorPanel.add(inserirAutorButton);
		
		limparAutorButton = new JButton("Limpar");
		limparAutorButton.setMnemonic(KeyEvent.VK_L);
		limparAutorButton.setBounds(338, 295, 91, 23);
		autorPanel.add(limparAutorButton);
		
		TratadorEventoInsereAutor eventoInsereAutor = new TratadorEventoInsereAutor(IgCadastrarFilme.this);
		autorField.addActionListener(eventoInsereAutor);
		inserirAutorButton.addActionListener(eventoInsereAutor);
		limparAutorButton.addActionListener(eventoInsereAutor);
		
		JPanel informacaoPanel = new JPanel();
		informacaoPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacaoPanel.setBackground(new Color(87, 87, 87));
		informacaoPanel.setBounds(228, 11, 214, 200);
		autorPanel.add(informacaoPanel);
		
		return autorPanel;
	}
	
	private JPanel criaAbaArtista(){
		JPanel artistaPanel = new JPanel();
		artistaPanel.setBackground(new Color(87, 87, 87));
		artistaPanel.setLayout(null);
		
		JPanel artistaCadastroPanel = new JPanel();
		artistaCadastroPanel.setBorder(new TitledBorder(null, "Atores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		artistaCadastroPanel.setBackground(new Color(87, 87, 87));
		artistaCadastroPanel.setBounds(10, 11, 208, 307);
		artistaPanel.add(artistaCadastroPanel);
		artistaCadastroPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 188, 273);
		artistaCadastroPanel.add(scrollPane);
		
		atorArea = new JTextArea();
		atorArea.setEditable(false);
		atorArea.setLineWrap(true);
		scrollPane.setViewportView(atorArea);
		
		JPanel cadastraArtistasPanel = new JPanel();
		cadastraArtistasPanel.setBorder(new TitledBorder(null, "Cadastra Ator", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cadastraArtistasPanel.setBackground(new Color(87, 87, 87));
		cadastraArtistasPanel.setBounds(228, 222, 214, 62);
		artistaPanel.add(cadastraArtistasPanel);
		cadastraArtistasPanel.setLayout(null);
		
		atorField = new JTextField();
		atorField.setBounds(10, 25, 188, 20);
		cadastraArtistasPanel.add(atorField);
		atorField.setColumns(10);
		
		inserirAtorButton = new JButton("Inserir");
		inserirAtorButton.setMnemonic(KeyEvent.VK_I);
		inserirAtorButton.setBounds(228, 295, 91, 23);
		artistaPanel.add(inserirAtorButton);
		
		limparAtorButton = new JButton("Limpar");
		limparAtorButton.setMnemonic(KeyEvent.VK_L);
		limparAtorButton.setBounds(338, 295, 91, 23);
		artistaPanel.add(limparAtorButton);
		
		JPanel informacaoPanel = new JPanel();
		informacaoPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacaoPanel.setBackground(new Color(87, 87, 87));
		informacaoPanel.setBounds(228, 11, 214, 200);
		artistaPanel.add(informacaoPanel);
		
		TratadorEventoInsereAtor eventoInsereAtor = new TratadorEventoInsereAtor(IgCadastrarFilme.this);
		atorField.addActionListener(eventoInsereAtor);
		inserirAtorButton.addActionListener(eventoInsereAtor);
		limparAtorButton.addActionListener(eventoInsereAtor);
		
		return artistaPanel;
	}
	
	private JPanel criaAbaPoster(){
		JPanel posterPanel = new JPanel();
		posterPanel.setBackground(new Color(87, 87, 87));
		posterPanel.setLayout(null);
		
		JPanel posterImagemPanel = new JPanel();
		posterImagemPanel.setBorder(new TitledBorder(null, "P\u00F4ster", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		posterImagemPanel.setBackground(new Color(87, 87, 87));
		posterImagemPanel.setBounds(10, 11, 190, 307);
		posterPanel.add(posterImagemPanel);
		posterImagemPanel.setLayout(null);
		
		posterLabel = new JLabel("");
		posterLabel.setBounds(10, 15, 171, 285);
		posterImagemPanel.add(posterLabel);
		
		JPanel informacoePanel = new JPanel();
		informacoePanel.setBackground(new Color(87, 87, 87));
		informacoePanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacoePanel.setBounds(210, 11, 210, 185);
		posterPanel.add(informacoePanel);
		informacoePanel.setLayout(null);
		
		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(new Color(87, 87, 87));
		fotoPanel.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fotoPanel.setBounds(210, 207, 210, 61);
		posterPanel.add(fotoPanel);
		fotoPanel.setLayout(null);
		
		fotoField = new JTextField();
		fotoField.setEditable(false);
		fotoField.setBounds(10, 23, 190, 20);
		fotoPanel.add(fotoField);
		fotoField.setColumns(10);
		
		inserirFotoButton = new JButton("Inserir");
		inserirFotoButton.setBounds(210, 295, 91, 23);
		posterPanel.add(inserirFotoButton);
		
		limparFotoButton = new JButton("Limpar");
		limparFotoButton.setBounds(311, 295, 91, 23);
		posterPanel.add(limparFotoButton);
		
		inserirFotoButton.setMnemonic(KeyEvent.VK_I);
		limparFotoButton.setMnemonic(KeyEvent.VK_I);
		
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
	 * @return um <code>JTextArea</code>.
	 */
	public JTextArea getDiretorArea() {
		return diretorArea;
	}

	/**Retorna a Referencia do autorArea
	 * @return um <code>JTextArea</code>.
	 */
	public JTextArea getAutorArea() {
		return autorArea;
	}

	/**Retorna a Referencia do atorArea
	 * @return um <code>JTextArea</code>.
	 */
	public JTextArea getAtorArea() {
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
