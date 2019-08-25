package fr.eni.clinique.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Animal.Sexe;
import fr.eni.clinique.dal.jdbc.JdbcConnect;

public class AnimalDaoJdbcImpl implements DAO<Animal>
{
	private static final String sqlInsert="INSERT INTO Animaux (Nom, MotPasse, Role, Archive) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?";
	private static final String sqlSelectByID="SELECT * FROM Animaux WHERE CodeAnimal=?";
	private static final String sqlSelectAll="SELECT * FROM Animaux";
	private static final String sqlUpdate="UPDATE Animaux SET Nom=?, MotPasse=?, Role=?, Archive=?";
	private static final String sqlDelete="DELETE FROM Animaux WHERE codeAnimal=?";

	private static AnimalDaoJdbcImpl instance= new AnimalDaoJdbcImpl();
	
	
	private AnimalDaoJdbcImpl()
		{
			
		}
		
		public static AnimalDaoJdbcImpl getInstance()
		{
			return instance;
		}
	
	@Override
	public void insert(Animal data) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNomAnimal());
			rqt.setString(2, String.valueOf(data.getSexe()));
			rqt.setString(3, data.getCouleur());
			rqt.setString(4, data.getRace());
			rqt.setString(5, data.getEspece());
			rqt.setInt(6, data.getCodeClient());
			rqt.setString(7, data.getTatouage());
			rqt.setString(8, data.getAntecedents());
			rqt.setBoolean(9, data.isArchive());

			int nbRows = rqt.executeUpdate();
			if(nbRows == 1)
				{
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next())
					{
						data.setCodeAnimal(rs.getInt(1));
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
	public Animal selectById(int id) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Animal anim=null;
		try
		{
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlSelectByID);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next())
				{
				anim = new Animal(rs.getInt("CodeAnimal"),
						rs.getString("NomAnimal"),
						Sexe.valueOf(rs.getString("Sexe")),
						rs.getString("Couleur"),
						rs.getString("Race"),
						rs.getString("Espece"),
						rs.getInt("CodeCLient"),
						rs.getString("Tatouage"),
						rs.getString("Antecedents"),
						rs.getBoolean("Archive"));
			
				
				}	
		}
		catch (SQLException e)
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
		return anim;
	}
		
	@Override
	public List<Animal> selectAll() throws DALException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Animal> liste = new ArrayList<Animal>();
		try
		{
			cnx = JdbcConnect.getInstance();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Animal anim = null;

			while (rs.next())
			{
				anim = new Animal(rs.getInt("CodeAnimal"),
						rs.getString("NomAnimal"),
						Sexe.valueOf(rs.getString("Sexe")),
						rs.getString("Couleur"),
						rs.getString("Race"),
						rs.getString("Espece"),
						rs.getInt("CodeCLient"),
						rs.getString("Tatouage"),
						rs.getString("Antecedents"),
						rs.getBoolean("Archive"));
				
				liste.add(anim);
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
	public void update(Animal data) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcConnect.getInstance();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getNomAnimal());
			rqt.setString(2, String.valueOf(data.getSexe()));
			rqt.setString(3, data.getCouleur());
			rqt.setString(4, data.getRace());
			rqt.setString(5, data.getEspece());
			rqt.setInt(6, data.getCodeClient());
			rqt.setString(7, data.getTatouage());
			rqt.setString(8, data.getAntecedents());
			rqt.setBoolean(9, data.isArchive());

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
