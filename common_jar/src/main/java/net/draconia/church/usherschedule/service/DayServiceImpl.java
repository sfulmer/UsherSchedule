package net.draconia.church.usherschedule.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.draconia.church.usherschedule.dao.DayDAO;
import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

@Service("dayService")
public class DayServiceImpl implements DayService
{
	private static final long serialVersionUID = 5238326547612706014L;
	
	@Autowired
	private DayDAO mObjDAO;
	
	public DayServiceImpl()
	{ }
	
	protected DayDAO getDAO()
	{
		return(mObjDAO);
	}
	
	public Day getById(final int iId) throws SQLException
	{
		return(getDAO().getById(iId));
	}

	public List<Day> listDaysInSchedule(final Schedule objSchedule) throws SQLException
	{
		return(getDAO().listDaysInSchedule(objSchedule));
	}
	
	public void remove(final Day objDay) throws SQLException
	{
		getDAO().remove(objDay);
	}
	
	public Day save(final Day objDay) throws SQLException
	{
		return(getDAO().save(objDay));
	}
}