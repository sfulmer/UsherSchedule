package net.draconia.church.usherschedule.windows.schedule.panels.Details.actions;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.ui.listdetails.actions.Save;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ScheduleSave extends Save<Schedule>
{
	private static final long serialVersionUID = -7712568924584807729L;
	
	@Autowired
	protected void setApplyAction(final ScheduleApply actApply)
	{
		super.setApplyAction(actApply);
	}
}