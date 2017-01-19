package net.draconia.church.usherschedule.windows.schedule.panels.Details.actions;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.ui.ScheduleDetailsPanel;

import net.draconia.ui.listdetails.actions.Cancel;

@Component
public class ScheduleCancel extends Cancel<Schedule>
{
	private static final long serialVersionUID = 4088950072649343137L;
	
	public ScheduleCancel()
	{
		super();
	}
	
	@Autowired
	protected void setDetailsPanel(final ScheduleDetailsPanel pnlDetails)
	{
		super.setDetailsPanel(pnlDetails);
	}
}