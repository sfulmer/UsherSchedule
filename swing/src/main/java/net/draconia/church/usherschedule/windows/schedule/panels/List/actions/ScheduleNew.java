package net.draconia.church.usherschedule.windows.schedule.panels.List.actions;

import java.awt.event.ActionEvent;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;
import net.draconia.ui.listdetails.actions.New;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ScheduleNew extends New<Schedule>
{
	private static final long serialVersionUID = -4640128558783449375L;
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getModel().setModel(null);
	}
	
	@Autowired
	protected void setModel(final ScheduleDetailsPanelModel objModel)
	{
		super.setModel(objModel);
	}
}