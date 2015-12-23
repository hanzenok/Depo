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
	
	public PopupController(Repo repo, RepoView repo_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
	}
	
	public void setRepo(Repo repo)
	{
		this.repo = repo;
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
			repo.removeRFile(1);
			
			repo_view.initialize();
			repo_view.refresh(repo);
		
			return;
		}
		
	}

}
