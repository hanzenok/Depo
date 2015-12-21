package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowEvent;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.filechooser.FileSystemView;

import org.ganza.repo.controller.DragController;
import org.ganza.repo.controller.ExitController;
import org.ganza.repo.controller.MenuController;
import org.ganza.repo.model.RepoFile;

public class RepoView extends JFrame {
	
	private static final long serialVersionUID = 1L;

	protected JPanel main_panel;
	
	protected JMenuBar menubar;
	protected JMenuItem new_menu;
	protected JMenuItem open_menu;
	protected JMenuItem save_menu;
	
	protected JFileChooser chooser;
	protected FileSystemView view;
	
	protected JList<File> list;
	DefaultListModel<File> listModel;
	
	public RepoView(){
		
		//Menu ==================================================
		menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu repo = new JMenu("Depôt");
        new_menu = new JMenuItem("Nouveau"); new_menu.setToolTipText("Créer un nouveau depôt");
        save_menu = new JMenuItem("Sauvegarder"); save_menu.setToolTipText("Sauvegarder le depôt"); save_menu.setEnabled(false);
        open_menu = new JMenuItem("Ouvrir"); open_menu.setToolTipText("Ouvrir un depôt existante"); open_menu.setEnabled(false);
        repo.add(new_menu); repo.add(save_menu); repo.add(open_menu);
       
        menubar.add(repo);
		
        //Panneau principal =====================================
		main_panel = new JPanel(new BorderLayout());
		
		setTitle("Repositoire");
		setMinimumSize(new Dimension(400, 300));
		setLocationByPlatform(true);
		
		pack();
		setVisible(true);
	}
	
	public void setReady(boolean enabled)
	{
		save_menu.setEnabled(enabled);
		open_menu.setEnabled(enabled);
	}
	
	public void setDragable(boolean dragable)
	{
		list.setDragEnabled(dragable);
	}
	
	public void setDragController(DragController drag_controller)
	{	
		drag_controller.setList(list);
		drag_controller.setListMode(listModel);
		list.setTransferHandler(drag_controller);
	}
	
	public void setMenuController(MenuController menu_controller)
	{
		open_menu.addActionListener(menu_controller);
		save_menu.addActionListener(menu_controller);
		new_menu.addActionListener(menu_controller);
	}
	
	public void setExitController(ExitController exit_controller)
	{
		addWindowListener(exit_controller);
	}
	
	public void initialize(){
		
		main_panel = new JPanel(new BorderLayout());
		
		//JList conteneur des fichiers
		list = new JList<File>();
		list.setCellRenderer(new FileListCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setDragEnabled(false); //par defaut
		
		//model d'affichage
		listModel = new DefaultListModel<File>();
		list.setModel(listModel);
		
		//ajout
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(400, 300));
		main_panel.add(scroll, BorderLayout.CENTER);
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
	
	public void refresh(String folder_path)
	{
		//pour choisir les fichiers
		JFileChooser chooser = new JFileChooser();
		FileSystemView view = chooser.getFileSystemView();
		File[] files = view.getFiles(new File(folder_path), true);
		
		for(int i=0; i<files.length; i++){
			
			listModel.addElement(files[i]);
		}
		
	}
}
