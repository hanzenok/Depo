package org.ganza.repo.model;

import java.util.Comparator;

/**
 * Définie comment le regle 
 * de comparaison des RepoFiles's
 * @author Ganza Mykhailo
 */
public class RepoFileComparator implements Comparator<RepoFile>{
	/**
	 * Function de comparaison
	 * Comparaison se fait
	 * par rapport à le nom de RepoFile
	 */
	@Override
	public int compare(RepoFile arg0, RepoFile arg1) 
	{
		String name1 = arg0.getName();
		String name2 = arg1.getName();
		
		if(name1.equals(name2)) return 0;
		
		return -1;
	}
	
}
