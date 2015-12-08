package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
		list.setCellRenderer(new FileListCellRenderer());
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

class FileListCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = -7799441088157759804L;
    private FileSystemView fileSystemView;
    private JLabel label;
    private Color textSelectionColor = Color.BLACK;
    private Color backgroundSelectionColor = Color.CYAN;
    private Color textNonSelectionColor = Color.BLACK;
    private Color backgroundNonSelectionColor = Color.WHITE;

    FileListCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getListCellRendererComponent(
        JList list,
        Object value,
        int index,
        boolean selected,
        boolean expanded) {

        File file = (File)value;
        label.setIcon(fileSystemView.getSystemIcon(file));
        label.setText(fileSystemView.getSystemDisplayName(file));
        label.setToolTipText(file.getPath());

        if (selected) {
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }

        return label;
    }
}
