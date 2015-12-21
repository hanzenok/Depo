package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;

import org.ganza.repo.model.Repo;
import org.ganza.repo.view.RepoView;

public class MenuController implements ActionListener
{	
	
	private RepoView repo_view;
	private Repo repo;
	
	private DragController drag_controller;
	private ExitController exit_controller;
	
	public MenuController(RepoView repo_view)
	{
		this.repo_view = repo_view;
		
		this.repo_view.setMenuController(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((JMenuItem)e.getSource()).getText();
		
		//new menu
		if(item_name.equals("New"))
		{	
			//creation de nouveau repositoire
			repo = new Repo();
			try { repo.create(); } 
			catch (IOException e1) { e1.printStackTrace(); }
			
			//initaliser le view
			repo_view.initialize();
			
			//changer le titre de la fenetr
			repo_view.setTitle(repo.getName());
			
			//devient dragable
			drag_controller = new DragController(repo);
			repo_view.setDragable(true);
			repo_view.setDragController(drag_controller);
			
			//controlleur de sortie
			exit_controller = new ExitController(repo);
			this.repo_view.setExitController(exit_controller);
		}
	}

}
