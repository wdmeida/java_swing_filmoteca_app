package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoCadastraGenero;

/**
 * @author Samuel
 * @author Wagner
 */
public class IgCadastraGenero extends JDialog {
	private JButton adicionarButton;
	private JButton	cancelaButton;
	private JTextField areaDescricao;
	private static Color corPainel = new Color(87, 87, 87);

	/** Constrói a janela de cadastro de renda.
	 * @param tituloJanela <code>TituloJanela</code> referencia do titulo da janela.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> referencia da Janela Principal.
	 */
	public IgCadastraGenero(IgCadastrarFilme igCadastrarFilme) {
		//Define o titulo da Janela, o tamanho da Janela e o Layout utilizado na Janela.
		setTitle("Cadastra Genero.");
		setBounds(100, 100, 307, 108);
		setBackground(corPainel);
		//Cria um JLabel e define seu tamanho.
		JLabel descricaoLabel = new JLabel("Descrição: ");
		descricaoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		descricaoLabel.setBounds(10,16, 67,14);
	
		//Cria um JTextField e difene suas propriedades.
		areaDescricao = new JTextField();
		areaDescricao.setToolTipText("Informa a Descri\u00E7\u00E3o da Renda.");
		areaDescricao.setColumns(20);
		areaDescricao.setBounds(87, 13, 198,20);
		getContentPane().setLayout(null);
		
		//Adiciona o JLabel e o JTextField a Janela.
		getContentPane().add(descricaoLabel);
		getContentPane().add(areaDescricao);
		getContentPane().setBackground(corPainel);
		
		//Cria os JButton e adiciona eles a Janela.
		adicionarButton = new JButton("Add");
		adicionarButton.setBounds(97, 44, 90, 25);
		adicionarButton.setToolTipText("Cadastra a Renda.");
		cancelaButton = new JButton("Sair");
		cancelaButton.setBounds(195, 44, 90, 25);
		cancelaButton.setToolTipText("Cancela o Cadastro.");
		getContentPane().add(adicionarButton);
		getContentPane().add(cancelaButton);
		
		TratadorEventoCadastraGenero eventoCadastraGenero = new TratadorEventoCadastraGenero(igCadastrarFilme, IgCadastraGenero.this);
		//Cria o tratador de Evento e Adiciona ele aos Buttons
		adicionarButton.addActionListener(eventoCadastraGenero);
		cancelaButton.addActionListener(eventoCadastraGenero);
		//Define as teclas Mnemonic dos Buttons.
		adicionarButton.setMnemonic(KeyEvent.VK_A);
		cancelaButton.setMnemonic(KeyEvent.VK_S);
		
		//Define o tratador de evento quando o usuario clicar para fechar a janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		//Define se a Janela e redimensionável e visivel. 
		setModal(true);
		setLocationRelativeTo(igCadastrarFilme);
		setResizable(false);
		setVisible(true);
	}

	/**Retorna a referencia do adicionarButton.
	 * @return um <code>JButton</code>.
	 */
	public JButton getAdicionarButton() {
		return adicionarButton;
	}

	/**Retorna a referencia do cancelaButton.
	 * @return um <code>JButton</code>.
	 */
	public JButton getCancelaButton() {
		return cancelaButton;
	}

	/**Retorna a referencia da areaDescricao.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getAreaDescricao() {
		return areaDescricao;
	}
}
