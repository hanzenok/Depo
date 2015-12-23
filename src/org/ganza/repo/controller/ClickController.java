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

public class ClickController extends MouseAdapter
{	
	private JList<RepoFile> list;
	private Repo repo;
	private RepoView repo_view;
	
	public ClickController(Repo repo, RepoView repo_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
	}
	
	public void setList(JList<RepoFile> list)
	{
		this.list = list;
	}
	
    public void mouseClicked(MouseEvent e) 
    {   
    	//click droit
        if ( SwingUtilities.isRightMouseButton(e) )
        {
        	int index = list.locationToIndex(e.getPoint());
            list.setSelectedIndex(index);
        	
            repo_view.showPopup(e);
            
            return;
        }
    	
    	//double click
    	if (e.getClickCount() == 2) 
    	{	
    		//reccuperation de l'indexe
    		int index = list.locationToIndex(e.getPoint());
            
    		//ouvrir le fichier dans systeme
    		File file = repo.getRFile(index).getFile();    		
    		try { Desktop.getDesktop().open(file); } 
    		catch (IOException e1) { e1.printStackTrace();}
    		
    		return;
         }
    	
    }
}
