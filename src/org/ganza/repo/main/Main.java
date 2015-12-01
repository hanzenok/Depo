package org.ganza.repo.main;

import org.ganza.repo.model.Repo;
import net.lingala.zip4j.exception.ZipException;


///repo.read() doesn't read the name in xmlmetafile
public class Main 
{	
	public static void main(String[] args) throws ZipException
	{	
		Repo repo = new Repo();
		
		repo.read("/tmp/repo_9aYbpfR");
		
		//repo.create();
		//repo.addFile(new RepoFile(new File("/home/gunza/Desktop/test"), null));
		//repo.write();
		
		repo.save("/tmp/repo_9aYbpfR.zip");
		//repo.load("/tmp/TestRepo.repo");
		
		System.out.println(repo.getPath() + ", name: " + repo.getName());
		System.out.println("Nb files: " + repo.size() + "\n");
		System.out.println(repo.list());
		
	}
	
}
