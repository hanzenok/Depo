package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
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

import org.ganza.repo.model.RepoFile;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 1L;

	protected JPanel main_panel;
	protected JList<File> list;
	
	public MainView(){
		
		main_panel = new JPanel(new BorderLayout());
		
		//configuration de JFrame
		setTitle("Files");
//		setSize(3000, 4000);	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		
		//pour choisir les fichiers
		JFileChooser chooser = new JFileChooser();
		FileSystemView view = chooser.getFileSystemView();
		File[] files = view.getFiles(new File("/home/gunza/workspace"), true);
		
		//JList conteneur de toutes les items
		list = new JList<File>();
		list.setCellRenderer(new FileListCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		DefaultListModel<File> listModel = new DefaultListModel<File>();
		
		for(int i=0; i<files.length; i++){
			
			listModel.addElement(files[i]);
		}
		
		//ajout
		list.setModel(listModel);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(400, 300));
		main_panel.add(scroll, BorderLayout.CENTER);
		setContentPane(main_panel);
		
		//afficher
		pack();
		setVisible(true);
	}
}

class FileListTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;
	  
}

class FileListCellRenderer extends DefaultListCellRenderer
{
    private static final long serialVersionUID = 1L;
    private FileSystemView fileSystemView;
    private JLabel label;
    
//    private Color textSelectionColor = Color.BLACK;
    private Color backgroundSelectionColor = Color.GRAY;
//    private Color textNonSelectionColor = Color.BLACK;
    private Color backgroundNonSelectionColor = Color.WHITE;
    
    FileListCellRenderer()
    {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean expanded) 
    {	
    	//fichier
        File file = (File)value;
        
        //icon et texte
        label.setIcon(fileSystemView.getSystemIcon(file));
        label.setText(fileSystemView.getSystemDisplayName(file));
        
        if (selected) {
            label.setBackground(backgroundSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
        }
        
        return label;
    }
}
