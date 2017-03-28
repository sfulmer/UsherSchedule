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

import net.draconia.church.usherschedule.domain.Person;
import net.draconia.church.usherschedule.domain.Service;
import net.draconia.church.usherschedule.domain.ServicePerson;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("personDAO")
public class PersonDAOImpl extends AbstractDAO<Person> implements PersonDAO
{
	private static final long serialVersionUID = -4900975508888914221L;
	
	@Autowired
	private DataSource mObjDataSource;
	
	@Autowired
	private ServiceDAO mObjServiceDAO;
	
	@Autowired
	private ServicePersonDAO mObjServicePersonDAO;
	
	protected List<Person> createListFromResults(final ResultSet objResults) throws SQLException
	{
		List<Person> lstPersons = new ArrayList<Person>();
		
		while(objResults.next())
			lstPersons.add(createPersonFromResults(objResults));
			
		return(lstPersons);
	}
	
	protected Person createPersonFromResults(final ResultSet objResults) throws SQLException
	{
		Person objPerson = new Person();
		
		objPerson.setId(objResults.getInt("Id"));
		objPerson.setFirstName(objResults.getString("FirstName"));
		objPerson.setLastName(objResults.getString("LastName"));
		objPerson.setStartDate(new Date(objResults.getDate("StartDate").getTime()));
		objPerson.setEndDate(new Date(objResults.getDate("EndDate").getTime()));
		objPerson.setOrdenationDate(new Date(objResults.getDate("OrdenationDate").getTime()));
		objPerson.setRequestedService(getServiceDAO().getById(objResults.getInt("RequestedService")));
		
		return(objPerson);
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
	
	public Person getById(final int iId) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			ResultSet objResults = null;
			
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("select Id, FirstName, LastName, StartDate, EndDate, OrdenationDate, RequestedService from Person where Id = ?");
			
			objStatement.setInt(1, iId);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createPersonFromResults(objResults));
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
	
	protected ServiceDAO getServiceDAO()
	{
		return(mObjServiceDAO);
	}
	
	protected ServicePersonDAO getServicePersonDAO()
	{
		return(mObjServicePersonDAO);
	}

	public List<Person> listPersonsInService(final Service objService) throws SQLException
	{
		List<Person> lstPersons = new ArrayList<Person>();
		
		if(!tableExists())
			createTable();
		
		for(ServicePerson objServicePerson : getServicePersonDAO().listPersonsInService(objService))
			lstPersons.add(objServicePerson.getPerson());
		
		return(lstPersons);
	}
	
	public void remove(final Person objPerson) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			objStatement = objConnection.prepareStatement("delete from Person where Id = ?");
			
			objStatement.setInt(1, objPerson.getId());
			
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
	
	public Person save(final Person objPerson) throws SQLException
	{
		Connection objConnection = null;
		PreparedStatement objStatement = null;
		
		if(!tableExists())
			createTable();
		
		try
			{
			objConnection = getDataSource().getConnection();
			
			if((objPerson.getId() == null) || (objPerson.getId() <= 0))
				{
				objStatement = objConnection.prepareStatement("insert into Person (FirstName, LastName, StartDate, EndDate, OrdenationDate, RequestedService) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				objStatement.setString(1, objPerson.getFirstName());
				objStatement.setString(2, objPerson.getLastName());
				objStatement.setDate(3, new java.sql.Date(objPerson.getStartDate().getTime()));
				objStatement.setDate(4, new java.sql.Date(objPerson.getEndDate().getTime()));
				objStatement.setDate(5, new java.sql.Date(objPerson.getOrdenationDate().getTime()));
				objStatement.setInt(6, objPerson.getRequestedService().getId());
				
				if(objStatement.executeUpdate() == 1)
					{
					ResultSet objGeneratedKeys = objStatement.getGeneratedKeys();
					
					if(objGeneratedKeys.next())
						objPerson.setId(objGeneratedKeys.getInt(1));
					
					return(objPerson);
					}
				}
			else
				{
				objStatement = objConnection.prepareStatement("update Person set FirstName = ?, LastName = ?, StartDate = ?, EndDate = ?, OrdenationDate = ?, RequestedService = ? where Id = ?");
				
				objStatement.setString(1, objPerson.getFirstName());
				objStatement.setString(2, objPerson.getLastName());
				objStatement.setDate(3, new java.sql.Date(objPerson.getStartDate().getTime()));
				objStatement.setDate(4, new java.sql.Date(objPerson.getEndDate().getTime()));
				objStatement.setDate(5, new java.sql.Date(objPerson.getOrdenationDate().getTime()));
				objStatement.setInt(6, objPerson.getRequestedService().getId());
				objStatement.setInt(7, objPerson.getId());
				
				if(objStatement.executeUpdate() == 1)
					return(objPerson);
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
			
			objResults = objMetadata.getTables(null, null, "Person", new String[] {"TABLE"});
			
			while(objResults.next())
				if(objResults.getString("TABLE_CAT").equalsIgnoreCase("SQL9158326") || objResults.getString("TABLE_SCHEMA").equalsIgnoreCase("SQL9158326"))
					return(objResults.getString("TABLE_NAME").equalsIgnoreCase("Person"));
			
			return(false);
			}
		finally
			{
			if(!objConnection.isClosed())
				objConnection.close();
			}
	}
}