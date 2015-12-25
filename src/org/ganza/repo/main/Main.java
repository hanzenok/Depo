package org.ganza.repo.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ganza.repo.controller.MenuController;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

import net.lingala.zip4j.exception.ZipException;

/**
 * Point d'entrée de la programme
 * @author Ganza Mykhailo
 */
public class Main 
{	
	/**
	 * Methode main
	 * @param args
	 * @throws ZipException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JDOMException
	 */
	public static void main(String[] args) 
	throws ZipException, FileNotFoundException, IOException, JDOMException
	{	
		//la vue principale
		RepoView repo_view = new RepoView();
		
		//controlleur principale
		@SuppressWarnings("unused")
		MenuController mc = new MenuController(repo_view); //c'est controlleur lance la model
	}
	
}
