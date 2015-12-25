package org.ganza.repo.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import org.ganza.repo.controller.EditorController;

/**
 * EditorView est un dialog
 * d'edition des attributs xµl
 * Controlé par EditorController
 * @author Ganza Mykhailo
 */
public class EditorView extends JFrame{

	private static final long serialVersionUID = 380295441672869172L;
	
	private JPanel main_panel; //panneu principal
	
	private JTextArea editor; //champ pour editer la valeur d'une balise
	private JButton button; //boutone des sauvegargde
	private JList<String> list; //list des attributes (balises xml)
	
	/*
	 * Constructeur principale
	 */
	public EditorView()
	{
        //pnneau principal
		main_panel = new JPanel(new GridBagLayout());
		
		setTitle("Edition des balises XML");
		setMinimumSize(new Dimension(250, 150));
		setLocationByPlatform(true);
		
		editor = new JTextArea();
		editor.setLineWrap(true);
		button = new JButton("Sauvegarder");
	}
	
	/**
	 * Défini le controlleur
	 * @param editor_controller controlleur
	 */
	public void setEditorController(EditorController editor_controller)
	{	
		editor_controller.setList(list);
		
		list.addListSelectionListener(editor_controller);
		button.addActionListener(editor_controller);
		
		list.setSelectedIndex(0);//element choisi par defaut
		
	}
	
	/**
	 * Mit à jour de la vue
	 * à partir de la liste 
	 * des attributes xml
	 * @param attributes
	 */
	public void show(String[] attributes)
	{
		GridBagConstraints c = new GridBagConstraints();
		
		//label attr
		c.gridx = 0; c.gridy = 0; c.gridwidth = 1;
		c.weightx = 2; c.fill = GridBagConstraints.HORIZONTAL;
		JLabel attr_label = new JLabel("Attributes:");
		main_panel.add(attr_label, c);
		
		//label value
		c.gridx = 1; c.gridy = 0; c.weightx = 3; 
		JLabel value_label = new JLabel("Valeur:");
		main_panel.add(value_label, c);
		
		//list
		c.gridx = 0; c.gridy = 1; c.weightx = 2;
		list = new JList<String>(attributes); 
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		main_panel.add(new JScrollPane(list), c);
		
		//textatea
		c.gridx = 1; c.gridy = 1; c.weightx = 3;
		c.fill = GridBagConstraints.BOTH;
		main_panel.add(new JScrollPane(editor), c);
		
		//bouton
		c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.weightx = 100;
		main_panel.add(button, c);
		
		//ajout
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
	
	/**
	 * Afficher value dans 
	 * l'editor
	 * @param value valeur de l'attribut xml
	 * dans l'editor
	 */
	public void showValue(String value)
	{
		editor.setText(value);
	}
	
	/**
	 * Renovoi le texte dans l'editor
	 * @return texte dans l'editor
	 */
	public String getValue()
	{
		return editor.getText();
	}
	
	/**
	 * Activation/desactionvation
	 * de l'editor
	 * Utilisé pour ne pas pouvoir 
	 * modifier la balise "name" de fichier 
	 * RepoFile
	 * @param state activer ou pas l'editor
	 */
	public void setEditorEditable(boolean state)
	{
		editor.setEditable(state);
	}
}
