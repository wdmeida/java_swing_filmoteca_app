package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoCadastraFilme;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoInsereAtor;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoInsereAutor;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoInsereDiretor;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

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
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JDateChooser chooser;
	private JYearChooser jYearChooser;
	private JEditorPane sinopseEditorPane;
	private JButton inserirFotoButton;
	private JButton limparFotoButton;
	private JRadioButton dvdRadio;
	private JRadioButton arquivoDigitalRadio;
	private JRadioButton bluRayRadio;
	private JTextArea diretorArea;
	private JTextArea autorArea;
	private JTextArea atorArea;
	private JButton inserirDiretorButton;
	private JButton limparDiretorButton;
	private JButton limparAutorButton;
	private JButton inserirAutorButton;
	private JButton inserirAtorButton;
	private JButton limparAtorButton;

	public IgCadastrarFilme(IgFilmoteca igFilmoteca) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarFilme.class.getResource("/tsi/lpv/samuelwagner/imagens/movie61.png")));
		getContentPane().setBackground(new Color(87, 87, 87));
		setBackground(new Color(87, 87, 87));
		setTitle("Cadastrar Filme");
		
		setSize(465, 418);
		getContentPane().setLayout(null);
		
		JPanel panelSul = new JPanel();
		panelSul.setBackground(new Color(87, 87, 87));
		panelSul.setBounds(0, 356, 475, 35);
		getContentPane().add(panelSul);
		panelSul.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(obtemData());
		lblNewLabel.setBounds(10, 9, 149, 14);
		panelSul.add(lblNewLabel);
		
		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(273, 5, 83, 23);
		panelSul.add(cadastrarButton);
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(366, 5, 77, 23);
		panelSul.add(cancelarButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		
		tabbedPane.setBackground(new Color(87, 87, 87));
		tabbedPane.setBounds(0, 0, 457, 354);
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Filme", new ImageIcon(IgCadastrarFilme.class.getResource("/tsi/lpv/samuelwagner/imagens/movie47.png")), criaAbaFilme(), "F");
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
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(87, 87, 87));
		tabbedPane.addTab("Classifica\u00E7\u00E3o", null, panel_4, null);
		tabbedPane.setDisplayedMnemonicIndexAt(5, 4);
		tabbedPane.setMnemonicAt(5, 67);
		
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
		setVisible(true);
	}//IgCadastrarFilme()
	
	private JPanel criaAbaFilme(){

		Calendar cal = Calendar.getInstance();
		
		JPanel filmePanel = new JPanel();
		filmePanel.setBackground(new Color(87, 87, 87));
		filmePanel.setLayout(null);
		
		JPanel tituloPanel = new JPanel();
		tituloPanel.setBounds(10, 11, 215, 50);
		tituloPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Titulo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tituloPanel.setBackground(new Color(87, 87, 87));
		filmePanel.add(tituloPanel);
		tituloPanel.setLayout(null);
		
		tituloField = new JTextField();
		tituloField.setBounds(10, 21, 195, 20);
		tituloPanel.add(tituloField);
		tituloField.setColumns(10);
		
		JPanel paisPanel = new JPanel();
		paisPanel.setBounds(10, 72, 215, 50);
		paisPanel.setBorder(new TitledBorder(null, "Pais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paisPanel.setBackground(new Color(87, 87, 87));
		filmePanel.add(paisPanel);
		paisPanel.setLayout(null);
		
		paisField = new JTextField();
		paisField.setBounds(10, 21, 195, 20);
		paisPanel.add(paisField);
		paisField.setColumns(10);
		
		JPanel generoPanel = new JPanel();
		generoPanel.setBounds(10, 133, 215, 50);
		generoPanel.setBorder(new TitledBorder(null, "Genero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		generoPanel.setBackground(new Color(87, 87, 87));
		filmePanel.add(generoPanel);
		generoPanel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setSize(195, 21);
		comboBox.setLocation(10, 18);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Terror", "A\u00E7\u00E3o", "Suspense", "Comedia", "Aventura", "Roma\u00E7a"}));
		comboBox.setSelectedIndex(0);
		generoPanel.add(comboBox);
		
		JPanel anoPanel = new JPanel();
		anoPanel.setBounds(235, 72, 215, 50);
		anoPanel.setBorder(new TitledBorder(null, "Ano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		anoPanel.setBackground(new Color(87, 87, 87));
		filmePanel.add(anoPanel);
		anoPanel.setLayout(null);
		
		jYearChooser = new JYearChooser();
		jYearChooser.setSize(97, 20);
		jYearChooser.setLocation(49, 19);
		
		anoPanel.add(jYearChooser);
		
		JPanel anoLacamentoPanel = new JPanel(null);
		anoLacamentoPanel.setBounds(235, 11, 215, 50);
		anoLacamentoPanel.setBackground(new Color(87, 87, 87));
		anoLacamentoPanel.setBorder(new TitledBorder(null, "Ano de Lan\u00E7amento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		filmePanel.add(anoLacamentoPanel);
		chooser = new JDateChooser(cal.getTime());
		chooser.setSize(101, 20);
		chooser.setLocation(53, 19);
		
		anoLacamentoPanel.add(chooser);
		
		JPanel faixaEtariaPanel = new JPanel();
		faixaEtariaPanel.setBounds(10, 194, 215, 50);
		faixaEtariaPanel.setBorder(new TitledBorder(null, "Faixa Etaria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		faixaEtariaPanel.setBackground(new Color(87, 87, 87));
		filmePanel.add(faixaEtariaPanel);
		faixaEtariaPanel.setLayout(null);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Livre", " inadequado para menores de 10", " inadequado para menores de 12", " inadequado para menores de 14", " inadequado para menores de 16", " inadequado para menores de 18"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(10, 21, 195, 22);
		faixaEtariaPanel.add(comboBox_1);
		
		JPanel midiaPanel = new JPanel();
		midiaPanel.setBackground(new Color(87, 87, 87));
		midiaPanel.setBorder(new TitledBorder(null, "M\u00EDdia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		midiaPanel.setBounds(10, 255, 215, 71);
		filmePanel.add(midiaPanel);
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
		
		JPanel sinopsePanel = new JPanel();
		sinopsePanel.setBorder(new TitledBorder(null, "Sinopse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sinopsePanel.setBackground(new Color(87, 87, 87));
		sinopsePanel.setBounds(235, 133, 215, 193);
		filmePanel.add(sinopsePanel);
		sinopsePanel.setLayout(null);
		
		JScrollPane sinopseScrollPane = new JScrollPane();
		sinopseScrollPane.setBounds(10, 14, 195, 168);
		sinopseScrollPane.setBackground(new Color(87, 87, 87));
		sinopsePanel.add(sinopseScrollPane);
		
		sinopseEditorPane = new JEditorPane();
		sinopseEditorPane.setBackground(Color.WHITE);
		sinopseScrollPane.setViewportView(sinopseEditorPane);
		
		return filmePanel;
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
		diretorArea.setEditable(false);
		diretorArea.setLineWrap(true);
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
		posterImagemPanel.setBounds(10, 11, 222, 307);
		posterPanel.add(posterImagemPanel);
		posterImagemPanel.setLayout(null);
		
		JPanel informacoePanel = new JPanel();
		informacoePanel.setBackground(new Color(87, 87, 87));
		informacoePanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacoePanel.setBounds(242, 11, 210, 185);
		posterPanel.add(informacoePanel);
		informacoePanel.setLayout(null);
		
		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(new Color(87, 87, 87));
		fotoPanel.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fotoPanel.setBounds(242, 207, 210, 61);
		posterPanel.add(fotoPanel);
		fotoPanel.setLayout(null);
		
		fotoField = new JTextField();
		fotoField.setBounds(10, 23, 190, 20);
		fotoPanel.add(fotoField);
		fotoField.setColumns(10);
		
		inserirFotoButton = new JButton("Inserir");
		inserirFotoButton.setBounds(242, 295, 91, 23);
		posterPanel.add(inserirFotoButton);
		
		limparFotoButton = new JButton("Limpar");
		limparFotoButton.setBounds(351, 295, 91, 23);
		posterPanel.add(limparFotoButton);
		
		inserirFotoButton.setMnemonic(KeyEvent.VK_I);
		limparFotoButton.setMnemonic(KeyEvent.VK_I);
		
		return posterPanel;
	}

	public JTextField getTituloField() {
		return tituloField;
	}

	public JTextField getPaisField() {
		return paisField;
	}

	public JTextField getTextField() {
		return atorField;
	}

	public JTextField getFotoField() {
		return fotoField;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public JButton getCadastrarButton() {
		return cadastrarButton;
	}

	public JButton getCancelarButton() {
		return cancelarButton;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JComboBox getComboBox_1() {
		return comboBox_1;
	}

	public JDateChooser getChooser() {
		return chooser;
	}

	public JYearChooser getjYearChooser() {
		return jYearChooser;
	}

	public JEditorPane getSinopseEditorPane() {
		return sinopseEditorPane;
	}

	public JButton getInserirButton_1() {
		return inserirFotoButton;
	}

	public JButton getLimparButton_1() {
		return limparFotoButton;
	}
	
	public JRadioButton getDvdRadio() {
		return dvdRadio;
	}

	public JRadioButton getArquivoDigitalRadio() {
		return arquivoDigitalRadio;
	}

	public JRadioButton getBluRayRadio() {
		return bluRayRadio;
	}

	public JTextArea getDiretorArea() {
		return diretorArea;
	}

	public JTextArea getAutorArea() {
		return autorArea;
	}

	public JTextArea getAtorArea() {
		return atorArea;
	}
	
	public JButton getInserirDiretorButton() {
		return inserirDiretorButton;
	}

	public JButton getLimparDiretorButton() {
		return limparDiretorButton;
	}
	
	public JTextField getDiretorField() {
		return diretorField;
	}
	
	public JTextField getAutorField() {
		return autorField;
	}

	public JButton getLimparAutorButton() {
		return limparAutorButton;
	}

	public JButton getInserirAutorButton() {
		return inserirAutorButton;
	}

	public JTextField getAtorField() {
		return atorField;
	}

	public JButton getInserirAtorButton() {
		return inserirAtorButton;
	}

	public JButton getLimparAtorButton() {
		return limparAtorButton;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new IgCadastrarFilme(null);
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
