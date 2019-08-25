package fr.eni.clinique.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.jdbc.JdbcConnect;

public class PersonnelDaoJdbcImpl implements DAO<Personnel>
{
	private static final String sqlInsert="INSERT INTO Personnels (Nom, MotPasse, Role, Archive) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?";
	private static final String sqlSelectByID="SELECT * FROM Personnels WHERE CodePers=?";
	private static final String sqlSelectAll="SELECT * FROM Personnels";
	private static final String sqlUpdate="UPDATE Personnels SET Nom=?, MotPasse=?, Role=?, Archive=?";
	private static final String sqlDelete="DELETE FROM Personnels WHERE codePers=?";
	

	private static PersonnelDaoJdbcImpl instance= new PersonnelDaoJdbcImpl();
	
	
	private PersonnelDaoJdbcImpl()
		{
			
		}
		
		public static PersonnelDaoJdbcImpl getInstance()
		{
			return instance;
		}
	
	@Override
	public void insert(Personnel data) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNom());
			rqt.setString(2, data.getMotPasse());
			rqt.setString(3, data.getRole());
			rqt.setBoolean(4, data.isArchive());

			int nbRows = rqt.executeUpdate();
			if(nbRows == 1)
				{
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next())
					{
						data.setCodePersonne(rs.getInt(1));
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
	public Personnel selectById(int id) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnel pers = null;
		try
		{
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlSelectByID);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next())
				{
				pers = new Personnel(rs.getInt("CodePers"),
						rs.getString("Nom"),
						rs.getString("MotPasse"),
						rs.getString("Role"),
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
		return pers;
	}
		
	@Override
	public List<Personnel> selectAll() throws DALException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Personnel> liste = new ArrayList<Personnel>();
		try
		{
			cnx = JdbcConnect.getInstance();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Personnel personnel = null;

			while (rs.next())
			{
				personnel = new Personnel(rs.getString("Nom"),
							rs.getString("MotPasse"),
							rs.getString("Role"),
							rs.getBoolean("Archive"));
				
				liste.add(personnel);
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
	public void update(Personnel data) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getMotPasse());
			rqt.setString(2, data.getRole());
			rqt.setBoolean(3, data.isArchive());

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
