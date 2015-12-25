package org.ganza.repo.model;

import java.util.ArrayList;

public class RepoSearchRequest {

	public String target;
	public ArrayList<String> attributes;
	
	public RepoSearchRequest(String target, ArrayList<String> attributes)
	{
		this.target = target;
		this.attributes = attributes;
	}
}
