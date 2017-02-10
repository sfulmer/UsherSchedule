package net.draconia.church.usherschedule.windows.schedule.panels.Details.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.JTextComponent;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;

import net.draconia.utilities.TextUtilities;

public class ScheduleDetailsFieldBindingEditingChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 67621882316294551L;
	
	private Map<String, JTextComponent> mMapFields;
	
	public ScheduleDetailsFieldBindingEditingChangeListener()
	{ }
	
	public ScheduleDetailsFieldBindingEditingChangeListener(final String sField, final JTextComponent txtField)
	{
		getFieldMap().put(sField, txtField);
	}
	
	public ScheduleDetailsFieldBindingEditingChangeListener(final String[] sArrFields, final JTextComponent[] txtArrFields)
	{
		if(sArrFields.length == txtArrFields.length)
			for(int iLength = sArrFields.length, iLoop = 0; iLoop < iLength; iLoop++)
				addField(sArrFields[iLoop], txtArrFields[iLoop]);
	}
	
	public void addField(final String sField, final JTextComponent txtField)
	{
		getFieldMap().put(sField, txtField);
	}
	
	public Map<String, JTextComponent> getFieldMap()
	{
		if(mMapFields == null)
			mMapFields = new HashMap<String, JTextComponent>();
		
		return(mMapFields);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		ScheduleDetailsPanelModel objModel = ((ScheduleDetailsPanelModel)(objPropertyChangeEvent.getSource()));
		
		if	(	(((Boolean)(objPropertyChangeEvent.getNewValue())) == true)
			&&	(((Boolean)(objPropertyChangeEvent.getOldValue())) == false))
			{
			Schedule objWorkingModel = objModel.getWorkingModel();
			TextUtilities objTextUtilities = new TextUtilities();
		
			for(String sField : getFieldMap().keySet())
				{
				JTextComponent txtField = getFieldMap().get(sField);
			
				objTextUtilities.resetTextComponent(txtField);
				objTextUtilities.setupUnRedoableBoundTextComponent(txtField, objWorkingModel, sField);
				}
			}
	}
}