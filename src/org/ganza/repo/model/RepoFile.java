package org.ganza.repo.model;

import java.io.File;
import java.io.FileNotFoundException;
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
	
	private RepoXML rx; //pour gerer les fichiers xml
	
	/**
     * Constructeur principal
     * 
     * @param file
     * 			le fichier
     * 
     * @param meta_file
     * 			fichier xml associé
     */
	public RepoFile(File file, File meta_file) 
	{	
		this.file = file;
		this.meta_file = meta_file;
		
		rx = new RepoXML();
	}
	
	public RepoFile(File file)
	{	
		this.file = file;
		this.meta_file = null;
		
		rx = new RepoXML();
	}
	
	public File getFile()
	{	
		return file;
	}
	
	public File getMetaFile()
	{	
		return meta_file;
	}
	
	public void copy(String path) 
	throws IOException
	{	
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
     * @return le nom d'un fichier sous format
     * fichier (.fichier)
     */
	public String toString()
	{	
		return getName() + " (" + getMetaFile().getName() + ")";
	}
	
	public boolean hasMeta()
	{
		return meta_file != null;	
	}
	
	public void createMeta(String folder_path) 
	throws FileNotFoundException, IOException
	{
		if(!hasMeta())
		{
			String meta_file_path = folder_path + "/." + getName();
			
			rx.createMeta(meta_file_path, getName());
			
			meta_file = new File(meta_file_path);
		}
	}
	
	public void delete()
	{
		file.delete();
		meta_file.delete();
	}
}
