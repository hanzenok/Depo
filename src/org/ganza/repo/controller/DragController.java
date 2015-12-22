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

public class DragController extends TransferHandler {

	private static final long serialVersionUID = 1L;
	
	private Repo repo;
	
	private JList<RepoFile> list;
	private DefaultListModel<RepoFile> listModel;
	
	public DragController(Repo repo)
	{
		this.repo = repo;
	}
	
	public void setList(JList<RepoFile> list)
	{
		this.list = list;
	}
	
	public void setListMode(DefaultListModel<RepoFile> listModel){
		
		this.listModel = listModel;
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
		
		System.out.println("Transferd BEACH!");
		
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
	}
}
