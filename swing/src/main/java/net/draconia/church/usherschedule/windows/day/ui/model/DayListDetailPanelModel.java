package net.draconia.church.usherschedule.windows.day.ui.model;

import java.io.Serializable;

import java.util.Observable;

import net.draconia.church.usherschedule.domain.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DayListDetailPanelModel extends Observable implements Serializable
{
	private static final long serialVersionUID = 5042785836155503395L;
	
	@Autowired
	private DayDetailsPanelModel mObjDetailsModel;
	
	private Schedule mObjCurrentSchedule;
	
	public Schedule getCurrentSchedule()
	{
		return(mObjCurrentSchedule);
	}
	
	protected DayDetailsPanelModel getDetailsModel()
	{
		return(mObjDetailsModel);
	}
	
	public void setCurrentSchedule(final Schedule objSchedule)
	{
		mObjCurrentSchedule = objSchedule;
		
		getDetailsModel().setCurrentSchedule(objSchedule);
		
		setChanged();
		notifyObservers();
	}
	
	protected void setDetailsModel(final DayDetailsPanelModel objDetailsModel)
	{
		mObjDetailsModel = objDetailsModel;
	}
}