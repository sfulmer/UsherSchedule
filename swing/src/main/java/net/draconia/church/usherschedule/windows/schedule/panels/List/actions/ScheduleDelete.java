package net.draconia.church.usherschedule.windows.schedule.panels.List.actions;

import java.awt.event.ActionEvent;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;
import net.draconia.ui.listdetails.actions.Delete;

import org.springframework.stereotype.Component;

@Component
public class ScheduleDelete extends Delete<Schedule>
{
	private static final long serialVersionUID = -1811394421799801231L;
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{ }
	
	protected void setModel(final ScheduleDetailsPanelModel objModel)
	{
		super.setModel(objModel);
	}
}