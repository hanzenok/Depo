package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		
		setMinimumSize(new Dimension(250, 150));
		setLocationByPlatform(true);
		
		//packing
		pack();
	}
	
	public void setEditorController(EditorController editor_controller)
	{
		
	}
	
	public void show(String[] attributes)
	{
		list = new JList(attributes);
		
		//ajout
		JScrollPane scroll = new JScrollPane(list);
//		scroll.setPreferredSize(new Dimension(400, 300));
		main_panel.add(scroll, BorderLayout.CENTER);
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
}
