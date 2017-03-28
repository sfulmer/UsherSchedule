package net.draconia.church.usherschedule.windows.schedule.ui.model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.service.ScheduleService;

import net.draconia.ui.listdetails.model.DialogModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ScheduleDialogModel extends DialogModel<Schedule>
{
	private static final long serialVersionUID = -3506613773355957424L;
	
	@Autowired
	private ScheduleService mObjScheduleService;
	
	public ScheduleDialogModel()
	{ }
	
 	public boolean addSchedule(final Schedule objSchedule) throws SQLException
	{
		return(addModelType(objSchedule));
	}
	
	public boolean addSchedules(final Collection<Schedule> collSchedules) throws SQLException
	{
		return(addModelTypes(collSchedules));
	}
	
	public Schedule create() throws SQLException
	{
		Schedule objSchedule = new Schedule();
		
		addSchedule(objSchedule);
		
		return(objSchedule);
	}
	
	public List<Schedule> getSchedules() throws SQLException
	{
		return(getModelList());
	}
	
	protected ScheduleService getScheduleService()
	{
		return(mObjScheduleService);
	}
	
	protected List<Schedule> listDAO() throws SQLException
	{
		return(getScheduleService().list());
	}
	
	protected void removeDAO(final Schedule objSchedule) throws SQLException
	{
		getScheduleService().remove(objSchedule);
	}
	
	public boolean removeSchedule(final Schedule objSchedule) throws SQLException
	{
		return(removeModelType(objSchedule));
	}
	
	public boolean removeSchedules(final Collection<Schedule> collSchedules) throws SQLException
	{
		return(removeModelTypes(collSchedules));
	}
	
	protected void saveDAO(final Schedule objSchedule) throws SQLException
	{
		getScheduleService().save(objSchedule);
	}
}