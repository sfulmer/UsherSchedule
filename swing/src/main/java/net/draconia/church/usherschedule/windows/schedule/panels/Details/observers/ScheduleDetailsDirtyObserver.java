package net.draconia.church.usherschedule.windows.schedule.panels.Details.observers;

import java.io.Serializable;

import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleApply;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleSave;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;
import net.draconia.ui.listdetails.actions.Apply;
import net.draconia.ui.listdetails.actions.Save;

@Component
public class ScheduleDetailsDirtyObserver implements Observer, Serializable
{
	private static final long serialVersionUID = -2064777208000351345L;
	
	@Autowired
	private ScheduleApply mActApply;
	
	@Autowired
	private ScheduleSave mActSave;
	
	public Apply<Schedule> getApplyAction()
	{
		return(mActApply);
	}
	
	public Save<Schedule> getSaveAction()
	{
		return(mActSave);
	}
	
	public void update(final Observable objObservable, final Object objArgument)
	{
		ScheduleDetailsPanelModel objModel = ((ScheduleDetailsPanelModel)(objObservable));
		
		getApplyAction().setEnabled(true);
		getSaveAction().setEnabled(true);
		
		if(objModel.isDirty())
			{
			getApplyAction().setEnabled(true);;
			getSaveAction().setEnabled(true);;
			}
		else
			{
			getSaveAction().setEnabled(objModel.getModel().getId() == null);
			getApplyAction().setEnabled(false);
			}
	}
}