package org.ganza.repo.controller;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.SwingUtilities;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

/**
 * ClickController gére les 
 * événements de click de la JList
 * @author Ganza Mykhailo
 */
public class ClickController extends MouseAdapter
{	
	private JList<RepoFile> list; //list de la vue, conteneur des RepoFile's
	private Repo repo; //depôt
	private RepoView repo_view; //la vue
	private int index;//index de l'element de JList choisi
	
	/**
	 * Constructeur principle
	 */
	public ClickController(Repo repo, RepoView repo_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
		
		index = -1;
	}
	
	/**
	 * Définit de la liste de la vue
	 * 
	 * @param list liste 
	 */
	public void setList(JList<RepoFile> list)
	{
		this.list = list;
	}
	
	/**
	 * Renvoi l'index de 
	 * élement de la liste cliquée
	 * 
	 * @return index
	 */
	public int getIndex()
	{
		return index;
	}
	
	/**
	 * Renvoi le nom de fichier
	 * de la liste cliquée
	 * 
	 * @return nom de fichier
	 */
	public String getFileName()
	{
		return list.getSelectedValue().getName();
	}
	
	/**
	 * Fonction qui gére le double 
	 * cliaques et clique droit
	 */
    public void mouseClicked(MouseEvent e) 
    {   
    	//click droit
        if ( SwingUtilities.isRightMouseButton(e) )
        {
        	index = list.locationToIndex(e.getPoint());
            list.setSelectedIndex(index);
        	
            //afficher le popup
            repo_view.showPopup(e);
          
            return;
        }
    	
    	//double click
    	if (e.getClickCount() == 2) 
    	{	
    		//reccuperation de l'indexe
    		index = list.locationToIndex(e.getPoint());
            
    		//ouvrir le fichier dans systeme   		
    		try { 
    				File file = repo.getRFiles().get(index).getFile(); 
    				Desktop.getDesktop().open(file); 
    			} 
    		catch (IOException e1) {e1.printStackTrace();} 
    		catch (JDOMException e1) {e1.printStackTrace();}
    		
    		return;
         }
    	
    }
}
