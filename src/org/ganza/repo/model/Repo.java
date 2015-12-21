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
	private static boolean exist = false; //si le dossier d'un depot a ete cree ou pas
	//visible
	
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
	public boolean read() 
	throws JDOMException, IOException{
		
		return read(folder_path);
	}
	
	public boolean read(String path) 
	throws JDOMException, IOException
	{	
		boolean is_repo = false; //indicateur si un dossier contient un depôt
		
		JFileChooser chooser = new JFileChooser();
		FileSystemView view = chooser.getFileSystemView();
		
		File[] listOfFiles = view.getFiles(new File(path), true); //que des fichiers visibles
		
		//vérifie si dossier contient un fichier avec les metadonnées
		File metafile = new File(path + "/" + metafile_name);
		if(metafile.exists())
		{ 	
			//redefinir le chemin vers dossier
			setPath(path);
			
			//redefinir le chemin vers meta fichier
			metafile_path = folder_path + "/" + metafile_name;
			
			//et le nom de repo
			setName(rx.getAttribute(metafile_path, "name"));
			
			is_repo = true;
		}
		
		for (File file : listOfFiles)
		{	
			if(is_repo)
			{
				File xmlfile = new File(file.getPath() + "/." + file.getName()); //fichier avec le meme nom avec le point au debut
				
				repofiles.add(new RepoFile(file, xmlfile));
			}
			else
				repofiles.add(new RepoFile(file));
		}
		
		return is_repo;
	}
	
	/**
     * Crée le depôt 
     * à partir d'un dossier
     * précisé par folder_path,
     * charge le fichier de metadata
     */
	public void create()
	throws FileNotFoundException, IOException
	{	
		//creation de dossier
		File dir = new File(folder_path);
		if(!dir.exists()) dir.mkdir();
		
		rx.createRepoMeta(metafile_path, repo_name);
		
		exist = true;
	}
	
	//if xml associated to the file does not exist
	//it creates it
	public void write() 
	throws IOException
	{	
		int i, n = size();
		
		for(i=0; i<n; i++){
			
			RepoFile rp = repofiles.get(i);
			rp.copy(folder_path);
			
			if(!rp.hasMeta())
			{
				rp.createMeta(folder_path);
			}
		}
	}
	
	public void save(File zipfile) 
	throws ZipException
	{	
		RepoZipper zipper = new RepoZipper(zipfile.getAbsolutePath());
		
		//archivage d'un fichier meta
		zipper.add(new File(metafile_path));
		
		//archivage de la reste
		int i, n = size();
		for(i=0; i<n; i++){
			
			zipper.add(repofiles.get(i));
		}
	}
	
	public void load(String zipfile_path) 
	throws ZipException, JDOMException, IOException
	{	
		RepoZipper zipper = new RepoZipper(zipfile_path);
		
		zipper.restore(folder_path); //desarchivage
		
		//changer le nom indiquée dans le ficher meta
		rx.setAttribute(metafile_path, "name", getName());
		
		//lecture de contenu de fichier et chargement dans la base
		read(folder_path);
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
	
	public void addFile(RepoFile repoFile) 
	throws IOException
	{	
		repofiles.add(repoFile);
		repoFile.copy(folder_path);
		repoFile.createMeta(folder_path);
	}
	
	public void close(){
		
		JFileChooser chooser = new JFileChooser();
		FileSystemView view = chooser.getFileSystemView();
		
		//supprimer les fichiers de dossier
		File[] listOfFiles = view.getFiles(new File(folder_path), false); //toutes les fichiers
		for (File file : listOfFiles)
		{	
			file.delete();
		}
		
		//supprimer le repertoire
		File dir = new File(folder_path);
		dir.delete();
		
	
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
	
	public boolean exists(){
		
		return exist;
	}
}
