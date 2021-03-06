package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.ganza.repo.controller.ClickController;
import org.ganza.repo.controller.DragController;
import org.ganza.repo.controller.ExitController;
import org.ganza.repo.controller.MenuController;
import org.ganza.repo.controller.PopupController;
import org.ganza.repo.model.RepoFile;

/**
 * RepoView est une vue principale
 * de la programme
 * @author Ganza Mykhailo
 */
public class RepoView extends JFrame {
	
	private static final long serialVersionUID = 6665797925474960011L;

	private JPanel main_panel; //panneau principale
	
	//les menus
	private JMenuBar menubar;
	private JMenuItem new_menu;
	private JMenuItem open_menu;
	private JMenuItem save_menu;
	private JMenuItem close_menu;
	private JMenuItem addfilter_menu;
	private JMenuItem launch_menu;
	private JMenuItem cancel_menu;
	private JMenuItem author_menu;
	
	//les popup menus
	private JPopupMenu popup_menu;
	private JMenuItem showxml_menu;
	private JMenuItem editxml_menu;
	private JMenuItem delete_menu;
	
	private JList<RepoFile> list; //liste container des RepoFile's
	private DefaultListModel<RepoFile> listModel; //LisModes associé
	
	/**
	 * Constructeur principale
	 * Initialise toutes le élements
	 * de l'interface
	 */
	public RepoView(){
		
		//menu
		menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu repo_menu = new JMenu("Depôt");
        new_menu = new JMenuItem("Nouveau"); new_menu.setToolTipText("Créer un nouveau depôt");
        save_menu = new JMenuItem("Sauvegarder"); save_menu.setToolTipText("Sauvegarder le depôt");
        open_menu = new JMenuItem("Ouvrir"); open_menu.setToolTipText("Ouvrir un depôt existante");
        close_menu = new JMenuItem("Fermer"); close_menu.setToolTipText("Ferm le depôt"); 
        
        repo_menu.add(new_menu); repo_menu.addSeparator(); 
        repo_menu.add(save_menu); repo_menu.add(open_menu); 
        repo_menu.addSeparator(); repo_menu.add(close_menu);
        
        JMenu filter_menu = new JMenu("Filtre");
        addfilter_menu = new JMenuItem("Appliquer"); addfilter_menu.setToolTipText("Ajouter des filtres");
        filter_menu.add(addfilter_menu);
        
        JMenu search_menu = new JMenu("Recherche");
        launch_menu = new JMenuItem("Lancer"); launch_menu.setToolTipText("Lancer la recherche");
        cancel_menu = new JMenuItem("Annuler"); cancel_menu.setToolTipText("Annuler la recherche");
        search_menu.add(launch_menu); search_menu.addSeparator(); search_menu.add(cancel_menu);
        
        JMenu treatment_menu = new JMenu("Traitement");
        author_menu = new JMenuItem("Définir l'auteur"); author_menu.setToolTipText("Définir un auteur pour les fichiers visibles");
        treatment_menu.add(author_menu);
        
        menubar.add(repo_menu); menubar.add(filter_menu);
        menubar.add(search_menu); menubar.add(treatment_menu);
        
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
	
	/**
	 * Active/desactvie certains
	 * éléments de la vue
	 * @param enabled activer ou pas
	 */
	public void setReady(boolean enabled)
	{
		save_menu.setEnabled(enabled);
		close_menu.setEnabled(enabled);
		addfilter_menu.setEnabled(enabled);
		launch_menu.setEnabled(enabled);
		cancel_menu.setEnabled(enabled);
		author_menu.setEnabled(enabled);
	}
	
	/**
	 * Faire des sorte que 
	 * le drag&drop soit réalisable
	 * @param dragable état
	 */
	public void setDragable(boolean dragable)
	{
		list.setDragEnabled(dragable);
	}
	
	/**
	 * Défini le controlleur
	 * @param drag_controller controlleur de drag&drop
	 */
	public void setDragController(DragController drag_controller)
	{			
		list.setTransferHandler(drag_controller);
	}
	
	/**
	 * Défini le controlleur 
	 * @param menu_controller controlleur des menus
	 */
	public void setMenuController(MenuController menu_controller)
	{	
		new_menu.addActionListener(menu_controller);
		save_menu.addActionListener(menu_controller);
		open_menu.addActionListener(menu_controller);
		close_menu.addActionListener(menu_controller);
		addfilter_menu.addActionListener(menu_controller);
		launch_menu.addActionListener(menu_controller);
		cancel_menu.addActionListener(menu_controller);
		author_menu.addActionListener(menu_controller);
	}
	
	/**
	 * Défini le controlleur
	 * @param exit_controller controlleur de sortie
	 */
	public void setExitController(ExitController exit_controller)
	{	
		addWindowListener(exit_controller);
	}
	
	/**
	 * Défini le controlleur
	 * @param click_controller controlleur de clique sur la
	 * la liste des RepoFile's
	 */
	public void setClickController(ClickController click_controller)
	{	
		click_controller.setList(list);
		list.addMouseListener(click_controller);
	}
	
	/**
	 * Défini le controlleur
	 * @param popup_controller controlleur des menus popup
	 */
	public void setPopupController(PopupController popup_controller)
	{	
		showxml_menu.addActionListener(popup_controller);
		editxml_menu.addActionListener(popup_controller);
		delete_menu.addActionListener(popup_controller);
	}
	
	/**
	 * Reinitialise la vue
	 * @param title titre de la vue
	 */
	public void initialize(String title){
		
		setTitle(title);
		
		main_panel = new JPanel(new BorderLayout());
		
		list = new JList<RepoFile>();
		list.setCellRenderer(new FileListCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setDragEnabled(true); //par defaut
		
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
	
	/**
	 * Reinitialise la vue
	 */
	public void initialize()
	{
		initialize("Depôt");
	}
	
	/**
	 * Renvoi le ListModel de la JList list
	 * @return ListModel associé à list
	 */
	public DefaultListModel<RepoFile> getListModel()
	{
		return listModel;
	}
	
	/**
	 * Défini le ListModel de la JList list
	 * @param listModel à définir
	 */
	public void setListModel(DefaultListModel<RepoFile> listModel)
	{
		this.listModel = listModel;
		list.setModel(this.listModel);
	}
	
	/**
	 * Affiche le popup à
	 * a la clique droite sur 
	 * un des éléments de la JList list
	 */
	public void showPopup(MouseEvent e)
	{
		popup_menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
