package org.ganza.repo.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.RepoView;

public class RepoController {
	
	public void refreshRepoView(Repo repo, RepoView repo_view)
	{	
		//list model
		DefaultListModel<RepoFile> listModel = new DefaultListModel<RepoFile>();
		ArrayList<RepoFile> repofiles = repo.getRFiles();
		
		//chargement
		int i, n = repofiles.size(); System.out.println("Size: " + n);
		for(i=0; i<n; i++){
			
			listModel.addElement(repofiles.get(i));
		}
		
		//attribution
		repo_view.setListModel(listModel);
	}
}
