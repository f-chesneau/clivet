package fr.eni.clinique.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.LoginMger;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.login.EcranLogin;

public class MainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBarre;
	private JMenu menuAgenda;
	
//	JFrame window= new JFrame();
	EcranLogin log;
	
	public MainFrame() 
		{
			super();
			initWindow_fullFrame();
			 initPanel();
		}
	
	//Initialize the window
	public void initWindow_fullFrame () 
	{
		setTitle("Clinique vétérinaire du chat qui tousse");
		setBounds(100, 100,400, 200);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Barre de menus
		setJMenuBar(getMenuBarre());
	}
	
	
	public void initPanel()
	{
		JDesktopPane desktopPane = new JDesktopPane();
		
		// Associer le JDesktopPane à la JFrame
		setContentPane(desktopPane);
		
		//Frame interne exemple		
		desktopPane.add(getLogWindow());
		
		getLogWindow().setVisible(true); // MD
		
		String log= getLogWindow().getLogField().getText();
		String pwd= String.valueOf(getLogWindow().getPwdField().getPassword());
		
		try
			{
				String role=LoginMger.getInstance().verifier(log, pwd);
				if(role!=null)
							{
								switch (role)
								{
									case "sec":
									{
										
									}
									
									case "vet":
									{
										
									}
									case "adm":
									{
										
									}
								}
							}
			} catch (BLLException e)
			{
				e.printStackTrace();
			} catch (DALException e)
			{
				e.printStackTrace();
			}	
	}
	
	public EcranLogin getLogWindow()
	{
		if(log== null)
			{
				log = new EcranLogin();
			}
		return log;
	}

	public void createMenuBar()
	{
		// Menu Fichier
			JMenu menuFichier = new JMenu("Fichier");
			menuBarre.add(menuFichier);

			// Sous menu Déconnexion
			JMenuItem menuItem = new JMenuItem("Déconnexion");
			menuItem.setActionCommand("deconnexion");
			menuItem.addActionListener(this);
			menuFichier.add(menuItem);

			// Sous menu fermer
			menuItem = new JMenuItem("Fermer");
			menuItem.setActionCommand("fermer");
			menuItem.addActionListener(this);
			menuFichier.add(menuItem);

		// Menu RDV
			JMenu menuRdv = new JMenu("Gestion des rendez-vous");
			menuBarre.add(menuRdv);
			
			//Sous menu Prise rdv
			menuItem = new JMenuItem("Prise de rendez-vous");
			menuRdv.add(menuItem);		
			menuItem.setActionCommand("ecranRdv");
			menuItem.addActionListener(this);
			
			//Sous menu gestion clients
			menuItem = new JMenuItem("Gestion des clients");
			menuRdv.add(menuItem);		
			menuItem.setActionCommand("ecranClients");
			menuItem.addActionListener(this);
			
			//Sous menu gestion animaux
			menuItem = new JMenuItem("Gestion des animaux");
			menuRdv.add(menuItem);		
			menuItem.setActionCommand("ecranAnimaux");
			menuItem.addActionListener(this);
			
		//	Menu Agenda
			JMenu menuAgenda = new JMenu("Agenda");
			menuBarre.add(menuAgenda);
			
		//	Menu RH
			JMenu menuRH = new JMenu("Gestion du personnel");
			menuBarre.add(menuRH);
	}
	
	public JMenuBar getMenuBarre()
		{
		if (menuBarre == null) {
			menuBarre = new JMenuBar();

			createMenuBar();
		}
		return menuBarre;
	}

	@Override
	public void actionPerformed (ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}
}
	

