package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import org.ganza.repo.model.Repo;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

import net.lingala.zip4j.exception.ZipException;

public class MenuController implements ActionListener
{	
	private RepoView repo_view;
	private Repo repo;
	
	private DragController drag_controller;
	private ClickController click_controller;
	private ExitController exit_controller;
	private PopupController popup_controller;
	
	public MenuController(RepoView repo_view)
	{
		this.repo_view = repo_view;
		
		this.repo_view.setMenuController(this);
		
		popup_controller = new PopupController(repo, repo_view);
		repo_view.setPopupController(popup_controller);
		
		exit_controller = new ExitController(repo);
		repo_view.setExitController(exit_controller);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((JMenuItem)e.getSource()).getText();
		
		//menu "nouveau"
		if(item_name.equals("Nouveau"))
		{	
			System.out.println("Nouveau");
			
			//supprimer le repo existante
			if(repo != null && repo.exists())
				repo.close();
			
			//creation de nouveau repositoire
			repo = new Repo();
			try { repo.create(); } 
			catch (IOException e1) { e1.printStackTrace(); }
			
			//initaliser et activer le view
			repo_view.initialize();
			repo_view.setReady(true);
			
			//changer le titre de la fenetr
			repo_view.setTitle(repo.getName());
			
			//reinitialiser les controlleurs
			setup_controllers();
			
			System.out.println(repo.getPath());
			
			return;
		}
		
		//menu "Sauvegarder"
		if(item_name.equals("Sauvegarder"))
		{	
			if(repo.exists())
			{
				//dialog
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choisisez le fichier à sauvegarder");   
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
			
			return;
		}
		
		//menu "Ouvrir"
		if(item_name.equals("Ouvrir"))
		{
			//supprimer le repo existante
			if(repo != null && repo.exists())
				repo.close();
				
			//dialog
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choisisez un depôt à ouvrir");   
			int selection = fileChooser.showOpenDialog(repo_view);
			
			//fichier choisi
			if (selection == JFileChooser.APPROVE_OPTION) 
			{
			    File selected_file = fileChooser.getSelectedFile();
			    
			    //charger le dossier
			    try { repo.load(selected_file.getAbsolutePath()); } 
			    catch (ZipException | JDOMException | IOException e1) { e1.printStackTrace(); }
			}
			
			//reinirialiser la view et charger le contenu
			repo_view.initialize();
			repo_view.refresh(repo);
			repo_view.setTitle(repo.getName());
			
			//reinitialiser les controlleurs
			setup_controllers();
			
			return;
		}
		
		//menu "Fermer"
		if(item_name.equals("Fermer"))
		{
			//supprimer le repo existante
			if(repo != null && repo.exists())
				repo.close();
			
			//et reinitialiser le view
			repo_view.initialize();
			repo_view.setReady(false);
			
			return;
		}
	}
	
	public void setup_controllers()
	{
		//controlleur de drag&drop
		drag_controller = new DragController(repo);
		repo_view.setDragable(true);
		repo_view.setDragController(drag_controller);
		
		//controlleur de click
		click_controller = new ClickController(repo, repo_view);
		repo_view.setClickController(click_controller);
		
		//controlleur de sortie
		exit_controller.setRepo(repo);
		
		//controlleur de popup
		popup_controller.setRepo(repo);

	}

}
