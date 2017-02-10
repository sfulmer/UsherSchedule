package net.draconia.church.usherschedule.windows.schedule.panels.Day.actions;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.day.ui.DayDialog;
import net.draconia.church.usherschedule.windows.day.ui.model.DayDialogModel;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;

import net.draconia.ui.subcomponent.actions.Add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDayAdd extends Add<Schedule, Day>
{
	private static final long serialVersionUID = -3775484836732092144L;
	
	@Autowired
	public ScheduleDayAdd(final ScheduleDetailsPanelModel objDetailsModel, final DayDialog dlgNew)
	{
		super(objDetailsModel, dlgNew);
	}
	
	protected void openNewDialog()
	{
		DayDialog dlg = ((DayDialog)(getNewDialog()));
		Schedule objSchedule = getDetailsModel().getWorkingModel();
		
		((DayDialogModel)(dlg.getModel())).setCurrentSchedule(objSchedule);
		
		dlg.setVisible(true);
	}
}