package net.draconia.church.usherschedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.draconia.church.usherschedule.dao.DayDAO;
import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

@Service
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
	
	public Day getById(final int iId)
	{
		return(getDAO().getById(iId));
	}

	public List<Day> listDaysInSchedule(final Schedule objSchedule)
	{
		return(getDAO().listDaysInSchedule(objSchedule));
	}
	
	public void remove(final Day objDay)
	{
		getDAO().remove(objDay);
	}
	
	public Day save(final Day objDay)
	{
		return(getDAO().save(objDay));
	}
}