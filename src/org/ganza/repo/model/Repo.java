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
	private String repo_name;
	private final String metafile_name;
	private static boolean exist = false; //si le dossier d'un depot a ete cree ou pas
	private String metafile_path;
	private String folder_path;
	private String tmp_path; //dossier temporel de systeme
	
	private ArrayList<RepoFile> repofiles;
	private RepoNamer rn;
	
	private RepoXML rx; //gestionnaire de xml
	
	private ArrayList<String> accepted_files;
	private boolean accept_all_files;
	
	/**
     * Constructeur principal
     */
	public Repo()
	{	
		rn = new RepoNamer();
		repo_name = new String(rn.generate());
		
		tmp_path = System.getProperty("java.io.tmpdir");
		folder_path = new String(tmp_path + File.separator + repo_name);
		
		metafile_name = new String(".repo_meta.xml");
		metafile_path = new String(folder_path + File.separator + metafile_name);
		
		repofiles = new ArrayList<RepoFile>();
		
		rx = new RepoXML(metafile_path);
		
		accepted_files = new ArrayList<String>();
		accepted_files.add("pdf");
		accepted_files.add("mp3");
		accepted_files.add("jpg");
		accept_all_files = true;
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
		File metafile = new File(path + File.separator + metafile_name);
		if(metafile.exists())
		{ 	
			//redefinir le chemin vers dossier
			setPath(path);
			
			//redefinir le chemin vers meta fichier
			metafile_path = folder_path + File.separator + metafile_name;
			
			//et le nom de repo
			setName(rx.getAttributeValue("name"));
			
			is_repo = true;
		}
		
		for (File file : listOfFiles)
		{	
			if(is_repo)
			{
				File xmlfile = new File(file.getParentFile().getPath() + File.separator + "." + file.getName() + ".xml"); //fichier avec le meme nom avec le point au debut

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
		
		rx.createRepoMeta(repo_name);

		exist = true;
	}
	
	//if xml associated to the file does not exist
	//it creates it
	public void write() 
	throws IOException, JDOMException
	{	
		int i, n = size();
		
		for(i=0; i<n; i++){
			
			RepoFile rp = repofiles.get(i);
			rp.transfer(folder_path);
			
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
		rx.setAttributeValue("name", getName());
		
		//lecture de contenu de fichier et chargement dans la base
		read();
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
	
	public void addRFile(RepoFile repo_file) 
	throws IOException, JDOMException
	{	
		//ajouter dans la liste
		repofiles.add(repo_file);
		
		//copier
		repo_file.transfer(folder_path);
		
		//generer fichier meta
		repo_file.createMeta(folder_path);
		
		System.out.println("Extension:" + repo_file.getExtenstion());
	}
	
//	public RepoFile getRFile(int index)
//	{
//		return repofiles.get(index);
//	}
	
	public void removeRFile(String filename)
	{	
		//recherche
		int i = 0;
		int index = -1;
		for(RepoFile repo_file : repofiles)
		{
			if(repo_file.getName().equals(filename))
			{
				index = i;
				break;
			}
			
			i++;		
		}
		
		//suppression
		repofiles.get(index).delete();
		repofiles.remove(index);	
	}
	
	public void close(){
		
		//supprimer les fichiers et retirer de la liste
		for (RepoFile rf : repofiles)
		{
			rf.delete();
		}
		
		//supprimer le ficher meta de depot
		File file = new File(metafile_path);
		file.delete();
		
		//supprimer le repertoire
		File dir = new File(folder_path);
		dir.delete();
		
		//supprimer le liste
		repofiles.removeAll(repofiles);
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
	
	public ArrayList<RepoFile> getRFiles()
	{
		if(accept_all_files) return repofiles;
		
		//filtrer les fichiers
		ArrayList<RepoFile> accepted = new ArrayList<RepoFile>();
		for(RepoFile repo_file : repofiles)
		{
			if(isAccepted(repo_file)){
				
				accepted.add(repo_file);
			}
		}
		
		return accepted;
	}
	
	public boolean isAccepted(RepoFile repo_file)
	{
		String extension = repo_file.getExtenstion();
		
		if(accepted_files.contains(extension))
			return true;
		
		return false;
	}
	
	public void setAcceptance(boolean state)
	{
		accept_all_files = state;
	}
	
	public boolean getAcceptance()
	{
		return accept_all_files;
	}
	
	public ArrayList<String> getAcceptedExtensions()
	{
		return accepted_files;
	}
	
	public void removeExtension(int index)
	{
		accepted_files.remove(index);
	}
	
	public void addExtension(String extension)
	{
		if(!accepted_files.contains(extension))
			
			accepted_files.add(extension);
	}
}
