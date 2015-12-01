package org.ganza.repo.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import net.lingala.zip4j.exception.ZipException;

/**
 * Repo répresent un dêpot
 * @author Ganza Mykhailo
 */
public class Repo 
{	
	private final String metafile_name;
	
	private String repo_name;
	private String metafile_path;
	private String folder_path;
	private ArrayList<RepoFile> repofiles;
	private RepoNamer rn;
	
	private RepoXML rx; //gestionnaire de xml
	
	/**
     * Constructeur principal
     */
	public Repo()
	{	
		rn = new RepoNamer();
		repo_name = new String(rn.generate());
		
		folder_path = new String("/tmp/" + repo_name);
		
		metafile_name = new String(".repo_meta.xml");
		metafile_path = new String(folder_path + "/" + metafile_name);
		
		repofiles = new ArrayList<RepoFile>();
		
		rx = new RepoXML();
	}
	
	
	/**
     * Défini le nom de depôt
     * 
     * @param name
     * 			le nom de depôt
     */
	public void setName(String name)
	{	
		this.repo_name = name;
	}
	
	/**
     * Renvoi le nom d'un dêpot
     * 
     * @return le nom d'un dépot
     */
	public String getName()
	{	
		return repo_name;
	}
	
	/**
     * Ouvre le depôt 
     * à partir d'un dossier
     * précisé par folder_path
     * 
     * @return si le dossier est un depôt
	 * @throws IOException 
	 * @throws JDOMException 
     */
	public boolean read() throws JDOMException, IOException{
		
		return read(folder_path);
	}
	
	public boolean read(String path) throws JDOMException, IOException
	{	
		boolean is_repo = false; //indicateur si un dossier contient un depôt
		
		JFileChooser chooser = new JFileChooser();
		FileSystemView view = chooser.getFileSystemView();
		
		File[] listOfFiles = view.getFiles(new File(path), false);
		
		for (File file : listOfFiles)
		{	
			//vérifie si dossier contient un fichier avec les metadonnées
			if(file.getName().equals(metafile_name))
			{ 	
				//redefinir le chemin vers dossier
				setPath(path);
				
				//redefinir le chemin vers meta fichier
				metafile_path = folder_path + "/" + metafile_name;
				
				//et le nom de repo
				setName(rx.getAttribute(metafile_path, "name"));
				
				is_repo = true;
				
			}

			repofiles.add(new RepoFile(file, null));
		}
		
		return is_repo;
	}
	
	/**
     * Crée le depôt 
     * à partir d'un dossier
     * précisé par folder_path,
     * charge le fichier de metadata
     */
	public void create() throws FileNotFoundException, IOException
	{	
		rx.createMeta(folder_path, metafile_path, repo_name);
	}
	
	public void write() throws IOException
	{	
		int i, n = size();
		
		for(i=0; i<n; i++){
			
			repofiles.get(i).copy(folder_path);
		}
	}
	
	public void save(String zipfile_path) throws ZipException
	{	
		RepoZipper zipper = new RepoZipper(zipfile_path);
		
		//archivage d'un fichier meta
		zipper.add(new File(metafile_path));
		
		//archivage de la reste
		int i, n = size();
		for(i=0; i<n; i++){
			
			zipper.add(repofiles.get(i));
		}
	}
	
	public void load(String zipfile_path) throws ZipException
	{	
		RepoZipper zipper = new RepoZipper(zipfile_path);
		
		zipper.restore("/tmp/test");
	}
	
	
	
	/**
     * Renvoi la liste
     * des fichiers dans un depôt
     * 
     * @return liste des fichiers
     */
	public String list()
	{	
		int i,n = size();
		
		String str = new String("");
		
		for(i=0; i<n; i++){
			
			str += repofiles.get(i);
			str += "\n";
		}
		
		return str;
	}
	
	/**
     * Renvoi la liste
     * des fichiers dans un depôt
     * 
     * @return liste des fichiers
     */
	public String toString(){
		
		return list();
	}
	
	
	/**
     * Renvoi le chemin
     * ver un dossier
     * avec un depôt
     * 
     * @return chemin
     */
	public String getPath(){
	
		return folder_path;
	}
	
	/**
     * Défini le chemin
     * vers un dossier avec le depoôt
     * 
     * @param path
     * 			le chemin
     */
	public void setPath(String path){
		
		this.folder_path = path;
	}
	
	public void addFile(RepoFile repoFile){
		
		repofiles.add(repoFile);
	}
	
	/**
     * Renvoi le nombre
     * des fichiers dans un depôt
     * 
     * @return nombre des fichiers
     */
	public int size(){
		
		return repofiles.size();
	}
}
