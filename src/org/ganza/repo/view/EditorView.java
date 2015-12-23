package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.ganza.repo.controller.EditorController;
import org.ganza.repo.model.RepoFile;

public class EditorView extends JFrame{

	private static final long serialVersionUID = 380295441672869172L;
	
	private JPanel main_panel;
	private JList list;
	
	
	public EditorView()
	{
        //manneau principal
		main_panel = new JPanel(new GridBagLayout());
		
		setTitle("Edition des balises XML");
		setMinimumSize(new Dimension(250, 150));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] str = {"ad", "sdf"};
		show(str);
	}
	
	public void setEditorController(EditorController editor_controller)
	{
		
	}
	
	public void show(String[] attributes)
	{
		GridBagConstraints c = new GridBagConstraints();
		
		//label
		c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
		c.weightx = 100; c.fill = GridBagConstraints.HORIZONTAL;
		JLabel attr_label = new JLabel("Attributes:");
		main_panel.add(attr_label, c);
		
		//list
		c.gridx = 0; c.gridy = 1; c.gridwidth = 1; c.weightx = 2;
		list = new JList(attributes);
		main_panel.add(new JScrollPane(list), c);
		
		//textatea
		c.gridx = 1; c.gridy = 1; c.weightx = 3;
		c.fill = GridBagConstraints.BOTH;
		JTextArea editor = new JTextArea(5,7);
		main_panel.add(new JScrollPane(editor), c);
		
		//bouton
		c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.weightx = 100;
		JButton button = new JButton("Sauvegarder");
		main_panel.add(button, c);
		
		//ajout
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
}
