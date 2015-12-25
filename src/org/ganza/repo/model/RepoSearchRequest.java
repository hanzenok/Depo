package org.ganza.repo.model;

import java.util.ArrayList;

/**
 * Un objet qui répresente 
 * une requête de recherche
 * pour le depôt
 * Composée d'un chaîne des caracteres
 * à rechercher et la liste des attributs
 * xml où il faut chercher
 * @author Ganza Mykhailo
 */
public class RepoSearchRequest {

	public String target; //chaîne des caracteres à rechercher
	public ArrayList<String> attributes; // liste des attributs xml où il faut chercher
	
	/**
	 * Constructeur principal
	 * @param target chaîne des caracteres
	 * @param attributes attributes xml
	 */
	public RepoSearchRequest(String target, ArrayList<String> attributes)
	{
		this.target = target;
		this.attributes = attributes;
	}
}
