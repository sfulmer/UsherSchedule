package net.draconia.church.usherschedule.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import net.draconia.church.usherschedule.dao.PersonDAO;

import net.draconia.church.usherschedule.domain.Person;
import net.draconia.church.usherschedule.domain.Service;
import net.draconia.church.usherschedule.domain.ServicePerson;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("ServicePersonDAO")
public class ServicePersonDAOImpl extends AbstractDAO<ServicePerson> implements ServicePersonDAO
{
	private static final long serialVersionUID = -88996529693322168L;
	
	@Autowired
	private PersonDAO mObjPersonDAO;
	
	@Autowired
	private ServiceDAO mObjServiceDAO;
	
	protected List<ServicePerson> createListFromResults(final ResultSet objResults) throws SQLException
	{
		List<ServicePerson> lstServicePersons = new ArrayList<ServicePerson>();
		
		while(objResults.next())
			lstServicePersons.add(createServicePersonFromResults(objResults));
			
		return(lstServicePersons);
	}
	
	protected ServicePerson createServicePersonFromResults(final ResultSet objResults) throws SQLException
	{
		ServicePerson objServicePerson = new ServicePerson();
		
		objServicePerson.setId(objResults.getInt("Id"));
		objServicePerson.setService(getServiceDAO().getById(objResults.getInt("Service")));
		objServicePerson.setPerson(getPersonDAO().getById(objResults.getInt("Person")));
		objServicePerson.setNotes(objResults.getString("Notes"));
		objServicePerson.setUsherPosition(objResults.getInt("UsherPosition"));
		objServicePerson.setCommunionPosition(objResults.getInt("CommunionPosition"));
		
		return(objServicePerson);
	}
	
	protected void createTable() throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("create table if not exists Person (Id int not null primary key auto_increment, FirstName varchar(200) not null, LastName varchar(200) not null, StartDate datetime, EndDate datetime, OrdenationDate datetime, RequestedService int references Service(Id));");
			
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
	
	public ServicePerson getById(final int iId) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			
			objStatement = getConnection().prepareStatement("select Id, Service, Person, Notes, UsherPosition, CommunionPosition from ServicePerson where Id = ?");
			
			objStatement.setInt(1, iId);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createServicePersonFromResults(objResults));
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			
			closeConnection();
			}
		
		return(null);
	}
	
	public ServicePerson getByServiceAndPerson(final Service objService, final Person objPerson) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			
			objStatement = getConnection().prepareStatement("select Id, Service, Person, Notes, UsherPosition, CommunionPosition from ServicePosition where Service = ? and Person = ?");
		
			objStatement.setInt(1, objService.getId());
			objStatement.setInt(2, objPerson.getId());
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createServicePersonFromResults(objResults));
			
			return(null);
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			
			closeConnection();
			}
	}
	
	protected PersonDAO getPersonDAO()
	{
		return(mObjPersonDAO);
	}
	
	protected ServiceDAO getServiceDAO()
	{
		return(mObjServiceDAO);
	}
	
	public List<ServicePerson> listPersonsInService(Service objService) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			objStatement = getConnection().prepareStatement("select Id, Service, Person, Notes, UsherPosition, CommunionPosition from ServicePerson where Id = ?");
			
			objStatement.setInt(1, objService.getId());
			
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
	
	public List<ServicePerson> listServicePersonsByService(final Service objService) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			
			objStatement = getConnection().prepareStatement("select Id, Service, Person, Notes, UsherPosition, CommunionPosition from ServicePerson where Service = ?");
			
			objStatement.setInt(1, objService.getId());
			
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
	
	public List<ServicePerson> listServicePersons() throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			
			objStatement = getConnection().prepareStatement("select Id, Service, Person, Notes, UsherPosition, CommunionPosition from ServicePosition");
			
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
	
	public void remove(final ServicePerson objServicePerson) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objStatement = getConnection().prepareStatement("delete from ServicePerson where Id = ?");
			
			objStatement.setInt(1, objServicePerson.getId());
			
			objStatement.executeUpdate();
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
			
			closeConnection();
			}
	}

	public ServicePerson save(final ServicePerson objServicePerson) throws SQLException
	{
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			if((objServicePerson.getId() == null) || (objServicePerson.getId() <= 0))
				{
				objStatement = getConnection().prepareStatement("insert into ServicePerson (Service, Person, Notes, UsherPosition, CommunionPosition) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				objStatement.setInt(1, objServicePerson.getService().getId());
				objStatement.setInt(2, objServicePerson.getPerson().getId());
				objStatement.setString(3, objServicePerson.getNotes());
				objStatement.setInt(4, objServicePerson.getUsherPosition());
				objStatement.setInt(5, objServicePerson.getCommunionPosition());
				
				if(objStatement.executeUpdate() == 1)
					{
					ResultSet objResults = objStatement.getGeneratedKeys();
					
					if(objResults.next())
						{
						objServicePerson.setId(objResults.getInt(1));
						
						return(objServicePerson);
						}
					}
				}
			else
				{
				objStatement = getConnection().prepareStatement("update ServicePerson set Service = ?, Person = ?, Notes = ?, UsherPosition = ?, CommunionPosition = ? where Id = ?");
				
				objStatement.setInt(1, objServicePerson.getService().getId());
				objStatement.setInt(2, objServicePerson.getPerson().getId());
				objStatement.setString(3, objServicePerson.getNotes());
				objStatement.setInt(4, objServicePerson.getUsherPosition());
				objStatement.setInt(5, objServicePerson.getCommunionPosition());
				objStatement.setInt(6, objServicePerson.getId());
				
				if(objStatement.executeUpdate() == 1)
					return(objServicePerson);
				}
			
			return(null);
			}
		finally
			{
			if(!objStatement.isClosed())
				objStatement.close();
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
			
			objResults = objMetadata.getTables(null, null, "ServicePerson", new String[] {"TABLE"});
			
			while(objResults.next())
				if(objResults.getString("TABLE_CAT").equalsIgnoreCase("SQL9158326") || objResults.getString("TABLE_SCHEMA").equalsIgnoreCase("SQL9158326"))
					return(objResults.getString("TABLE_NAME").equalsIgnoreCase("ServicePerson"));
			
			return(false);
			}
		finally
			{
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
}