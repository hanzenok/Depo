package org.ganza.repo.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.ganza.repo.model.Repo;

/**
 * Défini le comportement 
 * à la fermeture de fenêtre principale
 * de la vue
 * @author Ganza Mykhailo
 */
public class ExitController extends WindowAdapter{
	
	private Repo repo; //depôt
	
	/**
	 * Constructeur principale
	 * @param repo depôt
	 */
	public ExitController(Repo repo)
	{
		this.repo = repo;
	}
	
	/**
	 * Setter de depôt
	 * @param repo depôt
	 */
	public void setRepo(Repo repo)
	{
		this.repo = repo;
	}
	
	/**
	 * Vide la modele Repo
	 * et clôture la programme
	 */
    public void windowClosing(WindowEvent e) {
        
    	if(repo != null && repo.exists()) 
    		
    		repo.close();
    		System.exit(0);

    }
}
