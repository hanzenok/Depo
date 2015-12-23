package org.ganza.repo.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.ganza.repo.model.Repo;

public class ExitController extends WindowAdapter{
	
	private Repo repo;
	
	public ExitController(Repo repo)
	{
		this.repo = repo;
	}
	
	public void setRepo(Repo repo)
	{
		this.repo = repo;
	}
	
    public void windowClosing(WindowEvent e) {
        
    	if(repo != null && repo.exists()) 
    		
    		repo.close();

    }
}
