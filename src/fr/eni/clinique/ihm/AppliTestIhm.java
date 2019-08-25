package fr.eni.clinique.ihm;

import javax.swing.JDesktopPane;

import fr.eni.clinique.ihm.login.EcranLogin;

public class AppliTestIhm
{
	private JDesktopPane desktopPane;
	private EcranLogin ecrLog= new EcranLogin();
	
	 
	
	
	public static void main (String[] args)
		{
			
			MainFrame mainFrame= new MainFrame();
				
//			mainFrame.initWindow_fullFrame();
//				 mainFrame.initPanel();
				 mainFrame.setVisible(true);
	
//				 EcranLogin log= new EcranLogin();
//				 log.setVisible(true);

		
		

	}
}

	
