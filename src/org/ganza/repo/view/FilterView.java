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

public class FilterView extends JFrame{

	private static final long serialVersionUID = -7411493893110513171L;

	private JPanel main_panel;
	
	private JList<String> list;
	private JButton button_add;
	private JButton button_delete;
	private JTextArea editor;
	private JCheckBox allow;
	
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
	
	public void setFilterController(FilterController filter_controller)
	{
		filter_controller.setList(list);
		
		button_add.addActionListener(filter_controller);
		button_delete.addActionListener(filter_controller);
		allow.addActionListener(filter_controller);

	}
	
	public void setCloseDialogController(CloseDialogController dialog_controller)
	{
		addWindowListener(dialog_controller);
	}
	
	public boolean editorEmpty(){
		
		return editor.getText().length() == 0;
	}
	
	public String getEditor()
	{
		return editor.getText();
	}	
	
	public void setEditor(String str)
	{
		editor.setText(str);
	}
	
	public void setCheckBox(boolean state)
	{
		allow.setSelected(state);
	}
	
	public JList<String> getList()
	{
		return list;
	}
}
