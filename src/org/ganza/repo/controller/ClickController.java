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

public class ClickController extends MouseAdapter
{	
	private JList<RepoFile> list;
	private Repo repo;
	
	public ClickController(Repo repo)
	{
		this.repo = repo;
	}
	
	public void setList(JList<RepoFile> list)
	{
		this.list = list;
	}
	
    public void mouseClicked(MouseEvent e) 
    {   
    	//double click
    	if (e.getClickCount() == 2) 
    	{	
    		//reccuperation de l'indexe
    		int index = list.locationToIndex(e.getPoint());
            
    		//ouvrir le fichier dans systeme
    		File file = repo.getRFile(index).getFile();    		
    		try { Desktop.getDesktop().open(file); } 
    		catch (IOException e1) { e1.printStackTrace();}
         }
    	
    	//click droit
        if ( SwingUtilities.isRightMouseButton(e) )
        {
        	int index = list.locationToIndex(e.getPoint());
        	
        	System.out.println("Right click on: " + index);
        }
    }
}
