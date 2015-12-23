package org.ganza.repo.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class RepoXML 
{
	String metafile_path;
	
	public RepoXML(String metafile_path)
	{
		this.metafile_path = metafile_path;
	}
	
	public void createRepoMeta(String repo_name) 
	throws FileNotFoundException, IOException
	{				
			//création de fichier metadata
			Element root = new Element("repo");
			Document document = new Document(root);
			
			Element name = new Element("name");
			name.setText(repo_name);
			root.addContent(name);
			
			//ecrire le fichier meta
		    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		    sortie.output(document, new FileOutputStream(metafile_path));
	}
	
	public void createMeta(String filename) 
	throws FileNotFoundException, IOException
	{				
			//création de fichier metadata
			Element root = new Element("repofile");
			Document document = new Document(root);
			
			Element name = new Element("name");
			name.setText(filename);
			root.addContent(name);
			
			//ecrire le fichier meta
		    XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		    out.output(document, new FileOutputStream(metafile_path));
	}
	
	public String getAttribute(String element_name) 
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		return root.getChild(element_name).getText();
	}
	
	public void setAttribute(String element_name, String attribute) 
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		root.getChild(element_name).setText(attribute);
		
		//reecrire le fichier meta
	    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    sortie.output(document, new FileOutputStream(metafile_path));
	}
	
	public void addAttribute(String element_name, String attribute) 
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		//ajout d'un nouveu element
		Element elm = new Element(element_name);
		elm.setText(attribute);
		root.addContent(elm);
		
		//reecrire le fichier meta
	    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    sortie.output(document, new FileOutputStream(metafile_path));
	}
}
