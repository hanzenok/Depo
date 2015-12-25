package org.ganza.repo.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

/**
 * Gére le drag&drop
 * @author Ganza Mykhailo
 */
public class DragController extends TransferHandler {

	private static final long serialVersionUID = -7838465896297769967L;
	
	private Repo repo; //depôt
	private RepoView repo_view; //la vue principale
	
	/**
	 * Constructeur principale
	 * 
	 * @param repo depôt
	 * @param repo_view la vue principale
	 */
	public DragController(Repo repo, RepoView repo_view)
	{
		this.repo = repo;
		this.repo_view = repo_view;
	}
	
	/**
	 * Renvoi le type d'action
	 * Copier ou couper
	 */
	public int getSourceActions(JComponent c) 
	{	
		return COPY_OR_MOVE;
	}

	/**
	 * Apelée pendant le drag&drop
	 */
	public boolean canImport(TransferSupport ts) 
	{
		return ts.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
	}
	
	/**
	 * Rélise le Drag&Drop
	 * Ajout nouveu fichier dans la modele Repo
	 * et remit à jour la vue RepoView
	 */
	public boolean importData(TransferSupport ts) {
		
		try{
	        @SuppressWarnings("unchecked")
			List<RepoFile> data = ((List<RepoFile>) ts.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
	        
	        if (data.size() < 1) 
	        {
	        	return false;
	        }
	        
	        //reccuperer le fichier
	        for (Object item : data) 
	        {
	        	//fichier
	        	File file = (File) item;
	        	RepoFile repofile = new RepoFile(file);
	        	
	        	//ajout
	        	if(!repo.exists(repofile))
	        		repo.addRFile(repofile);
	        }
	        
	        //reinitialisaiton de la vue
	        refreshRepoView();
	        
	        return true;
		}
		catch (UnsupportedFlavorException e) {return false;} 
		catch (IOException e) {return false;} 
		catch (JDOMException e) {return false;}
	}
	
	/**
	 * Fonction de remise à jour
	 * de la vue
	 */
	public void refreshRepoView() 
	{	
		//normalement DragController doir heriter le RepoController
		//mais il ne peux pas
		RepoController repo_controller = new RepoController();
		repo_controller.refreshRepoView(repo, repo_view);
	}
}
