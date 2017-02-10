package net.draconia.church.usherschedule.windows.day.ui.observers;

import java.io.Serializable;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.day.ui.model.DayListDetailPanelModel;

@Component
public class DayListDetailPanelModelObserver implements Observer, Serializable
{
	private static final long serialVersionUID = 1102639249878661538L;
	
	private JComboBox<Schedule> mCboSchedule;
	
	public DayListDetailPanelModelObserver()
	{ }
	
	protected JComboBox<Schedule> getScheduleComboBox()
	{
		return(mCboSchedule);
	}
	
	@Autowired
	@Qualifier("DayScheduleComboBox")
	protected void setScheduleComboBox(final JComboBox<Schedule> cboSchedule)
	{
		mCboSchedule = cboSchedule;
	}
	
	public void update(final Observable objObservable, final Object objArgument)
	{
		DayListDetailPanelModel objModel = ((DayListDetailPanelModel)(objObservable));
		
		if(!objModel.getCurrentSchedule().equals(getScheduleComboBox().getSelectedItem()))
			getScheduleComboBox().setSelectedItem(objModel.getCurrentSchedule());
	}
}