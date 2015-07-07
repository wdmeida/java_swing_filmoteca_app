package tsi.lpv.samuelwagner.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * A classe <code>IgPesquisa</code> é responsável por construir a interface gráfica de pesquisa do aplicativo
 * Filmoteca.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgPesquisa extends JDialog {
	private JTextField pesquisaTextField;

	public IgPesquisa() {
		setSize(528,294);
		
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel pesquisaPanel = new JPanel();
		pesquisaPanel.setBounds(10, 11, 502, 244);
		getContentPane().add(pesquisaPanel);
		pesquisaPanel.setLayout(null);
		
		JLabel pesquisaLabel = new JLabel("Tipo de pesquisa:");
		pesquisaLabel.setBounds(10, 67, 99, 14);
		pesquisaPanel.add(pesquisaLabel);
		
		pesquisaTextField = new JTextField();
		pesquisaTextField.setBounds(127, 64, 179, 20);
		pesquisaPanel.add(pesquisaTextField);
		pesquisaTextField.setColumns(10);
		
		//Registra o tratador de eventos da janela de pesquisa.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgPesquisa.this.dispose();
			}
		});
		
		setVisible(true);
	}
}//class IgPesquisa
