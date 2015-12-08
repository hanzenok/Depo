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
	public void createRepoMeta(String metafile_path, String repo_name) 
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
	
	public void createMeta(String metafile_path, String filename) 
	throws FileNotFoundException, IOException
	{				
			//création de fichier metadata
			Element root = new Element("repofile");
			Document document = new Document(root);
			
			Element name = new Element("name");
			name.setText(filename);
			root.addContent(name);
			
			//ecrire le fichier meta
		    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		    sortie.output(document, new FileOutputStream(metafile_path));
	}
	
	public String getAttribute(String metafile_path, String element_name) 
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		
		Document document = sxb.build(new File(metafile_path));
		
		Element racine = document.getRootElement();
		
		return racine.getChild("name").getText();
	}
	
	public void setAttribute(String metafile_path, String element_name, String attribute) 
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		
		Document document = sxb.build(new File(metafile_path));
		
		Element racine = document.getRootElement();
		
		racine.getChild(element_name).setText(attribute);
		
		//reecrire le fichier meta
	    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    sortie.output(document, new FileOutputStream(metafile_path));
	}
}