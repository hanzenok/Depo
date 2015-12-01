package org.ganza.repo.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * RepoFile répresent 
 * un fichier dans un dêpot
 * @author Ganza Mykhailo
 */

public class RepoFile
{	
	private File file;
	private File meta_file;
	
	/**
     * Constructeur principal
     * 
     * @param file
     * 			le fichier
     * 
     * @param meta_file
     * 			fichier xml associé
     */
	public RepoFile(File file, File meta_file) {
		
		this.file = file;
		this.meta_file = meta_file;
	}
	
	public File getFile(){
		
		return file;
	}
	
	public File getMetaFile(){
		
		return meta_file;
	}
	
	public void copy(String path) throws IOException{
		
		Files.copy(file.toPath(), Paths.get(path + "/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
     * Renvoi le nom d'un fichier
     * 
     * @return le nom d'un fichier
     */
	public String getName()
	{	
		return file.getName();
	}
	
	/**
     * Renvoi le nom d'un fichier
     * 
     * @return le nom d'un fichier
     */
	public String toString()
	{	
		return getName();
	}
}
