package net.draconia.church.usherschedule.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.draconia.church.usherschedule.domain.Schedule;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("scheduleDAO")
public class ScheduleDAOImpl extends AbstractDAO<Schedule> implements ScheduleDAO
{
	private static final long serialVersionUID = 8022679655981503817L;
	
	@Autowired
	private DataSource mObjDataSource;
	
	@Autowired
	private DayDAO mObjDAYDAO;
	
	public ScheduleDAOImpl()
	{ }
	
	protected List<Schedule> createListFromResults(final ResultSet objResults) throws SQLException
	{
		List<Schedule> lstSchedules = new ArrayList<Schedule>();
		
		while(objResults.next())
			lstSchedules.add(createScheduleFromResults(objResults));
			
		return(lstSchedules);
	}
	
	
	protected Schedule createScheduleFromResults(final ResultSet objResults) throws SQLException
	{
		Schedule objSchedule = new Schedule();
		
		objSchedule.setId(objResults.getInt("Id"));
		objSchedule.setName(objResults.getString("Name"));
		objSchedule.setDays(getDayDAO().listDaysInSchedule(objSchedule));
		
		return(objSchedule);
	}
	
	protected void createTable() throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("create table if not exists Schedule (Id int not null primary key auto_increment, Name varchar(150) not null);");
			
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
	
	public Schedule getById(final int iId) throws SQLException
	{
		Connection objConnection= null;
		PreparedStatement objStatement = null;
		ResultSet objResults;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("select Id, Name from Schedule where Id = ?");
			
			objStatement.setInt(1, iId);
		
			objResults = objStatement.executeQuery();
		
			if(objResults.next())
				return(createScheduleFromResults(objResults));
			else
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
	
	protected DataSource getDataSource()
	{
		return(mObjDataSource);
	}
	
	protected DayDAO getDayDAO()
	{
		return(mObjDAYDAO);
	}
	
	public List<Schedule> list() throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("select Id, Name from Schedule;");
			
			return(createListFromResults(objStatement.executeQuery()));
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
	
	public void remove(final Schedule objSchedule) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("delete from Schedule where Id = ?");
		
			objStatement.setInt(1, objSchedule.getId());
		
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
	
	public Schedule save(final Schedule objSchedule) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			
			if((objSchedule.getId() == null) || (objSchedule.getId() <= 1))
				{
				ResultSet objGeneratedId;
				
				objStatement = objConnection.prepareStatement("insert into Schedule (Name) values (?)", Statement.RETURN_GENERATED_KEYS);
				
				objStatement.setString(1, objSchedule.getName());
				
				if(objStatement.executeUpdate() == 1)
					{
					objGeneratedId = objStatement.getGeneratedKeys();
					
					if(objGeneratedId.next())
						objSchedule.setId(objGeneratedId.getInt(1));
					
					return(objSchedule);
					}
				}
			else
				{
				objStatement = objConnection.prepareStatement("update Schedule set Name = ? where Id = ?");
				
				objStatement.setString(1, objSchedule.getName());
				objStatement.setInt(2, objSchedule.getId());
				
				if(objStatement.executeUpdate() == 1)
					return(objSchedule);
				}
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			if(!objConnection.isClosed())
				objConnection.close();
			}
		
		return(null);
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
			
			objResults = objMetadata.getTables(null, null, "Schedule", new String[] {"TABLE"});
			
			while(objResults.next())
				if("SQL9158326".equalsIgnoreCase(objResults.getString("TABLE_CAT")) || "SQL9158326".equalsIgnoreCase(objResults.getString("TABLE_SCHEM")))
					return("Schedule".equalsIgnoreCase(objResults.getString("TABLE_NAME")));
			
			return(false);
			}
		finally
			{
			closeConnection();
			}
	}
}