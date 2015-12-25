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

public class ClickController extends MouseAdapter
{	
	private JList<RepoFile> list;
	private Repo repo;
	private RepoView repo_view;
	private int index;//index de l'element de JList choisi
	
	public ClickController(Repo repo, RepoView repo_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
		
		index = -1;
	}
	
	public void setList(JList<RepoFile> list)
	{
		this.list = list;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public String getFileName()
	{
		return list.getSelectedValue().getName();
	}
	
    public void mouseClicked(MouseEvent e) 
    {   
    	//click droit
        if ( SwingUtilities.isRightMouseButton(e) )
        {
        	index = list.locationToIndex(e.getPoint());
            list.setSelectedIndex(index);
        	
            //dire a controleur de popup quel element de list a ete choisi
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
