package org.ganza.repo.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import org.ganza.repo.model.RepoFile;

/**
 * Rendeur de la JList des RepoFile's
 * de la vue principale RepoView
 * Utilise les icones de la Systeme
 * @author Ganza Mykhailo
 */
public class FileListCellRenderer extends DefaultListCellRenderer
{
 
	private static final long serialVersionUID = -8792995735838787400L;

	private FileSystemView fileSystemView; //pour reccuperer les icones systeme
    private JLabel label; //nom de fichier
    
    //couleurs
    private Color backgroundSelectionColor = Color.GRAY;
    private Color backgroundNonSelectionColor = Color.WHITE;
    
    /**
     * Constructeur principale
     */
    public FileListCellRenderer()
    {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean expanded) 
    {	
    	//fichier
        RepoFile repofile = (RepoFile)value;
        
        //icon et texte
        label.setIcon(fileSystemView.getSystemIcon(repofile.getFile()));
        label.setText(fileSystemView.getSystemDisplayName(repofile.getFile()));
        
        if (selected) {        	
            label.setBackground(backgroundSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
        }
        
        return label;
    }
}
