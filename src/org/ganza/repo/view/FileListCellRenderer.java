package org.ganza.repo.view;

import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

public class FileListCellRenderer extends DefaultListCellRenderer
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
