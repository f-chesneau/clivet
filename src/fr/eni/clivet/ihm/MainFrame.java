package fr.eni.clivet.ihm;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	public void initWindow_fullFrame () 
		{
		setTitle("Clinique vétérinaire du chat qui tousse");
		setBounds (400,200,800,500);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		}

		
	

}
