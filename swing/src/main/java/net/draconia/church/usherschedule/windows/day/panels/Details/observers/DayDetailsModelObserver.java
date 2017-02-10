package net.draconia.church.usherschedule.windows.day.panels.Details.observers;

import java.io.Serializable;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.text.JTextComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

@Component
public class DayDetailsModelObserver implements Observer, Serializable
{
	private static final long serialVersionUID = 8208470700623309403L;
	
	private JTextComponent mTxtId;
	
	@Autowired
	@Qualifier("dayDateField")
	private JDatePickerImpl mDPDate;
	
	protected JDatePickerImpl getDateField()
	{
		return(mDPDate);
	}
	
	protected JTextComponent getIdField()
	{
		return(mTxtId);
	}
	
	protected void setDateField(final JDatePickerImpl dpDate)
	{
		mDPDate = dpDate;
	}
	
	@Autowired
	@Qualifier("DayDetailsId")
	protected void setIdField(final JTextComponent txtId)
	{
		mTxtId = txtId;
	}
	
	@SuppressWarnings("unchecked")
	public void update(final Observable objObservable, final Object objArgument)
	{
		DayDetailsPanelModel objModel = ((DayDetailsPanelModel)(objObservable));
		Day objWorkingModel = objModel.getWorkingModel();
		
		if(objWorkingModel.getId() != null)
			{
			if(!getIdField().getText().equals(objWorkingModel.getId().toString()))
				getIdField().setText(objWorkingModel.getId().toString());
			}
		else if(!getIdField().getText().equals(""))
			getIdField().setText("");
		
		if((!((getDateField().getModel().getValue() == null) && (objWorkingModel.getDate() == null) && (getDateField().getModel().getValue() == objWorkingModel.getDate())))
			&& (!getDateField().getModel().getValue().equals(objWorkingModel.getDate())))
			((DateModel<Date>)(getDateField().getModel())).setValue(objWorkingModel.getDate());
	}
}