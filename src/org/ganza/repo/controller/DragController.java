package org.ganza.repo.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;

import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;
import org.ganza.repo.view.RepoView;
import org.jdom2.JDOMException;

public class DragController extends TransferHandler {

	private static final long serialVersionUID = 1L;
	
	private Repo repo;
	private JList<RepoFile> list;
	private DefaultListModel<RepoFile> listModel;
	
	public void setList(JList<RepoFile> list)
	{
		this.list = list;
	}
	
	public void setListModel(DefaultListModel<RepoFile> listModel)
	{
		this.listModel = listModel;
	}
	
	public DragController(Repo repo)
	{
		this.repo = repo;
	}

	public int getSourceActions(JComponent c) 
	{	
		return COPY_OR_MOVE;
	}

	public boolean canImport(TransferSupport ts) 
	{
		return ts.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
	}

	public boolean importData(TransferSupport ts) {
		
		try{
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
	        	listModel.addElement(repofile);
	        	repo.addRFile(repofile);
	        }
	
	        list.setModel(listModel);
	        
	        return true;
		}
		catch (UnsupportedFlavorException e) {return false;} 
		catch (IOException e) {return false;} 
		catch (JDOMException e) {return false;}
	}
	
//	public void refreshRepoView()
//	{	
//		repo_view.resetList();
//		
//		DefaultListModel<RepoFile> listModel = repo_view.getListModel();
//		
//		int i, n = repo.size();
//		
//		for(i=0; i<n; i++){
//			
//			listModel.addElement(repo.getRFile(i));
//		}
//	}
}
