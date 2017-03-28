package net.draconia.church.usherschedule.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.draconia.church.usherschedule.dao.ScheduleDAO;

import net.draconia.church.usherschedule.domain.Schedule;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService
{
	private static final long serialVersionUID = 7109058519103650656L;
	
	@Autowired
	private ScheduleDAO mObjDAO;
	
	public ScheduleServiceImpl()
	{ }
	
	protected ScheduleDAO getDAO()
	{
		return(mObjDAO);
	}
	
	public Schedule getById(final int iId) throws SQLException
	{
		return(getDAO().getById(iId));
	}

	public List<Schedule> list() throws SQLException
	{
		return(getDAO().list());
	}
	
	public void remove(final Schedule objSchedule) throws SQLException
	{
		getDAO().remove(objSchedule);
	}
	
	public Schedule save(final Schedule objSchedule) throws SQLException
	{
		return(getDAO().save(objSchedule));
	}
}