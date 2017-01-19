package net.draconia.church.usherschedule.windows.schedule.panels.Details.observers;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;
import net.draconia.ui.listdetails.EnablablePanel;

public class ScheduleDetailsEditingObserver implements Observer, Serializable
{
	private static final long serialVersionUID = 2477469298041928866L;
	
	private EnablablePanel mPnlToBeEnabled;
	
	public ScheduleDetailsEditingObserver(final EnablablePanel pnlToBeEnabled)
	{
		setPanelToBeEnabled(pnlToBeEnabled);
	}
	
	protected EnablablePanel getPanelToBeEnabled()
	{
		return(mPnlToBeEnabled);
	}
	
	protected void setPanelToBeEnabled(final EnablablePanel pnlToBeEnabled)
	{
		mPnlToBeEnabled = pnlToBeEnabled;
	}
	
	public void update(final Observable objObservable, final Object objArgument)
	{
		ScheduleDetailsPanelModel objModel = ((ScheduleDetailsPanelModel)(objObservable));
		
		if	(	(objModel.isEditing() && (!getPanelToBeEnabled().isEnabled()))
			||	((!objModel.isEditing()) && (getPanelToBeEnabled().isEnabled())))
			getPanelToBeEnabled().setEnabled(objModel.isEditing());
	}
}