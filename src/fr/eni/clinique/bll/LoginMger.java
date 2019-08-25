package fr.eni.clinique.bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.dal.jdbc.JdbcConnect;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAO;
import fr.eni.clinique.dal.PersonnelDaoJdbcImpl;



public class LoginMger 
{
	private static final String SQL_LOGIN = "Select Nom, MotPasse, Role from Personnels Where Nom LIKE ? AND MotPasse LIKE ?;";
	
	private DAO dao;
	private static LoginMger instance;
	
	// Constructor
	private LoginMger() throws BLLException
	 	{
		dao = PersonnelDaoJdbcImpl.getInstance();
		}
	
	// Pseudo-Constructor Singleton
			public static synchronized LoginMger getInstance() throws BLLException
			{
				if (instance==null)			{	
					instance=new LoginMger();}
				return instance;
			}

	
	public String verifier(String nom, String motPasse) throws BLLException, DALException
	{	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		try 
		{			
			connexion = JdbcConnect.getInstance();
			preparedStatement = connexion.prepareStatement(SQL_LOGIN);

			// valoriser les paramètres
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, motPasse);
			
			// Exécuter
			ResultSet rsLogin = preparedStatement.executeQuery();
				
			//Utilisation résultat
			
				if (rsLogin.next()) 
				{					
					return rsLogin.getString("Role");			
				}
			}
		catch (SQLException e)
			{
				throw new BLLException("problème méthode verifier", e);
			}
		finally 
		{
		try 
		{ 	// fermeture resultat, preparedStatement et connexion
			if (resultat != null) 
			{
				resultat.close();
			}
			if (preparedStatement != null) 
			{
				preparedStatement.close();
			}
			if (connexion != null) 
			{
				connexion.close();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		}
		return null;	
	}
}
