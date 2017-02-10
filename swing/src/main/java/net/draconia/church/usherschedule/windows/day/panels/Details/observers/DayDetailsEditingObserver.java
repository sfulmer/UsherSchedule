package net.draconia.church.usherschedule.windows.day.panels.Details.observers;

import java.io.Serializable;

import java.util.Observable;
import java.util.Observer;

import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;

import net.draconia.ui.listdetails.EnablablePanel;

public class DayDetailsEditingObserver implements Observer, Serializable
{
	private static final long serialVersionUID = 2477469298041928866L;
	
	private EnablablePanel mPnlToBeEnabled;
	
	public DayDetailsEditingObserver(final EnablablePanel pnlToBeEnabled)
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
		DayDetailsPanelModel objModel = ((DayDetailsPanelModel)(objObservable));
		
		if	(	(objModel.isEditing() && (!getPanelToBeEnabled().isEnabled()))
			||	((!objModel.isEditing()) && (getPanelToBeEnabled().isEnabled())))
			getPanelToBeEnabled().setEnabled(objModel.isEditing());
	}
}