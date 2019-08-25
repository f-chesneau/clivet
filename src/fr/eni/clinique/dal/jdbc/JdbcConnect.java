package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.clinique.dal.DALException;

public final class JdbcConnect
{
	//Properties
	private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;" +  
	         "databaseName=clivet;integratedSecurity=true;";

	
	//Single instance
	private static JdbcConnect instance=new JdbcConnect();
	
	//JDBC objects
	Connection con = null; 
			
	
	//Getters/Setters
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	
	//Constructor
	private JdbcConnect() 
	{
		
	}

	//Pseudo-Constructor
	public static Connection getInstance() throws DALException
	{
		return instance.connect();
	}
	
	
	//Methods
	public Connection connect () throws DALException
	{
		try
		   {			   
			Class.forName(this.driverName);  
		   }
		catch (ClassNotFoundException e)
		{
			throw new DALException(e.getMessage()+"SQL Server JDBC Driver not found", e);
		}
		
		try
		{
			con= DriverManager.getConnection(connectionUrl);
		}
		catch (SQLException e)
		{
			throw new DALException(e.getMessage()+"Connection has failed", e);
        }
		return con;
	}
	
	public void close () throws DALException
	{
		try
		{
			con.close();
		}
		catch (SQLException e)
		{
			throw new DALException(e.getMessage(), e);
		}
	}
	
			
}
