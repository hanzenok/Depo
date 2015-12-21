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

public class DragController extends TransferHandler {

	private static final long serialVersionUID = 1L;
	  
	private JList list;
	private DefaultListModel listModel;

	public DragController(JList list) 
	{
		this.list = list;
		listModel = new DefaultListModel();
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
		
		return true;
	}
}
