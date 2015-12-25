package org.ganza.repo.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.EditorView;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

/**
 * PopupController gére
 * les événements de la popup menu
 * lancé par ClickController
 * @author Ganza Mykhailo
 */
public class PopupController extends RepoController implements ActionListener
{
	private Repo repo; //depôt
	private RepoView repo_view; //la vue principale
	
	//controlleurs
	private ClickController click_controller; //pour savoir l'élement de la liste cliqué
	private DragController drag_controller; //pouir remettre à jour le controller apres
	//suppresion d'un fichier
	
	/**
	 * Constructeur principale
	 * @param repo depôt
	 * @param repo_view la vue principale
	 */
	public PopupController(Repo repo, RepoView repo_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
	}
	
	/**
	 * Setter de depôt
	 * @param repo depôt
	 */
	public void setRepo(Repo repo)
	{
		this.repo = repo;
	}
	
	/**
	 * Setter de ClickController
	 * @param click_controller controlleur de click
	 */
	public void setClickController(ClickController click_controller)
	{
		this.click_controller = click_controller;
	}
	
	/**
	 * Setter de DragController
	 * @param drag_controller controlleur de drag&drop
	 */
	public void setDragController(DragController drag_controller)
	{
		this.drag_controller = drag_controller;
	}
	
	/**
	 * Gérer les évenemnts
	 * d'affichage de fichier xml,
	 * affichage de la vue EditorView
	 * suppresion de fichier
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((JMenuItem)e.getSource()).getText();
		
		//popup menu "Reinsegner XML"
		if(item_name.equals("Reinsegner XML"))
		{	
			//reccuperation de fichier
			RepoFile repo_file = null;
			try { repo_file = repo.getRFiles().get(click_controller.getIndex()); } 
			catch (JDOMException | IOException e2) { e2.printStackTrace(); }
			
			//lancement de la vue
			EditorView editor_view = new EditorView();
			EditorController editor_controller = new EditorController(repo_file, editor_view);
			
			try {
				ArrayList<String> l = repo_file.getAttributes();
				editor_view.show(l.toArray(new String[l.size()])); 
				} 
			catch (JDOMException | IOException e1) { e1.printStackTrace(); }
			
			editor_view.setEditorController(editor_controller);
			
			return;
		}
		               
		//popup menu "Modifier XML"
		if(item_name.equals("Modifier XML"))
		{
    		//reccuperer le fichier xml
    		File xml_file = null;
			try { xml_file = repo.getRFiles().get(click_controller.getIndex()).getMetaFile(); } 
			catch (JDOMException | IOException e2) { e2.printStackTrace(); }
    		
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
			repo_view.initialize(repo.getName());
			repo.setNoSearching();
			refreshRepoView(repo, repo_view);
			
			//reinitialiser les contrlleurs
			//car le JList a ete modifiee
			repo_view.setClickController(click_controller);
			repo_view.setDragController(drag_controller);
			
			return;
		}
	}

}
