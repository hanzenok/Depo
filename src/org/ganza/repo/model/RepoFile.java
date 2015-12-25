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
 * Caracterisé par un fichier même et 
 * un fichier xml associe
 * @author Ganza Mykhailo
 */
public class RepoFile
{	
	private File file; //fichier dans le dossier
	private File meta_file; //fichier xml associé
	
	private RepoXML rx; //pour gerer les fichiers xml
	
	/**
     * Constructeur principal
     * 
     * @param file le fichier
     * 
     * @param meta_file fichier xml associé
     */
	public RepoFile(File file, File meta_file) 
	{	
		this.file = file;
		this.meta_file = meta_file;
		
		rx = new RepoXML(this.meta_file.getAbsolutePath());
	}
	
	/**
	 * Constructeur avec le fichier éxterieur
	 * de depôt qui ensuite sera copié dans 
	 * le dossier de depôt
	 * Utilisé par Drag&Drop
	 * @param transfer_file fichier
	 */
	public RepoFile(File transfer_file)
	{	
		this.file = transfer_file;
		this.meta_file = null;
		
		rx = new RepoXML();
	}
	
	/**
	 * Renvoi le fichier 
	 * @return fichier
	 */
	public File getFile()
	{	
		return file;
	}
	
	/**
	 * Renvoi le fichier avec les metadonnées
	 * @return fichier avec metadonnées
	 */
	public File getMetaFile()
	{	
		return meta_file;
	}
	
	/**
	 * Réalise la copie
	 * de fichier file vers 
	 * le dossier de depôt
	 * @param path dossier de depôt
	 * @throws IOException
	 */
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
     * fichier (.fichier.xml)
     */
	public String toString()
	{	
		return getName() + " (" + getMetaFile().getName() + ")";
	}
	
	/**
	 * Vérifie si le depôt 
	 * contient un fichier avec metadonnées
	 * @return
	 */
	public boolean hasMeta()
	{
		return meta_file != null;	
	}
	
	/**
	 * Géneration de fichier avec les 
	 * metadonnées associé a ficheir file
	 * Utilise un gestionnaire des fichiers xml rx
	 * @param folder_path dossier de depôt
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void createMeta(String folder_path) 
	throws FileNotFoundException, IOException, JDOMException
	{
		if(!hasMeta())
		{
			//creer un fichier meta standart
			String meta_file_path = folder_path + File.separator + "." + getName() + ".xml";
			
			//gestionnaire de fichiers xml
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
	
	/**
	 * Suppresion de RepoFile
	 * Supprime le fichier et 
	 * le fichier xml associé
	 */
	public void delete()
	{
		file.delete();
		meta_file.delete();
	}
	
	/**
	 * Défini un attribut xml element_name
	 * à une valeur value
	 * Utilise un gestionnaire des fichiers xml rx
	 * @param element_name nom de la balise xml
	 * @param value valeur
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void setAttributeValue(String element_name, String value) 
	throws JDOMException, IOException
	{
		rx.setAttributeValue(element_name, value);
	}
	
	/**
	 * Renvoi la liste des attributes
	 * xml de fichier xml
	 * Utilise un gestionnaire des fichiers xml rx
	 * @return liste des attributes
	 * @throws JDOMException
	 * @throws IOException
	 */
	public ArrayList<String> getAttributes() 
	throws JDOMException, IOException
	{
		return rx.getAttributes();
	}
	
	/**
	 * Renvoi un valeur de l'attribute
	 * en indice index
	 * Utilise un gestionnaire des fichiers xml rx
	 * @param index l'indice de balise xml
	 * @return valeur de l'attribut
	 * @throws JDOMException
	 * @throws IOException
	 */
	public String getAttributeValue(int index) 
	throws JDOMException, IOException
	{
		return rx.getAttributeValue(index);
	}
	
	/**
	 * Renvoi l'extension de fichier (le format)
	 * @return format de fichier
	 */
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
