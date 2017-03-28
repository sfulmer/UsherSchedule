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

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("serviceDAO")
public class ServiceDAOImpl extends AbstractDAO<Service> implements ServiceDAO
{
	private static final long serialVersionUID = 6408268280033682130L;
	
	@Autowired
	private DataSource mObjDataSource;
	
	@Autowired
	private DayDAO mObjDayDAO;
	
	@Autowired
	private ServicePersonDAO mObjServicePersonDAO;
	
	protected List<Service> createListFromResults(final ResultSet objResults) throws SQLException
	{
		List<Service> lstServices = new ArrayList<Service>();
		
		while(objResults.next())
			lstServices.add(createServiceFromResults(objResults));
			
		return(lstServices);
	}
	
	
	protected Service createServiceFromResults(final ResultSet objResults) throws SQLException
	{
		Service objService = new Service();
		
		objService.setId(objResults.getInt("Id"));
		objService.setLabel(objResults.getString("Label"));
		objService.setDay(getDayDAO().getById(objResults.getInt("Day")));
		
		/*for(ServicePerson objServicePerson : getServicePersonDAO().listPersonsInService(objService))
			objService.addPerson(objServicePerson);*/
		
		return(objService);
	}
	
	protected void createTable() throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("create table if not exists Service (Id int not null primary key auto_increment, Label varchar(150) not null, Day int not null references Day(Id));");
			
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
	
	public Service getById(final int iId) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			objStatement = getConnection().prepareStatement("select Id, Label, Day from Service where Id = ?");
			
			objStatement.setInt(1, iId);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createServiceFromResults(objResults));
			
			return(null);
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			
			closeConnection();
			}
	}
	
	protected DataSource getDataSource()
	{
		return(mObjDataSource);
	}
	
	protected DayDAO getDayDAO()
	{
		return(mObjDayDAO);
	}
	
	protected ServicePersonDAO getServicePersonDAO()
	{
		return(mObjServicePersonDAO);
	}
	
	public List<Service> listServicesInDay(final Day objDay) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			objStatement = getConnection().prepareStatement("select Id, Label, Day from Service where Day = ?");
			
			objStatement.setInt(1, objDay.getId());
			
			objResults = objStatement.executeQuery();
			
			return(createListFromResults(objResults));
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			
			closeConnection();
			}
	}
	
	public void remove(final Service objService) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objStatement = getConnection().prepareStatement("delete from Service where Id = ?");
			
			objStatement.setInt(1, objService.getId());
			
			objStatement.executeUpdate();
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			
			closeConnection();
			}
	}
	
	public Service save(final Service objService) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			if((objService.getId() == null) || ((objService.getId() != null) && (objService.getId() <= 0)))
				{
				objStatement = getConnection().prepareStatement("insert into Service (Label, Day) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				objStatement.setString(1, objService.getLabel());
				objStatement.setInt(2, objService.getDay().getId());
				
				if(objStatement.executeUpdate() == 1)
					{
					ResultSet objResults = objStatement.getGeneratedKeys();
					
					if(objResults.next())
						{
						objService.setId(objResults.getInt(1));
						
						return(objService);
						}
					}
				}
			else
				{
				objStatement = getConnection().prepareStatement("update Service set Label = ?, Day = ? where Id = ?");
				
				objStatement.setString(1, objService.getLabel());
				objStatement.setInt(2, objService.getDay().getId());
				objStatement.setInt(3, objService.getId());
				
				if(objStatement.executeUpdate() == 1)
					return(objService);
				}
			
			return(null);
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			
			closeConnection();
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
			
			objResults = objMetadata.getTables(null, null, "Service", new String[] {"TABLE"});
			
			while(objResults.next())
				if(objResults.getString("TABLE_CAT").equalsIgnoreCase("SQL9158326") || objResults.getString("TABLE_SCHEMA").equalsIgnoreCase("SQL9158326"))
					return(objResults.getString("TABLE_NAME").equalsIgnoreCase("Service"));
			
			return(false);
			}
		finally
			{
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
}