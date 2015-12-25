package org.ganza.repo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

/**
 * RepoControlleur est un
 * superclasse hérite par cerains
 * controlleurs
 * Réalise la mise à jour de la vue
 * à partir de depôt
 * @author Ganza Mykhailo
 */
public class RepoController {
	
	/**
	 * Remit à jour la vue repo_view
	 * à partire de repo
	 * @param repo depôt
	 * @param repo_view la vue principale
	 */
	public void refreshRepoView(Repo repo, RepoView repo_view)
	{	
		//list model
		DefaultListModel<RepoFile> listModel = new DefaultListModel<RepoFile>();
		
		//fichiers
		ArrayList<RepoFile> repofiles = null;
		try { repofiles = repo.getRFiles(); } 
		catch (JDOMException | IOException e) {e.printStackTrace();}
		
		//chargement
		int i, n = repofiles.size();
		for(i=0; i<n; i++){
			
			listModel.addElement(repofiles.get(i));
		}
		
		//attribution
		repo_view.setListModel(listModel);
	}
}
