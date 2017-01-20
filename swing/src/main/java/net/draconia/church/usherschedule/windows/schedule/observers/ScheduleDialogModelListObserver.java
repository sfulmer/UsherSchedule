package net.draconia.church.usherschedule.windows.schedule.observers;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleListTableModel;

import net.draconia.ui.listdetails.observers.DialogModelListObserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDialogModelListObserver extends DialogModelListObserver<Schedule>
{
	private static final long serialVersionUID = 6892303553165797057L;
	
	@Autowired
	public ScheduleDialogModelListObserver(final ScheduleListTableModel objListTableModel)
	{
		super(objListTableModel);
	}
}