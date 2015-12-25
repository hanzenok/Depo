package org.ganza.repo.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.ganza.repo.view.RepoView;

public class CloseDialogController extends WindowAdapter
{
	private RepoView repo_view;
	
	public CloseDialogController(RepoView repo_view)
	{
		this.repo_view = repo_view;
	}
	
    public void windowClosing(WindowEvent e) {
        
    	repo_view.setEnabled(true);

    }
}
