package fr.eni.clinique.ihm.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.LoginMger;
import fr.eni.clinique.dal.DALException;

public class EcranLogin  extends JInternalFrame implements ActionListener
	{
	
		private static final long serialVersionUID = 1L;
		private JPanel conPan;
		private JLabel logLabel, pwdLabel;
		private JTextField logField;
		private JPasswordField pwdField;
		JButton validation;
		

		public  EcranLogin () 
			{
				//title, resizable, closable, extendable, iconizable
				super("Connexion", true, true, true,true);
				setBounds(600, 300,300, 130);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
				
				this.getContentPane().setLayout(new GridBagLayout());
				GridBagConstraints gbc= new GridBagConstraints();
				gbc.insets= new Insets(5,5,5,5);
				
				conPan= new JPanel();
				
				logLabel= new JLabel ("Login");
				logLabel.setSize(100, 150);
				logLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				logField= new JTextField();
				logField.setPreferredSize(new Dimension (200, 20));
				logField.addActionListener(this);
				
				gbc.gridx=0;
				gbc.gridy=0;
				this.add(logLabel,gbc);
				
				gbc.gridx=1;
				this.add(logField,gbc);
				
				pwdLabel= new JLabel ("MdP  ");
				pwdLabel.setSize(100, 150);
				pwdLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				pwdField= new JPasswordField();
				pwdField.setPreferredSize(new Dimension (200, 20));
				pwdField.addActionListener(this);
				
				gbc.gridx=0;
				gbc.gridy=1;
				this.add(pwdLabel,gbc);
				
				gbc.gridx=1;
				this.add(pwdField,gbc);
								
				gbc.gridx=1;
				gbc.gridy=2;
				validation= new JButton ("Valider");
				validation.setEnabled(true);
				validation.addActionListener(this);
				this.getContentPane().add(validation,gbc);

				this.getContentPane().add(conPan);
/*				this.getContentPane().setLayout(new BorderLayout());
				
				//Define Connexion Panel
				JPanel conPan= new JPanel();
				
				JLabel logLabel= new JLabel ("Login");
				logLabel.setSize(100, 150);
				logLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				JTextField logField= new JTextField();
				logField.setPreferredSize(new Dimension (200, 20));
				
				JPanel pwdPan= new JPanel();
				JLabel pwdLabel= new JLabel ("MdP  ");
				pwdLabel.setSize(100, 150);
				pwdLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				JPasswordField pwdField= new JPasswordField();
				pwdField.setPreferredSize(new Dimension (200, 20));
				
				conPan.add(logLabel);
				conPan.add(logField);
				pwdPan.add(pwdLabel);
				pwdPan.add(pwdField);
				
				this.getContentPane().add(conPan, BorderLayout.NORTH);
				this.getContentPane().add(pwdPan, BorderLayout.CENTER);
	
				 
			    this.getContentPane().add(new JButton ("Valider"), BorderLayout.SOUTH);*/
			   
				
				
			}

		@Override
		public void actionPerformed (ActionEvent e)
			{
				String log, pwd;
				Object source=e.getSource();
				
				validation.setEnabled(true);
				
				while (logField.getText().length()==0)
					{	logField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						logField.setBackground(Color.BLUE);
						System.out.println ("Coucou");
					}
				while (pwdField.getPassword().length==0)
					{
						
					}
				if ((logField.getText().length()!=0)&&(pwdField.getPassword().length!=0))
					{
						validation.setEnabled(true);
						System.out.println (String.valueOf(pwdField.getPassword()));
								
					}
				
				if (source==validation)
						
						try
							{
								String utilisateurConnu=LoginMger.getInstance().verifier (logField.getText(),String.valueOf(pwdField.getPassword()));
								if (utilisateurConnu!=null) 
									{
										System.out.println("validation nom et mdp ok");
										dispose();
									}
									else
										{
											JOptionPane.showMessageDialog(this, "Sort de ce corps, HackerDeLaMeule !!!");
										}
							}
						catch (BLLException e1)
							{
								e1.printStackTrace();
							}
						catch (DALException e1)
							{
								e1.printStackTrace();
							}
						logField.getText();
						pwdField.getPassword().toString();
						System.out.println(logField.getText());
						System.out.println(pwdField.getPassword());
					}

		public JTextField getLogField ()
			{
				return logField;
			}

		public JPasswordField getPwdField ()
			{
				return pwdField;
			}
	
/*				switch (e.getActionCommand())
				{
					case "logField":
					{
						log= logField.getText();
						break;
					}
					case "pwdField":
					{
						pwd= pwdField.getPassword().toString();
						break;
					}
					case "validation":
					{
						System.out.println("mon papa c'est mon papy");
						logField.setText("Glabzru");
						break;
					}
				}
				*/
				
			}
		
			
	
