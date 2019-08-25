package fr.eni.clinique.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.dal.jdbc.JdbcConnect;

public class AgendaDaoJdbcImpl implements DAO<Agenda>
	{
		private static final String sqlInsert="INSERT INTO Agendas (CodeVeto, DateRdv, CodeAnimal) "
				+ "VALUES (?,CAST(? AS smalldatetime),?)";
		private static final String sqlSelectByID="SELECT * FROM Agendas WHERE CodeVeto=?";
		private static final String sqlSelectAll="SELECT * FROM Agendas";
		private static final String sqlUpdate="UPDATE Agendas SET CodeVeto=?, DateRdv=(CAST(? AS smalldatetime)), CodeAnimal=?";
		private static final String sqlDelete="DELETE FROM Agendas WHERE codeVeto=?";
		
		private static AgendaDaoJdbcImpl instance= new AgendaDaoJdbcImpl();

		
		private AgendaDaoJdbcImpl()
			{
				
			}
			
			public static AgendaDaoJdbcImpl getInstance()
			{
				return instance;
			}
		@Override
		public void insert(Agenda data) throws DALException
		{
			Connection cnx = null;
			PreparedStatement rqt = null;
			try {
				cnx = JdbcConnect.getInstance();
				rqt = cnx.prepareStatement(sqlInsert);
				rqt.setInt(1, data.getCodeVeto());
				String dateRdv= data.getDateRdv().format(DateTimeFormatter.ISO_LOCAL_DATE);
				String timeRdv=data.getHeureRdv().format(DateTimeFormatter.ISO_LOCAL_TIME);
				String dateTimeRdv= dateRdv+"T"+ timeRdv;
				rqt.setString(2,dateTimeRdv);
				rqt.setInt(3, data.getCodeAnimal());

				rqt.executeUpdate();
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
		public Agenda selectById(int id) throws DALException
		{
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			Agenda rdv = null;
			try
			{
				cnx = JdbcConnect.getInstance();
				rqt = cnx.prepareStatement(sqlSelectByID);
				rqt.setInt(1, id);

				rs = rqt.executeQuery();
				if (rs.next())
					{
					rdv = new Agenda(rs.getInt("CodeVeto"),
							rs.getDate("DateRdv").toLocalDate(),
							rs.getTime("DateRdv").toLocalTime(),
							rs.getInt("CodeAnimal"));
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
			return rdv;
		}
			
		@Override
		public List<Agenda> selectAll() throws DALException
		{
			Connection cnx = null;
			Statement rqt = null;
			ResultSet rs = null;
			List<Agenda> liste = new ArrayList<Agenda>();
			try
			{
				cnx = JdbcConnect.getInstance();
				rqt = cnx.createStatement();
				rs = rqt.executeQuery(sqlSelectAll);
				Agenda rdv = null;

				while (rs.next())
				{
					rdv = new Agenda(rs.getInt("CodeVeto"),
							rs.getDate("DateRdv").toLocalDate(),
							rs.getTime("DateRdv").toLocalTime(),
							rs.getInt("CodeAnimal"));
					
					liste.add(rdv);
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
		public void update(Agenda data) throws DALException
		{
			Connection cnx = null;
			PreparedStatement rqt = null;
			try {
				cnx = JdbcConnect.getInstance();
				rqt = cnx.prepareStatement(sqlUpdate);
				rqt.setInt(1, data.getCodeVeto());
				rqt.setString(2,LocalDateTime.parse(data.getDateRdv()+""+data.getHeureRdv(), DateTimeFormatter.BASIC_ISO_DATE).toString());				
				rqt.setInt(3, data.getCodeAnimal());

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
