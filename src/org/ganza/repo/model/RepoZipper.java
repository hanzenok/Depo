package org.ganza.repo.model;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * Un gestionnaire des archives
 * @author Ganza Mykhailo
 */
public class RepoZipper
{	
	private String zipfile_path; //chemin vers archive
	private ZipFile zipfile; //zippeur
	ZipParameters params = new ZipParameters(); //parametres
	
	/**
	 * Constructeur principal
	 * @param zipfile_path chemin vers archive
	 * @throws ZipException
	 */
	public RepoZipper(String zipfile_path) throws ZipException
	{
		this.zipfile_path = zipfile_path;
		
		zipfile = new ZipFile(zipfile_path);
		
		params.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
	}
	
	/**
	 * Setter de chemin
	 * vers l'archive
	 * @param path chemin vers l'archive
	 */
	public void setPath(String path){
		
		zipfile_path = path;
	}
	
	/**
	 * Renvoi le chemin vers l'archive
	 * @return chemin vers l'archive
	 */
	public String getPath(){
		
		return zipfile_path;
	}
	
	/**
	 * Ajout un fichier dans l'archive
	 * @param file ficher à ajouter
	 * @throws ZipException
	 */
	public void add(File file) throws ZipException
	{	
		zipfile.addFile(file, params);
	}
	
	/**
	 * Ajout un fichier RepoFile dans l'archive
	 * @param rf un RepoFile à ajouter
	 * @throws ZipException
	 */
	public void add(RepoFile rf) throws ZipException
	{
		add(rf.getFile());
		add(rf.getMetaFile());
	}
	
	/**
	 * Desarchive l'archive dans 
	 * dossier précisé par destination
	 * @param destination chemin où il faut déposer 
	 * le contenu d'archive
	 * @throws ZipException
	 */
	public void restore(String destination) throws ZipException
	{
		zipfile.extractAll(destination);
	}
	
	/**
	 * Desarchive un seul fichier
	 * filename de l'archive vers destination
	 * @param destination chemin où il faut deposer le fichier
	 * @param filename nom de fichier à desarchiver
	 * @throws ZipException
	 */
	public void extractFile(String destination, String filename) throws ZipException
	{	
		zipfile.extractFile(filename, destination);
	}
	
}
