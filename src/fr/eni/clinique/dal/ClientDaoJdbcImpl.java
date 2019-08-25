package fr.eni.clinique.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.jdbc.JdbcConnect;


public class ClientDaoJdbcImpl implements DAO<Client>
{

	private static final String sqlInsert="INSERT INTO Clients (NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) "
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String sqlSelectByID="SELECT * FROM Clients WHERE CodeCLient=?";
	private static final String sqlSelectAll="SELECT * FROM Clients";
	private static final String sqlUpdate="UPDATE Clients SET NomClient=?, PrenomClient=?, Adresse1=?, Adresse2=?, CodePostal=?, Ville=?, NumTel=?, Assurance=?, Email=?, Remarque=?, Archive=? WHERE CodeClient=?";
	private static final String sqlDelete="DELETE FROM Clients WHERE codeClient=?";
	
	private static ClientDaoJdbcImpl instance= new ClientDaoJdbcImpl();
	
	
	private ClientDaoJdbcImpl()
		{
			
		}
		
		public static ClientDaoJdbcImpl getInstance()
		{
			return instance;
		}
	
	@Override
	public void insert(Client data) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNomClient());
			rqt.setString(2, data.getPrenomClient());
			rqt.setString(3, data.getAdresse1());
			rqt.setString(4, data.getAdresse2());
			rqt.setString(5, data.getCodePostal());
			rqt.setString(6, data.getVille());
			rqt.setString(7, data.getNumTel());
			rqt.setString(8, data.getAssurance());
			rqt.setString(9, data.getEmail());
			rqt.setString(10, data.getRemarque());
			rqt.setBoolean(11, data.isArchive());

			int nbRows = rqt.executeUpdate();
			if(nbRows == 1)
				{
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next())
					{
						data.setCodeClient(rs.getInt(1));
					}

				}
		}
		catch(SQLException e)
		{
			throw new DALException("Insert request failed - " + data, e);
		}
		finally
		{
			try
			{
				if (rqt != null)
				{
					rqt.close();
				}
				if(cnx!=null)
				{
					cnx.close();
				}
			}
			catch (SQLException e)
			{
				throw new DALException("close failed - ", e);
			}
		}		
	}

	@Override
	public Client selectById(int id) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Client client = null;
		try
		{
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlSelectByID);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next())
				{
				client = new Client(rs.getInt("CodeClient"),
						rs.getString("NomClient"),
						rs.getString("PrenomClient"),
						rs.getString("Adresse1").trim(),
						rs.getString("Adresse2"),
						rs.getString("CodePostal"),
						rs.getString("Ville"),
						rs.getString("NumTel"),
						rs.getString("Assurance"),
						rs.getString("Email"),
						rs.getString("Remarque"),
						rs.getBoolean("Archive"));
				}
				
		} catch (SQLException e)
		{
			throw new DALException("selectById request failed - id = " + id , e);
		}
		finally
		{
			try {
				if (rs != null)
					{
					rs.close();
					}
				if (rqt != null)
					{
					rqt.close();
					}
				if(cnx!=null)
					{
					cnx.close();
					}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return client;
	}

	@Override
	public List<Client> selectAll() throws DALException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Client> liste = new ArrayList<Client>();
		try
		{
			cnx = JdbcConnect.getInstance();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Client client = null;

			while (rs.next())
			{
				client = new Client(rs.getString("NomClient"),
							rs.getString("PrenomClient"),
							rs.getString("Adresse1").trim(),
							rs.getString("Adresse2"),
							rs.getString("CodePostal"),
							rs.getString("Ville"),
							rs.getString("NumTel"),
							rs.getString("Assurance"),
							rs.getString("Email"),
							rs.getString("Remarque"),
							rs.getBoolean("Archive"));
				
				liste.add(client);
			}
		}
		catch (SQLException e)
		{
			throw new DALException("selectAll request failed - " , e);
		}
		finally
		{
			try
			{
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return liste;
	}

	@Override
	public void update(Client data) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getPrenomClient());
			rqt.setString(2, data.getNomClient());
			rqt.setString(3, data.getAdresse1());
			rqt.setString(4, data.getAdresse2());
			rqt.setString(5, data.getCodePostal());
			rqt.setString(6, data.getVille());
			rqt.setString(7, data.getNumTel());
			rqt.setString(8, data.getAssurance());
			rqt.setString(9, data.getEmail());
			rqt.setString(10, data.getRemarque());
			rqt.setBoolean(11, data.isArchive());
			rqt.setInt(12, data.getCodeClient());

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update request failed - " + data, e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx !=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void delete(int id) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try
		{		
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, id);
			rqt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DALException("Delete request failed - id=" + id, e);
		}
		finally
		{
			try
			{
				if (rqt != null)
				{
					rqt.close();
				}
				if(cnx!=null)
				{
					cnx.close();
				}
			}
			catch (SQLException e)
			{
				throw new DALException("close failed " , e);
			}
		}
	}

}
