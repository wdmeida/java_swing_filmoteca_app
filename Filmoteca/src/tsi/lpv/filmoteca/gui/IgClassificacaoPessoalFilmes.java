package tsi.lpv.filmoteca.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;
/**
 * A classe <code>IgClassificacaoPessoalFilmes</code> constrói a interface gráfica responsável por 
 * exibir os filmes ordenados pela classificação do usuário.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgClassificacaoPessoalFilmes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> resultadoJList;
	private Color corBase = new Color(17,17,17);
	private Color corLista = new Color(114,124,115);
	private Color corSubMenu = new Color(124,61,139);

	/**
	 * Construtor da classe <code>IgClassificacaoPessoalFilmes</code>
	 * @param igFilmoteca <code>IgFilmoteca</code> com a referência do painel principal.
	 * @param filmes<code>String[]</code> com os elementos a serem exibidos.
	 */
	@SuppressWarnings("unchecked")
	public IgClassificacaoPessoalFilmes(IgFilmoteca igFilmoteca, String[] filmes) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgClassificacaoPessoalFilmes.class.getResource("/tsi/lpv/samuelwagner/imagens/star.png")));
		setTitle("Classifica\u00E7\u00E3o Filmes");
		getContentPane().setBackground(corBase);
		
		//Define as propriedades da janela.
		setSize(670, 451);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		setLocationRelativeTo(igFilmoteca);
		
		//Cria o painel de titulo.
		JPanel tituloPanel = new JPanel();
		tituloPanel.setBounds(0, 11, 664, 56);
		getContentPane().add(tituloPanel);
		tituloPanel.setBackground(corSubMenu);
		tituloPanel.setLayout(null);
		
		//Cria o logo.
		JLabel logoLabel = new JLabel("Classifica\u00E7\u00E3o Pessoal");
		logoLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		logoLabel.setIcon(new ImageIcon(IgClassificacaoPessoalFilmes.class.getResource("/tsi/lpv/samuelwagner/imagens/star.png")));
		logoLabel.setForeground(Color.WHITE);
		logoLabel.setBounds(10, 5, 306, 45);
		tituloPanel.add(logoLabel);
		
		//Cria o painel de informações.
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "Classifica\u00E7\u00E3o Pessoal", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		infoPanel.setBounds(10, 78, 644, 337);
		getContentPane().add(infoPanel);
		infoPanel.setBackground(corBase);
		infoPanel.setLayout(null);
		
		//Cria o painel rolável.
		JScrollPane resultadoScrollPane = new JScrollPane();
		resultadoScrollPane.setBounds(10, 24, 624, 306);
		infoPanel.add(resultadoScrollPane);
		
		//Cria o JList que exibirá os filmes por ordem de classificação.
		resultadoJList = new JList<String>(filmes);
		resultadoJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultadoJList.setBackground(Color.BLACK);
		resultadoJList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resultadoJList.setCellRenderer(new Cores());
		resultadoScrollPane.setViewportView(resultadoJList);
		resultadoJList.setForeground(Color.WHITE);
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgClassificacaoPessoalFilmes.this.dispose();
			}
		});
		
		setVisible(true);
	}//IgClassificacaoPessoalFilmes
	
	/**
	 * Cria a classe interna responsável por alterar as cores do jlist.
	 * @author Wagner Almeida
	 * @author Samuel Gonçalves
	 *
	 */
	@SuppressWarnings("rawtypes")
	public class Cores extends JLabel implements ListCellRenderer{
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
}//class IgClassificacaoPessoalFilmes
