package org.ganza.repo.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ganza.repo.controller.MenuController;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

import net.lingala.zip4j.exception.ZipException;


public class Main 
{	
	public static void main(String[] args) 
	throws ZipException, FileNotFoundException, IOException, JDOMException
	{	
		
		RepoView repo_view = new RepoView();
		MenuController mc = new MenuController(repo_view);
		
//		EditorView rv = new EditorView();
		
//		Repo repo = new Repo();
//		
//		
//		FilterView fv = new FilterView();
//		
//		FilterController fc = new FilterController(repo, null, fv);
//		fv.show(repo.getAcceptedExtensions());
//		
//		fv.setFilterController(fc);
	}
	
}
