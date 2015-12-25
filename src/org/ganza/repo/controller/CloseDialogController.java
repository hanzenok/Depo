package org.ganza.repo.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.ganza.repo.view.RepoView;

/**
 * Gérer les événements de fermeture
 * des dialogs SearchView et FilterView
 * 
 * Sert à réactiver la vue principale
 * RepoView
 * @author Ganza Mykhailo
 */
public class CloseDialogController extends WindowAdapter
{
	private RepoView repo_view; //vue principale
	
	/**
	 * Constructeur principale
	 * @param repo_view la vue
	 */
	public CloseDialogController(RepoView repo_view)
	{
		this.repo_view = repo_view;
	}
	
	/**
	 * Desactive la vue principale
	 */
    public void windowClosing(WindowEvent e) {
        
    	repo_view.setEnabled(true);

    }
}
