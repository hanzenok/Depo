package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.EditorView;

public class EditorController implements ActionListener{

	private RepoFile repo_file;
	private EditorView editor_view;
	
	public EditorController(RepoFile repo_file, EditorView editor_view)
	{
		this.repo_file = repo_file;
		this.editor_view = editor_view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}

}
