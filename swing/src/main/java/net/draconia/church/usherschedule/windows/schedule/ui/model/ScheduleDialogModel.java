package net.draconia.church.usherschedule.windows.schedule.ui.model;

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
	;
	@Autowired
	private ScheduleService mObjScheduleService;
	
	public ScheduleDialogModel()
	{ }
	
 	public boolean addSchedule(final Schedule objSchedule)
	{
		return(addModelType(objSchedule));
	}
	
	public boolean addSchedules(final Collection<Schedule> collSchedules)
	{
		return(addModelTypes(collSchedules));
	}
	
	public Schedule create()
	{
		Schedule objSchedule = new Schedule();
		
		addSchedule(objSchedule);
		
		return(objSchedule);
	}
	
	public List<Schedule> getSchedules()
	{
		return(getModelList());
	}
	
	protected ScheduleService getScheduleService()
	{
		return(mObjScheduleService);
	}
	
	protected List<Schedule> listDAO()
	{
		return(getScheduleService().list());
	}
	
	protected void removeDAO(final Schedule objSchedule)
	{
		getScheduleService().remove(objSchedule);
	}
	
	public boolean removeSchedule(final Schedule objSchedule)
	{
		return(removeModelType(objSchedule));
	}
	
	public boolean removeSchedules(final Collection<Schedule> collSchedules)
	{
		return(removeModelTypes(collSchedules));
	}
	
	protected void saveDAO(final Schedule objSchedule)
	{
		getScheduleService().save(objSchedule);
	}
}