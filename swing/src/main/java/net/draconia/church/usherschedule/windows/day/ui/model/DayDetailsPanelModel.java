package net.draconia.church.usherschedule.windows.day.ui.model;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.ui.listdetails.model.DetailsPanelModel;

import org.springframework.stereotype.Component;

@Component
public class DayDetailsPanelModel extends DetailsPanelModel<Day>
{
	private static final long serialVersionUID = -5982336069774509579L;
	
	private Schedule mObjSchedule;
	
	protected Day createNewModel()
	{
		return(new Day(getCurrentSchedule()));
	}
	
	public Schedule getCurrentSchedule()
	{
		return(mObjSchedule);
	}
	
	public void setCurrentSchedule(final Schedule objSchedule)
	{
		mObjSchedule = objSchedule;
	}
}