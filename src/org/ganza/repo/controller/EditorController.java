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
	
	public EditorController(RepoFile repo_file, EditorView editor_view)
	{
		this.repo_file = repo_file;
		this.editor_view = editor_view;
	}	

	public void setList(JList<String> list)
	{
		this.list = list;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent le) 
	{	
        if (le.getValueIsAdjusting()) return;
        
        
		try {
			//reccuperer le valeur de l'attribut chosisi
			String value = repo_file.getAttributeValue(list.getSelectedIndex());
			
			//l'afficher
			editor_view.showValue(value);
		} 
		catch (JDOMException | IOException e) { e.printStackTrace(); }
		

		
	}
	
	

}
