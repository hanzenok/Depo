package org.ganza.repo.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.ganza.repo.controller.SearchController;

public class SearchView extends JFrame{

	private static final long serialVersionUID = 5346803320843425078L;
	
	private JPanel main_panel;
	
	private JTextArea editor;
	private JButton button_search;
	private ArrayList<JCheckBox> boxes;
	
	public SearchView()
	{
        //panneau principal
		main_panel = new JPanel(new GridBagLayout());
		
		setTitle("Recherche");
		setMinimumSize(new Dimension(250, 150));
		setLocationByPlatform(true);
		
		editor = new JTextArea(3, 8);
		button_search = new JButton("Chercher");
	}
	
	public void show(ArrayList<String> attributes)
	{
		GridBagConstraints c = new GridBagConstraints();
		
		main_panel = new JPanel(new GridBagLayout());
		
		//editor
		c.gridx = 0; c.gridy = 0; c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		main_panel.add(editor, c);
		
		//checkboxes
		c.gridy = 1;
		boxes = new ArrayList<JCheckBox>();
		JPanel boxes_panel = new JPanel(new FlowLayout());
		
		for(String attribut : attributes)
		{	
			boxes.add(new JCheckBox(attribut));
			boxes_panel.add(boxes.get(boxes.size() - 1));
		}
		
		JScrollPane scroll = new JScrollPane(boxes_panel);
		scroll.setPreferredSize(new Dimension(50, 50));
		main_panel.add(scroll, c);
		
		//button recherche
		c.gridy = 2;
		main_panel.add(button_search, c);
		
		//ajout
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
	
	public void setSearchController(SearchController search_controller)
	{
		button_search.addActionListener(search_controller);
		
		for(JCheckBox box : boxes)
		{
			box.addActionListener(search_controller);
		}
	}
	
	public boolean editorEmpty(){
		
		return editor.getText().length() == 0;
	}
	
	public String getEditor()
	{
		return editor.getText();
	}	

}
