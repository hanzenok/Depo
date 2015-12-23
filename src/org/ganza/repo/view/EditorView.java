package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
		main_panel = new JPanel(new BorderLayout());
		
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
		list = new JList(attributes);
		
		//labele
		JLabel attr_label = new JLabel("Attributes:");
		main_panel.add(attr_label, BorderLayout.NORTH);
		
		//list
		main_panel.add(new JScrollPane(list), BorderLayout.WEST);
		
		//textatea
		JTextArea editor = new JTextArea(5,7);
		main_panel.add(new JScrollPane(editor), BorderLayout.EAST);
		
		//bouton
		JButton button = new JButton("Sauvegarder");
		main_panel.add(button, BorderLayout.SOUTH);
		
		//ajout
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
}
