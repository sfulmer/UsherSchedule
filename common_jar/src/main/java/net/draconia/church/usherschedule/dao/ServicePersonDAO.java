package net.draconia.church.usherschedule.dao;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.church.usherschedule.domain.Service;
import net.draconia.church.usherschedule.domain.ServicePerson;

public interface ServicePersonDAO extends Serializable
{
	public ServicePerson getById(final int iId) throws SQLException;
	public List<ServicePerson> listPersonsInService(final Service objService) throws SQLException;
	public void remove(final ServicePerson objServicePerson) throws SQLException;
	public ServicePerson save(final ServicePerson objServicePerson) throws SQLException;
}