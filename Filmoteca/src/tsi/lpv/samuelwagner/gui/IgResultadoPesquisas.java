package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
/**
 * A classe <code>IgResultadoPesquisas</code> é responsável por construir a interface gráfica responsável
 * por exibir o resultado das pesquisas.
 * 
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgResultadoPesquisas extends JDialog {
	private Color corBase = new Color(17,17,17);
	private Color corLista = new Color(114,124,115);
	
	/**
	 * Construtor da classe <code>IgResultadoPesquisas</code>
	 * @param titulo <code>String</code> com o titulo da janela.
	 * @param mensagem <code>String</code> com a mensagem da janela.
	 * @param componente <code>Component</code> com a referência da janela ao qual deve se posicionar.
	 */
	public IgResultadoPesquisas(String titulo, String mensagem, Component componente) {
		//Define o tamanho.
		setSize(400, 500);
		
		//Define a janela como não redimensionável.
		setResizable(false);
		
		setLocationRelativeTo(componente);
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgResultadoPesquisas.this.dispose();
			}
		});
		
		//Define a janela como modal.
		setModal(true);
	}//IgResultadoPesquisas
	
}//class IgResultadoPesquisa
