package org.ganza.repo.model;

import java.util.Comparator;

public class RepoFileComparator implements Comparator<RepoFile>{

	@Override
	public int compare(RepoFile arg0, RepoFile arg1) 
	{
		String name1 = arg0.getName();
		String name2 = arg1.getName();
		
//		return name1.compareTo(name2);
		
		if(name1.equals(name2)) return 0;
		
		return -1;
	}
	
}
