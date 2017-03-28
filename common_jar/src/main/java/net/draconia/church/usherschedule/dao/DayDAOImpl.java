package net.draconia.church.usherschedule.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import net.draconia.church.usherschedule.domain.Day;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.domain.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("dayDAO")
public class DayDAOImpl extends AbstractDAO<Day> implements DayDAO
{
	private static final long serialVersionUID = 493871618220323316L;
	
	@Autowired
	private DataSource mObjDataSource;
	
	@Autowired
	private ScheduleDAO mObjScheduleDAO;
	
	@Autowired
	private ServiceDAO mObjServiceDAO;
	
	protected Day createDayFromResults(final ResultSet objResults) throws SQLException
	{
		Day objDay = new Day();
		
		objDay.setId(objResults.getInt("Id"));
		objDay.setDate(new Date(objResults.getDate("Date").getTime()));
		objDay.setSchedule(getScheduleDAO().getById(objResults.getInt("Schedule")));
		
		for(Service objService : getServiceDAO().listServicesInDay(objDay))
			objDay.addService(objService);
		
		return(objDay);
	}
	
	protected List<Day> createListFromResults(final ResultSet objResults) throws SQLException
	{
		List<Day> lstDays = new ArrayList<Day>();
		
		while(objResults.next())
			lstDays.add(createDayFromResults(objResults));
		
		return(lstDays);
	}
	
	protected void createTable() throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("create table Day (Id int not null primary key auto_increment, Date date not null, Schedule int not null references Schedule(Id));");
			
			objStatement.executeUpdate();
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
	
	public Day getById(final int iId) throws SQLException
	{
		Connection objConnection = null;
		Day objDay = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("select Id, Date, Schedule from Day where Id = ?");
			
			objStatement.setInt(1, iId);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				objDay = createDayFromResults(objResults);
			
			return(objDay);
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
	
	protected DataSource getDataSource()
	{
		return(mObjDataSource);
	}
	
	protected ScheduleDAO getScheduleDAO()
	{
		return(mObjScheduleDAO);
	}
	
	protected ServiceDAO getServiceDAO()
	{
		return(mObjServiceDAO);
	}
	
	public List<Day> listDaysInSchedule(final Schedule objSchedule) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("select Id, Date, Schedule from Day where Schedule = ?");
			objStatement.setInt(1, objSchedule.getId());
			
			objResults = objStatement.executeQuery();
			
			return(createListFromResults(objResults));
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
	
	public void remove(final Day objDay) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("delete from Day where Id = ?");
			
			objStatement.setInt(1, objDay.getId());
			
			objStatement.executeUpdate();
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
	
	public Day save(final Day objDay) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			
			if((objDay.getId() == 0) || (objDay.getId() <= 0))
				{
				ResultSet objResults = null;
				objStatement = objConnection.prepareStatement("insert into Day (Date, Schedule) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				objStatement.setDate(1, new java.sql.Date(objDay.getDate().getTime()));
				objStatement.setInt(2, objDay.getSchedule().getId());
				
				if(objStatement.executeUpdate() == 1)
					{
					objResults = objStatement.getGeneratedKeys();
					
					if(objResults.next())
						{
						objDay.setId(objResults.getInt(1));
						
						return(objDay);
						}
					}
				}
			else
				{
				objStatement = objConnection.prepareStatement("update Day set Date = ?, Schedule = ? where Id = ?");
				
				objStatement.setDate(1,new java.sql.Date(objDay.getDate().getTime()));
				objStatement.setInt(2, objDay.getSchedule().getId());
				objStatement.setInt(3, objDay.getId());
				
				if(objStatement.executeUpdate() == 1)
					return(objDay);
				}
			
			return(null);
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
	
	protected boolean tableExists() throws SQLException
	{
		Connection objConnection = null;
		DatabaseMetaData objMetadata = null;
		ResultSet objResults = null;
		
		try
			{
			objConnection = getDataSource().getConnection();
			objMetadata = objConnection.getMetaData();
			
			objResults = objMetadata.getTables(null, null, "Day", new String[] {"TABLE"});
			
			while(objResults.next())
				if(objResults.getString("TABLE_CAT").equalsIgnoreCase("SQL9158326") || objResults.getString("TABLE_SCHEMA").equalsIgnoreCase("SQL9158326"))
					return(objResults.getString("TABLE_NAME").equalsIgnoreCase("Day"));
			
			return(false);
			}
		finally
			{
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
}