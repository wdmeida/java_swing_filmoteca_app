package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoCadastraAAD;

/**
 * A classe <code>IgCadastraAtorAutorDiretor</code> constrói a interface gráfica responsável pelo cadastro de um novo 
 * filme e seus atributos.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class IgCadastraAtorAutorDiretor extends JDialog {
	private JButton procurarButton;
	private JButton	cancelaButton;
	private JTextField areaDescricao;
	private static Color corPainel = new Color(87, 87, 87);
	
	/**
	 * Construtor default da classe <code>IgCadastraAtorAutorDiretor</code>.
	 * @param igFilmoteca <code>IgFilmoteca</code> com a referência da classe principal.
	 */
	public IgCadastraAtorAutorDiretor(IgFilmoteca igFilmoteca) {
		//Define o titulo da Janela, o tamanho da Janela e o Layout utilizado na Janela.
				setTitle("Cadastra Autor, Ator e Diretor.");
				setBounds(100, 100, 307, 108);
				setBackground(corPainel);
				//Cria um JLabel e define seu tamanho.
				JLabel descricaoLabel = new JLabel("Filme: ");
				descricaoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
				descricaoLabel.setBounds(10,16, 67,14);
			
				//Cria um JTextField e difene suas propriedades.
				areaDescricao = new JTextField();
				areaDescricao.setToolTipText("Informa o Nome do Filme.");
				areaDescricao.setColumns(20);
				areaDescricao.setBounds(87, 13, 198,20);
				getContentPane().setLayout(null);
				
				//Adiciona o JLabel e o JTextField a Janela.
				getContentPane().add(descricaoLabel);
				getContentPane().add(areaDescricao);
				getContentPane().setBackground(corPainel);
				
				//Cria os JButton e adiciona eles a Janela.
				procurarButton = new JButton("Procurar");
				procurarButton.setBounds(97, 44, 90, 25);
				procurarButton.setToolTipText("Procurar Filme.");
				cancelaButton = new JButton("Sair");
				cancelaButton.setBounds(195, 44, 90, 25);
				cancelaButton.setToolTipText("Cancela a procura.");
				getContentPane().add(procurarButton);
				getContentPane().add(cancelaButton);
				
				//Cria o tratador de Evento e Adiciona ele aos Buttons
				//Define as teclas Mnemonic dos Buttons.
				procurarButton.setMnemonic(KeyEvent.VK_P);
				cancelaButton.setMnemonic(KeyEvent.VK_S);
				
				//Define o tratador de evento quando o usuario clicar para fechar a janela.
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						IgCadastraAtorAutorDiretor.this.dispose();
					}
				});
				
				IgCadastrarFilme igCadastrarFilme = new IgCadastrarFilme(igFilmoteca,false);
				
				procurarButton.addActionListener(new TratadorEventoCadastraAAD(igCadastrarFilme, IgCadastraAtorAutorDiretor.this));
				
				cancelaButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						IgCadastraAtorAutorDiretor.this.dispose();
					}
				});
				
				//Define se a Janela e redimensionável e visivel. 
				setModal(true);
				setLocationRelativeTo(igFilmoteca);
				setResizable(false);
				setVisible(true);
			}
	
	/**
	 * Obtém a referência do <code>JTextFeild</code> da descrição.
	 * @return <code>JTextField</code>
	 */
	public JTextField getAreaDescricao() {
		return areaDescricao;
	}
	
}
