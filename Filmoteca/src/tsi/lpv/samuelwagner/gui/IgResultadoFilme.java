package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class IgResultadoFilme extends JDialog {
	private Color corBase = new Color(17,17,17);
	private JLabel tituloLabel;
	
	public IgResultadoFilme(IgFilmoteca igFilmoteca) {
		setSize(682, 500);
		
		getContentPane().setBackground(corBase);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(corBase);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filme", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(10, 11, 156, 194);
		getContentPane().add(panel);
		
		tituloLabel = new JLabel("");
		tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tituloLabel.setForeground(Color.WHITE);
		tituloLabel.setBounds(198, 118, 374, 32);
		getContentPane().add(tituloLabel);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				IgResultadoFilme.this.dispose();
			}
		});
		
		setResizable(false);
		
		setModal(true);
		
		setLocationRelativeTo(igFilmoteca);
		
		setVisible(true);
	}
}//class IgResultadoFilme
