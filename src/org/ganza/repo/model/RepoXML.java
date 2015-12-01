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
	
	public void createMeta(String folder_path, String metafile_path, String repo_name) throws FileNotFoundException, IOException
	{	
		File dir = new File(folder_path);
		
		if(!dir.exists())
		{	
			//création de dossier
			dir.mkdir();
			
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
	}
	
	String getAttribute(String metafile_path, String element_name) throws JDOMException, IOException{
		
		SAXBuilder sxb = new SAXBuilder();
		
		Document document = sxb.build(new File(metafile_path));
		
		Element racine = document.getRootElement();
		
		return racine.getChild("name").getText();
	}
}
