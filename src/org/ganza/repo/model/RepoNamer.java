package org.ganza.repo.model;

public class RepoNamer {
	
	final String base = new String("repo_");
	
	final int n = 7;
	final String lexicon = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	final java.util.Random rand = new java.util.Random();

	public String generate() 
	{    
		StringBuilder builder = new StringBuilder(base);
	    
		for(int i = 0; i < n; i++)
	            
			builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
		
	    return builder.toString();
	}
}
