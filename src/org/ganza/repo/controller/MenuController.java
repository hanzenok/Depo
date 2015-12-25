package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.FilterView;
import org.ganza.repo.view.RepoView;
import org.ganza.repo.view.SearchView;
import org.jdom2.JDOMException;

import net.lingala.zip4j.exception.ZipException;

public class MenuController extends RepoController implements ActionListener
{	
	private RepoView repo_view;
	private Repo repo;
	
	private DragController drag_controller;
	private ClickController click_controller;
	private ExitController exit_controller;
	private PopupController popup_controller;
	private CloseDialogController dialog_controller;
	
	public MenuController(RepoView repo_view)
	{
		this.repo_view = repo_view;
		
		this.repo_view.setMenuController(this);
		
		popup_controller = new PopupController(repo, repo_view);
		repo_view.setPopupController(popup_controller);
		
		exit_controller = new ExitController(repo);
		repo_view.setExitController(exit_controller);
		
		dialog_controller = new CloseDialogController(this.repo_view);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((JMenuItem)e.getSource()).getText();
		
		//menu "nouveau"
		if(item_name.equals("Nouveau"))
		{	
			//supprimer un depot existante
			if(repo != null && repo.exists())
				repo.close();
			
			//creation de nouveau repositoire
			repo = new Repo();
			try { repo.create(); } 
			catch (IOException e1) { e1.printStackTrace(); }
			
			//initaliser et activer le view
			repo_view.initialize(repo.getName());
			repo_view.setReady(true);
			
			//reinitialiser les controlleurs
			setup_controllers();
			
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
			//supprimer un depot existante
			if(repo != null && repo.exists())
				repo.close();
			
			//creer un nouveau depot
			repo = new Repo();
				
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
			
				//reinirialiser la view et charger le contenu
				repo_view.initialize(repo.getName());
				repo_view.setReady(true);
				repo.setNoSearching();
				refreshRepoView(repo, repo_view);
				
				
				//reinitialiser les controlleurs
				setup_controllers();
				
			}
			
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
		
		//menu "Appliquer" filtre
		if(item_name.equals("Appliquer"))
		{
			FilterView filter_view = new FilterView();
			
			FilterController filter_controller = new FilterController(repo, repo_view, filter_view);
			filter_view.show(repo.getAcceptedExtensions());
			
			filter_view.setFilterController(filter_controller);
			filter_view.setCloseDialogController(dialog_controller);
			
			//desactiver la vue principale
			repo_view.setEnabled(false);
			
			return;
		}
		
		//menu "Lancer" recherche
		if(item_name.equals("Lancer"))
		{
			SearchView search_view = new SearchView();
			
			SearchController search_controller = new SearchController(repo, repo_view, search_view);
			try { search_view.show(repo.getAttributes()); } 
			catch (JDOMException | IOException e1) {e1.printStackTrace();}
			
			search_view.setSearchController(search_controller);
			search_view.setCloseDialogController(dialog_controller);
			
			//desactiver la vue principale
			repo_view.setEnabled(false);
			
			return;
		}
		
		//menu "Annuler" recherche
		if(item_name.equals("Annuler"))
		{
			repo.setNoSearching();
			refreshRepoView(repo, repo_view);
			
			return;
		}
		
		//menu "Définir l'auteur"
		if(item_name.equals("Définir l'auteur"))
		{
			String author = JOptionPane.showInputDialog(repo_view, "Auteur: ", "Définir l'auteur", JOptionPane.QUESTION_MESSAGE);
			
			try { repo.setAuthor(author); } 
			catch (JDOMException | IOException e1) {e1.printStackTrace();}
		}
	}
	
	public void setup_controllers()
	{
		//controlleur de drag&drop
		drag_controller = new DragController(repo, repo_view);
		repo_view.setDragable(true);
		repo_view.setDragController(drag_controller);
		
		//controlleur de click
		click_controller = new ClickController(repo, repo_view);
		repo_view.setClickController(click_controller);
		
		//controlleur de sortie
		exit_controller.setRepo(repo);
		
		//controlleur de popup
		popup_controller.setRepo(repo);
		popup_controller.setClickController(click_controller);
		popup_controller.setDragController(drag_controller);
		
		dialog_controller = new CloseDialogController(repo_view);

	}

}
