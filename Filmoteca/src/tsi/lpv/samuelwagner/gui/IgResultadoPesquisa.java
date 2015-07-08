package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoPesquisarFilme;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
/**
 * A classe <code>IgResultadoPesquisa</code> é responsável por construir a janela de exibição de resultados das
 * pesquisas.
 * @author Wagner Almeida
 * @author Samuel Gonçalves.
 *
 */
public class IgResultadoPesquisa extends JDialog {
	private IgFilmoteca igFilmoteca;
	private JList<String> resultadoList;
	private Color corBase =  new Color(17, 17, 17);
	private Color corLista = new Color(114,124,115);
	
	@SuppressWarnings("unchecked")
	public IgResultadoPesquisa(IgFilmoteca igFilmoteca, String tituloPesquisa, DefaultListModel<String> valores) {
		this.igFilmoteca = igFilmoteca;
		
		setTitle("Pesquisar");
		
		//Define as propriedades da janela.
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgResultadoPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/Old_3d_glasses_16.png")));
		setSize(442, 395);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(corBase);
		setLocationRelativeTo(igFilmoteca);
		
		//Cria o painel para exibição do resultado
		JPanel resultadoPanel = new JPanel();
		resultadoPanel.setBackground(corBase);
		resultadoPanel.setBorder(new TitledBorder(null, tituloPesquisa, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		resultadoPanel.setBounds(10, 11, 406, 284);
		getContentPane().add(resultadoPanel);
		resultadoPanel.setLayout(new BorderLayout(0, 0));
		
		//Cria o painel rolável.
		JScrollPane resultadoScrollPane = new JScrollPane();
		resultadoPanel.setBackground(corBase);
		resultadoPanel.add(resultadoScrollPane, BorderLayout.CENTER);
		
		//Cria o jlist para exibição dos resultados.
		resultadoList = new JList<String>(valores);
		resultadoScrollPane.setViewportView(resultadoList);
		resultadoList.setBackground(corBase);
		resultadoList.setCellRenderer(new Cores());
		
		//Cria o botão de visualização de detalhes do filme.
		JButton visualizarDetalhesButton = new JButton("Visualizar");
		visualizarDetalhesButton.setIcon(new ImageIcon(IgResultadoPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/movie47.png")));
		visualizarDetalhesButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		visualizarDetalhesButton.setBounds(139, 313, 137, 33);
		getContentPane().add(visualizarDetalhesButton);
		
		//Registra o tratador de eventos do botão visualizarDetalhes;
		visualizarDetalhesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exibirDetalhes();
			}
		});
		
		//Cria e registra o tratador de eventos do botão sair.
		JButton sairButton = new JButton("Sair");
		sairButton.setIcon(new ImageIcon(IgResultadoPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/Film_reel_circular_shape_16.png")));
		sairButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		sairButton.setBounds(286, 313, 130, 33);
		getContentPane().add(sairButton);
		
		//Registra o tratador de eventos do botão sair.
		sairButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IgResultadoPesquisa.this.dispose();
			}
		});
		
		setVisible(true);
	}//class IgResultadoPesquisa
	
	/**
	 * Exibe os detalhes do filme selecionado.
	 */
	private void exibirDetalhes() {
		if(resultadoList.isSelectionEmpty()){new IgMensagem(igFilmoteca, "Você deve selecionar um item primeiro");}
		else new TratadorEventoPesquisarFilme(igFilmoteca, resultadoList.getSelectedValue()).pesquisarFilme();
	}//exibirDetalhes
	
	/**
	 * Cria a classe interna responsável por alterar as cores do jlist.
	 * @author Wagner Almeida
	 * @author Samuel Gonçalves
	 *
	 */
	@SuppressWarnings("rawtypes")
	public class Cores extends JLabel implements ListCellRenderer{
		  public Cores(){
		    setOpaque(true);
		  }
		  
		  //Obtém a referência do objeto a ser modificado.
		  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		     
		    setText(value.toString());
		    setForeground(Color.WHITE);
		    resultadoList.setFont(new Font("Tahoma", Font.BOLD, 14));
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
}//class IgResultadoPesquisa
