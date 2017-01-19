package net.draconia.church.usherschedule.windows.schedule.ui;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.ui.listdetails.ButtonsPanel;
import net.draconia.ui.listdetails.ListDetailsPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ScheduleListDetailsPanel extends ListDetailsPanel<Schedule>
{
	private static final long serialVersionUID = 5203911681628358910L;
	
	public ScheduleListDetailsPanel()
	{
		super();
	}
	
	@Autowired
	@Qualifier("ScheduleMainButtonPanel")
	protected void setButtonsPanel(final ButtonsPanel pnlButtons)
	{
		super.setButtonsPanel(pnlButtons);
	}
	
	@Autowired
	protected void setDetailsPanel(final ScheduleDetailsPanel pnlScheduleDetails)
	{
		super.setDetailsPanel(pnlScheduleDetails);
	}
	
	@Autowired
	protected void setListPanel(final ScheduleListPanel pnlScheduleList)
	{
		super.setListPanel(pnlScheduleList);
	}
}