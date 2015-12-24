package org.ganza.repo.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;
import org.ganza.repo.view.EditorView;

public class PopupController extends RepoController implements ActionListener
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
			RepoFile repo_file = repo.getRFiles().get(click_controller.getIndex());
			
			EditorView editor_view = new EditorView();
			EditorController editor_controller = new EditorController(repo_file, editor_view);
			
			try { editor_view.show(repo_file.getAttributes()); } 
			catch (JDOMException | IOException e1) { e1.printStackTrace(); }
			
			editor_view.setEditorController(editor_controller);
			
			return;
		}
		               
		//popup menu "Modifier XML"
		if(item_name.equals("Modifier XML"))
		{
    		//reccuperer le fichier xml
    		File xml_file = repo.getRFiles().get(click_controller.getIndex()).getMetaFile();
    		
    		//ouvrir dans la systeme
    		try { Desktop.getDesktop().open(xml_file); } 
    		catch (IOException e1) { e1.printStackTrace();}
			
			return;
		}
		
		//popup menu "Supprimer"
		if(item_name.equals("Supprimer"))
		{
			//supprimer le fichier
			repo.removeRFile(click_controller.getFileName());
			
			//reinitilaiser la vue
			repo_view.initialize();
			refreshRepoView(repo, repo_view);
			repo_view.setTitle(repo.getName());
			
			//reinitialiser le contrlleur de click
			//car le JList a ete modifiee
			repo_view.setClickController(click_controller);
			
			return;
		}
	}

}
