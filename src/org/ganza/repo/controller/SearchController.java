package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;

import org.ganza.repo.model.Repo;
import org.ganza.repo.view.RepoView;
import org.ganza.repo.view.SearchView;

/**
 * SearchController gére 
 * le SearchView de recherche
 * des fichiers dans le depôt
 * @author Ganza Mykhailo
 */
public class SearchController extends RepoController implements ActionListener{
	
	private Repo repo; //depôt
	private RepoView repo_view; //la vue principale
	private SearchView search_view; //la vue de recherche
	
	private ArrayList<String> attributes; //liste des attributes xml 
	
	/**
	 * Constructeur principale
	 * 
	 * @param repo depôt
	 * @param repo_view la vue principale
	 * @param search_view la vue de recherche
	 */
	public SearchController(Repo repo, RepoView repo_view, SearchView search_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
		this.search_view = search_view;
		
		attributes = new ArrayList<String>();
		
	}
	
	/**
	 * Passe la requête de 
	 * recherche à model
	 * et remit à jour la vue
	 * Gérer le bouton et les 
	 * checkbox
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((AbstractButton) e.getSource()).getText();
		
		//button "Chercher"
		if(item_name.equals("Chercher"))
		{
			if(search_view.editorEmpty()) return;
			
			//passer la requete a Repo
			repo.setSearchRequest(search_view.getEditor(), attributes);
			
			//reinitilaiser la vue
			refreshRepoView(repo, repo_view);
		}
		
		//sinon il s'agit de checkbox
		else
		{
			boolean checked = ((JCheckBox)e.getSource()).isSelected();
			
			if(checked)
			{
				attributes.add(item_name);
			}
			else
			{
				attributes.remove(item_name);
			}
		}
	}

}
