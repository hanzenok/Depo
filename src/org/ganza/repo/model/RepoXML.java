package org.ganza.repo.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
	
	public String getAttributeValue(String element_name) 
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		return root.getChild(element_name).getText();
	}
	
	public void setAttributeValue(String element_name, String value) 
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		root.getChild(element_name).setText(value);
		
		//reecrire le fichier meta
	    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    sortie.output(document, new FileOutputStream(metafile_path));
	}
	
	public void addAttribute(String element_name, String value) 
	throws JDOMException, IOException
	{
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
	
	public String[] getAttributes()
	throws JDOMException, IOException
	{
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(new File(metafile_path));
		Element root = document.getRootElement();
		
		//reccuperer les toutes les elements
		List<Element> list = root.getChildren();
		int i=0, n = list.size();
		String[] attributes = new String[n];
		
		for(Element el: list)
		{	
			attributes[i++] = el.getName();
			System.out.println(el.getName());
		}
		
		return attributes;
	}
}
