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
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((JMenuItem)e.getSource()).getText();
		
		System.out.println(item_name);
		
//		//menu ""
//		if(item_name.equals("Nouveau"))
//		{
//			
//		}
		
	}

}
