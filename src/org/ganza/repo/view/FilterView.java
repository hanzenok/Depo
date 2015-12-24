package org.ganza.repo.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FilterView extends JFrame{

	private static final long serialVersionUID = -7411493893110513171L;

	private JPanel main_panel;
	
	public FilterView()
	{
		
        //pnneau principal
		main_panel = new JPanel(new GridBagLayout());
		
		setTitle("Filtre");
		setMinimumSize(new Dimension(250, 150));
		setLocationByPlatform(true);
		
		//ajout
		setContentPane(main_panel);
		
		//rendre visible
		pack();
		setVisible(true);
	}
}
