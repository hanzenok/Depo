package org.ganza.repo.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ganza.repo.controller.MenuController;
import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.EditorView;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

import net.lingala.zip4j.exception.ZipException;


///repo.read() doesn't read the name in xmlmetafile
public class Main 
{	
	public static void main(String[] args) 
	throws ZipException, FileNotFoundException, IOException, JDOMException
	{	
		
//		RepoView repo_view = new RepoView();
//		MenuController mc = new MenuController(repo_view);
		
		EditorView rv = new EditorView();
	}
	
}
