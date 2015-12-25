package org.ganza.repo.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import org.ganza.repo.controller.CloseDialogController;
import org.ganza.repo.controller.FilterController;

/**
 * FilterView est un dialog
 * de modification des options
 * de filtrage
 * Controlée par FilterController
 * @author Ganza Mykhailo
 */
public class FilterView extends JFrame{

	private static final long serialVersionUID = -7411493893110513171L;

	private JPanel main_panel; //panneau principale
	
	private JList<String> list; //list des extensions acceptables
	private JButton button_add; //bouton d'ajout d'une nouvelle extension
	private JButton button_delete; //bouton de suppression d'extension
	private JTextArea editor; //pour ajouter le nom d'extension
	private JCheckBox allow; //checkbox pour autoriser ou pas les fichiers avec toutes les extensions
	
	/**
	 * Constructeur principale
	 */
	public FilterView()
	{
        //panneau principal
		main_panel = new JPanel(new GridBagLayout());
		
		setTitle("Filtre");
		setMinimumSize(new Dimension(250, 150));
		setLocationByPlatform(true);
		
		button_add = new JButton("<<");
		button_delete = new JButton(">>");
		editor = new JTextArea(2,8);
		allow = new JCheckBox("autoriser tout");
	}
	
	/**
	 * Mit à jour la vue FilterView
	 * à partir de la liste des extensions
	 * acceptées
	 * @param extensions liste des extensions
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void show(ArrayList<String> extensions)
	{	
		GridBagConstraints c = new GridBagConstraints();
		
		main_panel = new JPanel(new GridBagLayout());
		
		//label extensions
		c.gridx = 0; c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel ext_label = new JLabel("Extensions:");
		main_panel.add(ext_label, c);

		//list
		c.gridy = 1; c.weightx = 2; c.gridheight = 5;
		c.fill = GridBagConstraints.BOTH;
		list = new JList(extensions.toArray()); 
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		main_panel.add(new JScrollPane(list), c);
		
		//button add
		c.gridx = 1; c.gridy = 1; c.gridheight = 1;
		c.fill = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		main_panel.add(button_add, c);
		
		//editor
		c.gridy = 2;
		c.fill = GridBagConstraints.CENTER;
		main_panel.add(editor, c);
		
		//button delete
		c.gridy = 3;
		c.fill = GridBagConstraints.SOUTH;
		main_panel.add(button_delete, c);
		
		//checkbox
		c.gridy = 4;
		main_panel.add(allow, c);
		
		//ajout
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
	/**
	 * Défini le controlleur
	 * @param filter_controller controlleur de la vue 
	 * de filtrage
	 */
	public void setFilterController(FilterController filter_controller)
	{
		filter_controller.setList(list);
		
		button_add.addActionListener(filter_controller);
		button_delete.addActionListener(filter_controller);
		allow.addActionListener(filter_controller);
	}
	
	/**
	 * Défini le controlleur
	 * Besoion de cela pour 
	 * reactiver la vue principale 
	 * à la fermeture de dialog
	 * @param dialog_controller controlleur de drag&drop
	 */
	public void setCloseDialogController(CloseDialogController dialog_controller)
	{
		addWindowListener(dialog_controller);
	}
	
	/**
	 * Vérifie si l'editor
	 * est vide ou ps
	 * @return l'état d'editor
	 */
	public boolean editorEmpty(){
		
		return editor.getText().length() == 0;
	}
	
	/**
	 * Renvoi le texte de l'editor
	 * @return texte de l'editor
	 */
	public String getEditor()
	{
		return editor.getText();
	}	
	
	/**
	 * Setter d'un editor
	 * @param str texte à ajouter 
	 * dans l'editor
	 */
	public void setEditor(String str)
	{
		editor.setText(str);
	}
	
	/**
	 * Défini l'état de checkbox
	 * @param state l'état de checkbox
	 */
	public void setCheckBox(boolean state)
	{
		allow.setSelected(state);
	}
	
	/**
	 * Renvoi la liste container 
	 * des extensions
	 * @return liste avec les extensions accatées
	 */
	public JList<String> getList()
	{
		return list;
	}
}
