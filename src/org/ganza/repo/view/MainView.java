package org.ganza.repo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class MainView extends JFrame {
	
	protected JPanel main_panel;
	
	public MainView(){
		
		WrapLayout wl = new WrapLayout();
		main_panel = new JPanel(new FlowLayout());

		setTitle("Files");
		setSize(300, 400);	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		
		JFileChooser chooser = new JFileChooser();
		FileSystemView view = chooser.getFileSystemView();
		
		Icon I;
		JLabel label;
		
		System.out.println(view.getDefaultDirectory());
		
		File[] files = view.getFiles(new File("/home/gunza/workspace"), true);
		
		for(int i=0; i<files.length; i++){
			
			I = view.getSystemIcon(files[i]);
			label = new JLabel(files[i].getName(), I, JLabel.CENTER);
			
			main_panel.add(label);
			
		}
		
		setContentPane(main_panel);
		
		//afficher
		pack();
		setVisible(true);
	}
}
