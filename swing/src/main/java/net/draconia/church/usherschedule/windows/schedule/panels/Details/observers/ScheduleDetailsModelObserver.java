package net.draconia.church.usherschedule.windows.schedule.panels.Details.observers;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.text.JTextComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;

@Component
public class ScheduleDetailsModelObserver implements Observer, Serializable
{
	private static final long serialVersionUID = 8208470700623309403L;
	
	@Autowired
	@Qualifier("ScheduleDetailsId")
	private JTextComponent mTxtId;
	
	@Autowired
	@Qualifier("ScheduleDetailsName")
	private JTextComponent mTxtName;
	
	protected JTextComponent getIdField()
	{
		return(mTxtId);
	}
	
	protected JTextComponent getNameField()
	{
		return(mTxtName);
	}
	
	protected void setIdField(final JTextComponent txtId)
	{
		mTxtId = txtId;
	}
	
	protected void setNameField(final JTextComponent txtName)
	{
		mTxtName = txtName;
	}
	
	public void update(final Observable objObservable, final Object objArgument)
	{
		ScheduleDetailsPanelModel objModel = ((ScheduleDetailsPanelModel)(objObservable));
		Schedule objWorkingModel = objModel.getWorkingModel();
		
		if(objWorkingModel.getId() != null)
			{
			if(!getIdField().getText().equals(objWorkingModel.getId().toString()))
				getIdField().setText(objWorkingModel.getId().toString());
			}
		else if(!getIdField().getText().equals(""))
			getIdField().setText("");
		
		if(!getNameField().getText().equals(objWorkingModel.getName()))
			getNameField().setText(objWorkingModel.getName().toString());
	}
}