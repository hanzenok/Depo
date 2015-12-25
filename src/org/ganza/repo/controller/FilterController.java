package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JList;

import org.ganza.repo.model.Repo;
import org.ganza.repo.view.FilterView;
import org.ganza.repo.view.RepoView;

/**
 * FiltreController controle 
 * la vue FiltreView avec les 
 * options de filtrage
 * Ecoute les événements des boutons et 
 * un checkbox
 * Agit sur la modele et la vue 
 * principles
 * @author Ganza Mykhailo
 */
public class FilterController extends RepoController implements ActionListener{
	 
	private Repo repo; // depôt
	private RepoView repo_view; // vue principale
	private FilterView  filter_view; //vue de filtrage
	private JList<String> list; //liste avec filtres
	
	/**
	 * Constructeur principale
	 * 
	 * @param repo depôt
	 * @param repo_view	la vue principle
	 * @param filter_view la vue de filtrage
	 */
	public FilterController(Repo repo, RepoView repo_view, FilterView filter_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
		this.filter_view = filter_view;
		
		//checkbox est initialisé à l'état d'acceptance de la modele Repo
		this.filter_view.setCheckBox(repo.getAcceptance());
	}
	
	/**
	 * Définit de la liste de la vue
	 * 
	 * @param list liste 
	 */
	public void setList(JList<String> list)
	{
		this.list = list;
	}
	
	/**
	 * Gérer les événements 
	 * des boutones d'ajout et 
	 * suppression des attributs xml
	 * Et un checkbox pour autorisation
	 * des toutes les fichiers ou pas
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((AbstractButton) e.getSource()).getText();
		
		//button delete
		if(item_name.equals(">>"))
		{
			int index = list.getSelectedIndex();
			if (index < 0) return;
			
			//supprimer
			repo.removeExtension(index);
			
			//remettre a jour la vue
			filter_view.show(repo.getAcceptedExtensions());
			setList(filter_view.getList());
			
			//appliquer les changements
			refreshRepoView(repo, repo_view);
			
			return;
		}
		
		//button add
		if(item_name.equals("<<")){
			
			if(filter_view.editorEmpty()) return;
			
			//ajouter l'extension dans Repo
			repo.addExtension(filter_view.getEditor());
			
			//remettre a jour la vue
			filter_view.show(repo.getAcceptedExtensions());
			setList(filter_view.getList());
			filter_view.setEditor("");
			
			//appliquer les changements
			refreshRepoView(repo, repo_view);
			
			return;
		}
		
		//checkbox
		if(item_name.equals("autoriser tout"))
		{
			//changer l'état d'acceptance
			boolean checked = ((JCheckBox)e.getSource()).isSelected();
			repo.setAcceptance(checked);
			
			//appliquer les changements
			refreshRepoView(repo, repo_view);
			
			return;
		}
	}

}
