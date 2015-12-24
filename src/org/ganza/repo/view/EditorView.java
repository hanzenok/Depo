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
import javax.swing.ListSelectionModel;

import org.ganza.repo.controller.EditorController;
import org.ganza.repo.model.RepoFile;

public class EditorView extends JFrame{

	private static final long serialVersionUID = 380295441672869172L;
	
	private JPanel main_panel;
	
	private JTextArea editor;
	private JButton button;
	private JList<String> list;
	
	
	public EditorView()
	{
        //pnneau principal
		main_panel = new JPanel(new GridBagLayout());
		
		setTitle("Edition des balises XML");
		setMinimumSize(new Dimension(250, 150));
		setLocationByPlatform(true);
	}
	
	public void setEditorController(EditorController editor_controller)
	{	
		editor_controller.setList(list);
		
		list.addListSelectionListener(editor_controller);
		button.addActionListener(editor_controller);
		
		list.setSelectedIndex(0);//choisir par defaut
		
	}
	
	public void show(String[] attributes)
	{
		GridBagConstraints c = new GridBagConstraints();
		
		//label attr
		c.gridx = 0; c.gridy = 0; c.gridwidth = 1;
		c.weightx = 2; c.fill = GridBagConstraints.HORIZONTAL;
		JLabel attr_label = new JLabel("Attributes:");
		main_panel.add(attr_label, c);
		
		//label value
		c.gridx = 1; c.gridy = 0; c.gridwidth = 1;
		c.weightx = 4; 
		JLabel attr_value = new JLabel("Valeur:");
		main_panel.add(attr_value, c);
		
		//list
		c.gridx = 0; c.gridy = 1; c.gridwidth = 1; c.weightx = 2;
		list = new JList<String>(attributes); 
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		main_panel.add(new JScrollPane(list), c);
		
		//textatea
		c.gridx = 1; c.gridy = 1; c.weightx = 4;
		c.fill = GridBagConstraints.BOTH;
		editor = new JTextArea(5,7);
		editor.setLineWrap(true);
		main_panel.add(new JScrollPane(editor), c);
		
		//bouton
		c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.weightx = 100;
		button = new JButton("Sauvegarder");
		main_panel.add(button, c);
		
		//ajout
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
	
	public void showValue(String value)
	{
		editor.setText(value);
	}
	
	public String getValue()
	{
		return editor.getText();
	}
}
