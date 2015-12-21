package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import org.ganza.repo.model.Repo;
import org.ganza.repo.view.RepoView;

import net.lingala.zip4j.exception.ZipException;

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
		
		//menu "nouveau"
		if(item_name.equals("Nouveau"))
		{	
			//creation de nouveau repositoire
			repo = new Repo();
			try { repo.create(); } 
			catch (IOException e1) { e1.printStackTrace(); }
			
			//initaliser et activer le view
			repo_view.initialize();
			repo_view.setReady();
			
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
		
		//menu "Sauvegarder"
		if(item_name.equals("Sauvegarder"))
		{	System.out.println("Yoooo");
			if(repo.exists())
			{
				//dialog
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				int selection = fileChooser.showSaveDialog(repo_view);
				
				//fichier choisi
				if (selection == JFileChooser.APPROVE_OPTION) 
				{
				    File selected_file = fileChooser.getSelectedFile();
				    
				    //sauvegarde
				    try { repo.save(selected_file); } 
				    catch (ZipException e1) { e1.printStackTrace(); }
				}
			}
		}
	}

}
