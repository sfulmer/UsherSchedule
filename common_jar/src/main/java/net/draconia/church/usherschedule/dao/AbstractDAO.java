package net.draconia.church.usherschedule.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Observable;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractDAO<ModelType extends Observable>
{
	private Connection mObjConnection;
	
	@Autowired
	private DataSource mObjDataSource;
	
	protected void closeConnection() throws SQLException
	{
		if((mObjConnection != null) && (!mObjConnection.isClosed()))
			mObjConnection.close();
	}
	
	protected abstract void createTable() throws SQLException;
	
	protected Connection getConnection() throws SQLException
	{
		if((mObjConnection == null) || ((mObjConnection != null) && (mObjConnection.isClosed())))
			mObjConnection = getDataSource().getConnection();
		
		return(mObjConnection);
	}
	
	protected DataSource getDataSource()
	{
		return(mObjDataSource);
	}
	
	protected abstract boolean tableExists() throws SQLException;
}