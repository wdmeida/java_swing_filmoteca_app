package tsi.lpv.samuelwagner.gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;

public class IgFilmoteca extends JFrame {

	public IgFilmoteca() {
		super("Filmoteca");
		setResizable(false);
		
		setSize(720, 460);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		setVisible(true);
	}
}
