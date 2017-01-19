package net.draconia.church.usherschedule.windows.schedule.ui.model;

import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.ui.listdetails.model.DetailsPanelModel;

@Component
public class ScheduleDetailsPanelModel extends DetailsPanelModel<Schedule>
{
	private static final long serialVersionUID = -4244475709565035713L;
	
	protected void addWorkingModelObservers()
	{
		super.addWorkingModelObservers();
		
		//TODO: Add Additional Observers here!
	}
	
	protected Schedule createNewModel()
	{
		return(new Schedule());
	}
}