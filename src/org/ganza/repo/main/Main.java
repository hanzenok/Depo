package org.ganza.repo.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.jdom2.JDOMException;

import net.lingala.zip4j.exception.ZipException;


///repo.read() doesn't read the name in xmlmetafile
public class Main 
{	
	public static void main(String[] args) 
	throws ZipException, FileNotFoundException, IOException, JDOMException
	{	
		Repo repo = new Repo();
		
		repo.read("/tmp/repo_2LwK3Kc");
		
//		repo.create();
//		repo.addFile(new RepoFile(new File("/home/gunza/Desktop/test"), null));
//		repo.write();
		
		repo.save("/tmp/" + repo.getName() + ".zip");
		//repo.load("/tmp/TestRepo.repo");
		
		System.out.println(repo.getPath() + ", name: " + repo.getName());
		System.out.println("Nb files: " + repo.size() + "\n");
		System.out.println(repo.list());
		
	}
	
}
