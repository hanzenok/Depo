package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.EditorView;
import org.jdom2.JDOMException;

/**
 * EditorController contrôle 
 * la vue EditorView d'édition des 
 * attributes xml d'un fichier donnée
 * Travaile avec les événements de button 
 * et la liste
 * @author Ganza Mykhailo
 */
public class EditorController implements ActionListener,ListSelectionListener{
	
	private RepoFile repo_file; //ficher
	private EditorView editor_view; //la vue d'un editor
	
	private JList<String> list; //liste avec les attributes xml
	
	private int index; //index d'un element de JList choisi
	
	/**
	 * Constructeur principle
	 * 
	 * @param repo_file ficher
	 * @param editor_view vue d'editor
	 */
	public EditorController(RepoFile repo_file, EditorView editor_view)
	{
		this.repo_file = repo_file;
		this.editor_view = editor_view;
		
		index = -1;
	}	

	/**
	 * Définit de la liste de la vue
	 * 
	 * @param list liste 
	 */
	public void setList(JList<String> list)
	{
		this.list = list;
	}
	
	/**
	 * Sauvegrde le valeur d'un attribut xml
	 * dans le fichier xml
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String element = list.getSelectedValue();
		String value = editor_view.getValue();
		
		try { repo_file.setAttributeValue(element, value); } 
		catch (JDOMException | IOException e1) { e1.printStackTrace(); }
		
	}
	
	/**
	 * Rémit à jour la EditorView
	 */
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
			
			//desactiver l'editor si il s'agit de l'attribut de nom
			editor_view.setEditorEditable(!list.getSelectedValue().equals("name"));
		} 
		catch (JDOMException | IOException e) { e.printStackTrace(); }
	}
	
	

}
