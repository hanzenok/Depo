package org.ganza.repo.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.jdom2.JDOMException;


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
		
		rx = new RepoXML(this.meta_file.getAbsolutePath());
	}
	
	public RepoFile(File transfer_file)
	{	
		this.file = transfer_file;
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
	
	public void transfer(String path) 
	throws IOException
	{	
		//copier
		Files.copy(file.toPath(), Paths.get(path + File.separator + file.getName()), StandardCopyOption.REPLACE_EXISTING);
		
		//pointer RepoFile vers le fichier dans le dossier
		file = new File(path + File.separator + file.getName());
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
	throws FileNotFoundException, IOException, JDOMException
	{
		if(!hasMeta())
		{
			//creer un fichier meta standart
			String meta_file_path = folder_path + File.separator + "." + getName() + ".xml";
			
			rx.setMetafilePath(meta_file_path);
			rx.createMeta(getName());
			
			meta_file = new File(meta_file_path);
			
			//y ajouter un attribut en fonction de type de fichier
			String type = Files.probeContentType(Paths.get(file.getAbsolutePath())).split("/")[0];
			rx.addAttribute("type", type);
			rx.addAttribute("author", "unknown");
			
			if(type.equals("image"))
			{
				rx.addAttribute("where", "unknown");
				rx.addAttribute("when", "unkown");
				
				return;
			}
			
			if(type.equals("audio"))
			{
				rx.addAttribute("genre", "unknown");
				
				return;
			}
			
		}
	}
	
	public void delete()
	{
		file.delete();
		meta_file.delete();
	}
	
	public void setAttributeValue(String element_name, String value) 
	throws JDOMException, IOException
	{
		rx.setAttributeValue(element_name, value);
	}
	
	public ArrayList<String> getAttributes() 
	throws JDOMException, IOException
	{
		return rx.getAttributes();
	}
	
	public String getAttributeValue(int index) 
	throws JDOMException, IOException
	{
		return rx.getAttributeValue(index);
	}
	
	public String getExtenstion()
	{	
		String name = file.getName();
		String extension = "";

		int i = name.lastIndexOf('.');
		if (i >= 0) {
		    extension = name.substring(i+1);
		}
		
		return extension;
	}
}
