package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.EditorView;
import org.jdom2.JDOMException;

public class EditorController implements ActionListener,ListSelectionListener{
	
	private RepoFile repo_file;
	private EditorView editor_view;
	
	private JList<String> list;
	
	private int index; //index d'un element de JList choisi
	
	public EditorController(RepoFile repo_file, EditorView editor_view)
	{
		this.repo_file = repo_file;
		this.editor_view = editor_view;
		
		index = -1;
	}	

	public void setList(JList<String> list)
	{
		this.list = list;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String element = list.getSelectedValue();
		String value = editor_view.getValue();
		
		try {
			repo_file.setAttributeValue(element, value);
		} catch (JDOMException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent le) 
	{	
        if (le.getValueIsAdjusting()) return;
        
        
		try {
			
			//reccuperer l'index d'element de liste choisi
			index = list.getSelectedIndex();
			
			//son valeur
			String value = repo_file.getAttributeValue(index);
			
			//l'afficher
			editor_view.showValue(value);
		} 
		catch (JDOMException | IOException e) { e.printStackTrace(); }
		

		
	}
	
	

}
