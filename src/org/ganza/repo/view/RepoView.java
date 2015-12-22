package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.filechooser.FileSystemView;

import org.ganza.repo.controller.ClickController;
import org.ganza.repo.controller.DragController;
import org.ganza.repo.controller.ExitController;
import org.ganza.repo.controller.MenuController;
import org.ganza.repo.controller.PopupController;
import org.ganza.repo.model.Repo;
import org.ganza.repo.model.RepoFile;

public class RepoView extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel main_panel;
	
	private JMenuBar menubar;
	private JMenuItem new_menu;
	private JMenuItem open_menu;
	private JMenuItem save_menu;
	private JMenuItem close_menu;
	
	private JPopupMenu popup_menu;
	private JMenuItem showxml_menu;
	private JMenuItem editxml_menu;
	private JMenuItem delete_menu;
	
	
	private JList<RepoFile> list;
	private DefaultListModel<RepoFile> listModel;
	
	public RepoView(){
		
		//menu
		menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu repo_menu = new JMenu("Depôt");
        new_menu = new JMenuItem("Nouveau"); new_menu.setToolTipText("Créer un nouveau depôt");
        save_menu = new JMenuItem("Sauvegarder"); save_menu.setToolTipText("Sauvegarder le depôt");
        open_menu = new JMenuItem("Ouvrir"); open_menu.setToolTipText("Ouvrir un depôt existante");
        close_menu = new JMenuItem("Fermer"); close_menu.setToolTipText("Ferm le depôt"); 
        
        repo_menu.add(new_menu); repo_menu.add(new JSeparator()); 
        repo_menu.add(save_menu); repo_menu.add(open_menu); 
        repo_menu.add(new JSeparator()); repo_menu.add(close_menu);
       
        menubar.add(repo_menu);
		
        //menu popup
        popup_menu = new JPopupMenu();
        showxml_menu = new JMenuItem("Reinsegner XML"); showxml_menu.setToolTipText("Reinsegner les balises de fihcier xml");
        editxml_menu = new JMenuItem("Modifier XML"); editxml_menu.setToolTipText("Modifier le fichier xml");
        delete_menu = new JMenuItem("Supprimer"); delete_menu.setToolTipText("Supprimer le fichier de la base");
        
        popup_menu.add(showxml_menu); popup_menu.add(editxml_menu); 
        popup_menu.add(new JPopupMenu.Separator()); popup_menu.add(delete_menu);
        
        //manneau principal
		main_panel = new JPanel(new BorderLayout());
		
		setMinimumSize(new Dimension(400, 300));
		setLocationByPlatform(true);
		
		//preparation
		initialize();
		setReady(false);
		
		//packing
		pack();
		setVisible(true);
	}
	
	public void setReady(boolean enabled)
	{
		save_menu.setEnabled(enabled);
		open_menu.setEnabled(enabled);
		close_menu.setEnabled(enabled);
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
		new_menu.addActionListener(menu_controller);
		save_menu.addActionListener(menu_controller);
		open_menu.addActionListener(menu_controller);
		close_menu.addActionListener(menu_controller);
		
		System.out.println("menu listeners: " + open_menu.getActionListeners().length);
	}
	
	public void setExitController(ExitController exit_controller)
	{	
		addWindowListener(exit_controller);
	}
	
	public void setClickController(ClickController click_controller)
	{	
		click_controller.setList(list);
		list.addMouseListener(click_controller);
	}
	
	public void setPopupController(PopupController popup_controller)
	{	
		//if(showxml_menu.getActionListeners().length >= 1) return;
		
		showxml_menu.addActionListener(popup_controller);
		editxml_menu.addActionListener(popup_controller);
		delete_menu.addActionListener(popup_controller);
		
		System.out.println("popup listeners: " + showxml_menu.getActionListeners().length);
	}
	
	public void initialize(){
		
		setTitle("Repository");
		
		main_panel = new JPanel(new BorderLayout());
		
		//JList conteneur des fichiers
		list = new JList<RepoFile>();
		list.setCellRenderer(new FileListCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setDragEnabled(false); //par defaut
		
		//model d'affichage
		listModel = new DefaultListModel<RepoFile>();
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
	
	public void refresh(Repo repo)
	{
		int i, n = repo.size();
		
		for(i=0; i<n; i++){
			
			listModel.addElement(repo.getRFile(i));
		}
		
	}
	
	public void showPopup(MouseEvent e)
	{
		popup_menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
