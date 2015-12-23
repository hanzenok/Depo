package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.ganza.repo.model.Repo;
import org.ganza.repo.view.RepoView;

public class PopupController implements ActionListener
{
	private Repo repo;
	private RepoView repo_view;
	private ClickController click_controller;
	
	public PopupController(Repo repo, RepoView repo_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
		
	}
	
	public void setRepo(Repo repo)
	{
		this.repo = repo;
	}
	
	public void setClickController(ClickController click_controller)
	{
		this.click_controller = click_controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((JMenuItem)e.getSource()).getText();
		
		//popup menu "Reinsegner XML"
		if(item_name.equals("Reinsegner XML"))
		{
			return;
		}
		               
		//popup menu "Modifier XML"
		if(item_name.equals("Modifier XML"))
		{
			return;
		}
		
		//popup menu "Supprimer"
		if(item_name.equals("Supprimer"))
		{
			//supprimer le fichier
			repo.removeRFile(click_controller.getIndex());
			
			//reinitilaiser la vue
			repo_view.initialize();
			repo_view.refresh(repo);
			repo_view.setTitle(repo.getName());
			
			//reinitialiser le contrlleur de click
			//car le JList a ete modifiee
			repo_view.setClickController(click_controller);
			
			return;
		}
		
	}

}
