package org.ganza.repo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.FilterView;
import org.ganza.repo.view.RepoView;

public class FilterController extends RepoController implements ActionListener{
	
	private Repo repo;
	private RepoView repo_view;
	private FilterView  filter_view;
	private JList<String> list;
	
	public FilterController(Repo repo, RepoView repo_view, FilterView filter_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
		this.filter_view = filter_view;
		
		this.filter_view.setCheckBox(repo.getAcceptance());
	}
	
	public void setList(JList<String> list)
	{
		this.list = list;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((AbstractButton) e.getSource()).getText();
		
		//button delete
		if(item_name.equals(">>"))
		{
			int index = list.getSelectedIndex();
			if (index < 0) return;
			
			repo.removeExtension(index);
			filter_view.show(repo.getAcceptedExtensions());
			
			//appliquer les changements
			repo.setNoSearching();
			refreshRepoView(repo, repo_view);
			
			return;
		}
		
		//button add
		if(item_name.equals("<<")){
			
			if(filter_view.editorEmpty()) return;
			
			repo.addExtension(filter_view.getEditor());
			
			filter_view.show(repo.getAcceptedExtensions());
			filter_view.setEditor("");
			
			//appliquer les changements
			repo.setNoSearching();
			refreshRepoView(repo, repo_view);
			
			return;
		}
		
		//checkbox
		if(item_name.equals("autoriser tout"))
		{
			boolean checked = ((JCheckBox)e.getSource()).isSelected();
			
			repo.setAcceptance(checked);
			
			//appliquer les changements
			repo.setNoSearching();
			refreshRepoView(repo, repo_view);
			
			return;
		}
	}

}
