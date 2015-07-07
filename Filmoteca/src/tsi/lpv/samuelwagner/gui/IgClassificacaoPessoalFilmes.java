package tsi.lpv.samuelwagner.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.ListSelectionModel;
/**
 * A classe <code>IgClassificacaoPessoalFilmes</code> constrói a interface gráfica responsável por 
 * exibir os filmes ordenados pela classificação do usuário.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgClassificacaoPessoalFilmes extends JDialog {
	private JList<String> resultadoJList;
	private Color corBase = new Color(17,17,17);
	private Color corLista = new Color(114,124,115);

	/**
	 * Construtor da classe <code>IgClassificacaoPessoalFilmes</code>
	 * @param igFilmoteca <code>IgFilmoteca</code> com a referência do painel principal.
	 * @param filmes<code>String[]</code> com os elementos a serem exibidos.
	 */
	@SuppressWarnings("unchecked")
	public IgClassificacaoPessoalFilmes(IgFilmoteca igFilmoteca, String[] filmes) {
		setTitle("Classifica\u00E7\u00E3o Filmes");
		getContentPane().setBackground(corBase);
		//Define as propriedades da janela.
		setSize(764, 451);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		setLocationRelativeTo(igFilmoteca);
		
		JPanel tituloPanel = new JPanel();
		tituloPanel.setBounds(0, 11, 758, 56);
		getContentPane().add(tituloPanel);
		
		JLabel logoLabel = new JLabel("");
		tituloPanel.add(logoLabel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "Classifica\u00E7\u00E3o Pessoal", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		infoPanel.setBounds(10, 78, 738, 337);
		getContentPane().add(infoPanel);
		infoPanel.setBackground(corBase);
		infoPanel.setLayout(null);
		
		JScrollPane resultadoScrollPane = new JScrollPane();
		resultadoScrollPane.setBounds(6, 24, 726, 306);
		infoPanel.add(resultadoScrollPane);
		
		resultadoJList = new JList/*<String>*/(filmes);
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
	private class Cores extends JLabel implements ListCellRenderer{
		  public Cores(){
		    setOpaque(true);
		  }
		 
		  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		     
		    setText(value.toString());
		    setForeground(Color.WHITE);
		    resultadoJList.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
