package tsi.lpv.samuelwagner.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JButton;
/**
 * A classe <code>IgPesquisa</code> é responsável por construir a interface gráfica de pesquisa do aplicativo
 * Filmoteca.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgPesquisa extends JDialog {
	private JTextField pesquisaTextField;
	private Color corBase = new Color(17,17,17);

	/**
	 * Construtor da classe <code>IgPesquisar</code> responsável por construir a interface gráfica da janela.
	 */
	public IgPesquisa(String tipoPesquisa, String mensagem) {
		setSize(443,205);
		
		setTitle(tipoPesquisa);
		
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(corBase);
		
		//Cria o painel de pesquisa.
		JPanel pesquisaPanel = new JPanel();
		pesquisaPanel.setBackground(corBase);
		pesquisaPanel.setBorder(new TitledBorder(null, "Pesquisar", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pesquisaPanel.setBounds(10, 11, 418, 155);
		getContentPane().add(pesquisaPanel);
		pesquisaPanel.setLayout(null);
		
		//Cria o rótulo da pesquisa.
		JLabel pesquisaLabel = new JLabel(mensagem);
		pesquisaLabel.setForeground(Color.WHITE);
		pesquisaLabel.setBounds(10, 55, 99, 14);
		pesquisaPanel.add(pesquisaLabel);
		
		//Cria o campo de texto para realizar a pesquisa.
		pesquisaTextField = new JTextField();
		pesquisaTextField.setBounds(127, 52, 267, 20);
		pesquisaPanel.add(pesquisaTextField);
		pesquisaTextField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(206, 110, 89, 23);
		pesquisaPanel.add(btnPesquisar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(305, 110, 89, 23);
		pesquisaPanel.add(btnCancelar);
		
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
