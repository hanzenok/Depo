package org.ganza.repo.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.ganza.repo.view.View;

public class MenuController implements ActionListener
{
	private View view;
	
	public MenuController(View view)
	{
		this.view = view;
		this.view.setMenuController(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String item_name = ((JMenuItem)e.getSource()).getText();
		
		System.out.println("Hi");
	}

}
