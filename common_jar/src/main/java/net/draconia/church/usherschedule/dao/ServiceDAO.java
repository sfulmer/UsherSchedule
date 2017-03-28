package net.draconia.church.usherschedule.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

public interface ServiceDAO extends Serializable
{
	public Service getById(final int iId) throws SQLException;
	public List<Service> listServicesInDay(final Day objDay) throws SQLException;
	public void remove(final Service objService) throws SQLException;
	public Service save(final Service objService) throws SQLException;
}