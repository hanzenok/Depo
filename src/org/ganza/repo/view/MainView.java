package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.filechooser.FileSystemView;

public class MainView extends JFrame {
	
	protected JPanel main_panel;
	protected JList list;
	
	public MainView(){
		
		main_panel = new JPanel(new FlowLayout());
		
		//configuration de JFrame
		setTitle("Files");
		setSize(300, 400);	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		
		//pour choisir les fichiers
		JFileChooser chooser = new JFileChooser();
		FileSystemView view = chooser.getFileSystemView();
		File[] files = view.getFiles(new File("/home/gunza/workspace"), true);
		
		//JList conteneur de toutes les items
		list = new JList();
		DefaultListModel listModel = new DefaultListModel();
		
		Icon I;
		JLabel label;
		
		for(int i=0; i<files.length; i++){
			
//			I = view.getSystemIcon(files[i]);
//			label = new JLabel(files[i].getName(), I, JLabel.CENTER);
			
			listModel.addElement(files[i]);
			
		}
		
		//ajout
		list.setModel(listModel);
		main_panel.add(new JScrollPane(list));
		setContentPane(main_panel);
		
		//afficher
		pack();
		setVisible(true);
	}
}

class FileListTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;
	  
}
