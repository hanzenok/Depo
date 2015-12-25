package org.ganza.repo.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Gestionnare des lecture
 * et chargement des fichiers xml
 * @author Ganza Mykhailo
 */
public class RepoXML 
{
	String metafile_path; //chemin vers fichier xml
	
	/**
	 * Constructeur simple
	 */
	public RepoXML(){}
	
	/**
	 * Constructeur principale
	 * @param metafile_path chemin ver le fichier xml
	 */
	public RepoXML(String metafile_path)
	{
		this.metafile_path = metafile_path;
	}
	
	/**
	 * Setter de chemin vers fichier xml
	 * @param metafile_path chemin vers fichier xml
	 */
	public void setMetafilePath(String metafile_path)
	{
		this.metafile_path = metafile_path;
	}
	
	/**
	 * Création de fichier xml des metadonnées
	 * de depôt Repo
	 * @param repo_name nom de dep
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void createRepoMeta(String repo_name) 
	throws FileNotFoundException, IOException
	{				
			//création de fichier metadata
			Element root = new Element("repo");
			Document document = new Document(root);
			
			//ajout d'un attribut
			Element name = new Element("name");
			name.setText(repo_name);
			root.addContent(name);
			
			//ecrire le fichier meta
		    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		    sortie.output(document, new FileOutputStream(metafile_path));
	}
	
	/**
	 * Création de fichier xml des metadonnées
	 * d'un fichier RepoFile
	 * @param filename nom de RepoFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void createMeta(String filename) 
	throws FileNotFoundException, IOException
	{				
			//création de fichier metadata
			Element root = new Element("repofile");
			Document document = new Document(root);
			
			//ajout d'un attribut
			Element name = new Element("name");
			name.setText(filename);
			root.addContent(name);
			
			//ecrire le fichier meta
		    XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		    out.output(document, new FileOutputStream(metafile_path));
	}
	
	/**
	 * Renvoi une valleur d'un attribut
	 * précise par elemnt_name
	 * @param element_name nom de l'attribut
	 * @return valeur associé
	 * @throws JDOMException
	 * @throws IOException
	 */
	public String getAttributeValue(String element_name) 
	throws JDOMException, IOException
	{	
		//lecture de fichier
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		return root.getChild(element_name).getText();
	}
	
	/**
	 * Renvoi une valleur d'un attribut
	 * précise par sont indice
	 * @param index l'indice de l'attribut
	 * @return valeur associé
	 * @throws JDOMException
	 * @throws IOException
	 */
	public String getAttributeValue(int index)
	throws JDOMException, IOException
	{
		//lecture de fichier
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		//reccuperer toutes les elements
		List<Element> list = root.getChildren();
		
		if(index < 0 || index > list.size()) return null;
		
		return list.get(index).getText();
	}
	
	/**
	 * Défini une valeur de l'attribut xml
	 * précisé par element_name
	 * @param element_name l'attrbut xml
	 * @param value valeur associé à définir
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void setAttributeValue(String element_name, String value) 
	throws JDOMException, IOException
	{	
		//lecture de fichier
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		root.getChild(element_name).setText(value);
		
		//reecrire le fichier meta
	    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    sortie.output(document, new FileOutputStream(metafile_path));
	}
	
	/**
	 * Ajoute un nouveau attribut
	 * dans le fichier xml
	 * @param element_name nom de l'attribut
	 * @param value valeur associé
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void addAttribute(String element_name, String value) 
	throws JDOMException, IOException
	{	
		//lecture de fichier
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		//ajout d'un nouveu element
		Element elm = new Element(element_name);
		elm.setText(value);
		root.addContent(elm);
		
		//reecrire le fichier meta
	    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    sortie.output(document, new FileOutputStream(metafile_path));
	}
	
	/**
	 * Renvoi la liste des 
	 * toutes les attributes
	 * de fichier xml
	 * @return toutes les attributes
	 * @throws JDOMException
	 * @throws IOException
	 */
	public ArrayList<String> getAttributes()
	throws JDOMException, IOException
	{
		//lire le fichier
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		//reccuperer les toutes les elements
		List<Element> list = root.getChildren();
		
		ArrayList<String> attributes = new ArrayList<>();
		for(Element el: list)
		{	
			attributes.add(el.getName());
		}
		
		return attributes;
	}
}
