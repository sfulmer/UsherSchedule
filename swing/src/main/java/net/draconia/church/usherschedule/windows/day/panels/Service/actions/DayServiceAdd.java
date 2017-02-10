package net.draconia.church.usherschedule.windows.day.panels.Service.actions;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;

import net.draconia.church.usherschedule.windows.service.ui.ServiceDialog;

import net.draconia.church.usherschedule.windows.service.ui.model.ServiceDialogModel;

import net.draconia.ui.subcomponent.actions.Add;

import org.springframework.stereotype.Component;

@Component
public class DayServiceAdd extends Add<Day, Service>
{
	private static final long serialVersionUID = 4288071543890685880L;
	
	public DayServiceAdd(final DayDetailsPanelModel objDetailsModel, final ServiceDialog dlgService)
	{
		super(objDetailsModel, dlgService);
	}
	
	protected void openNewDialog()
	{
		ServiceDialog dlg = ((ServiceDialog)(getNewDialog()));
		Day objDay = getDetailsModel().getWorkingModel();
		
		((ServiceDialogModel)(dlg.getModel())).setCurrentDay(objDay);
		
		dlg.setVisible(true);
	}
}