package org.ganza.repo.model;

/**
 * Generateur des noms aléatoires
 * pour le depôt
 * format: "repo_m4kxh41"
 * @author Ganza Mykhailo
 */
public class RepoNamer {
	
	final String base = new String("repo_"); //base de nom
	
	final int n = 7; //taile de la partie qui sera generé
	final String lexicon = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890"; //pool des caracteres
	final java.util.Random rand = new java.util.Random(); //generateur aléatoires

	/**
	 * Renvoi le nom de depôt aléatoire
	 * @return nom de depôt
	 */
	public String generate() 
	{    
		StringBuilder builder = new StringBuilder(base);
	    
		for(int i = 0; i < n; i++)
	            
			builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
		
	    return builder.toString();
	}
}
