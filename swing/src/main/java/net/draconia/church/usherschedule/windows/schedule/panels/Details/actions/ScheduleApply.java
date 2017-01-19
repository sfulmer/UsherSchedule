package net.draconia.church.usherschedule.windows.schedule.panels.Details.actions;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;
import net.draconia.ui.listdetails.actions.Apply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleApply extends Apply<Schedule>
{
	private static final long serialVersionUID = -4167834176576431092L;
	
	@Autowired
	public ScheduleApply(final ScheduleDetailsPanelModel objModel)
	{
		super(objModel);
	}
	
	protected void setModel(final ScheduleDetailsPanelModel objModel)
	{
		super.setModel(objModel);
	}
}