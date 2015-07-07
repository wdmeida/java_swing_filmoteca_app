package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoPesquisarFilme;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class IgResultadoPesquisa extends JDialog {
	
	
	public IgResultadoPesquisa() {
		setSize(442, 395);
		getContentPane().setLayout(null);
		
		JPanel resultadoPanel = new JPanel();
		resultadoPanel.setBounds(10, 11, 406, 272);
		getContentPane().add(resultadoPanel);
		resultadoPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane resultadoScrollPane = new JScrollPane();
		resultadoPanel.add(resultadoScrollPane, BorderLayout.CENTER);
		
		JList list = new JList();
		resultadoScrollPane.setViewportView(list);
	}
}//class IgResultadoPesquisa
