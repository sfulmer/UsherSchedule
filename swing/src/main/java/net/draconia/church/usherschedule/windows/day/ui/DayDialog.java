package net.draconia.church.usherschedule.windows.day.ui;

import net.draconia.church.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.windows.day.ui.model.DayDialogModel;
import net.draconia.church.usherschedule.windows.schedule.ui.ScheduleDialog;

import net.draconia.ui.listdetails.ListDetailsDialog;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class DayDialog extends ListDetailsDialog<Day>
{
	private static final long serialVersionUID = 2648230950902185636L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	@Autowired
	public DayDialog(final ScheduleDialog wndOwner)
	{
		super("Day", wndOwner);
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	@Autowired
	protected void setListDetailsPanel(final DayListDetailPanel pnlListDetails)
	{
		if(pnlListDetails == null)
			super.setListDetailsPanel((DayListDetailPanel)(getBean(DayListDetailPanel.class)));
		else
			super.setListDetailsPanel(pnlListDetails);
	}
	
	@Autowired
	protected void setModel(final DayDialogModel objModel)
	{
		if(objModel == null)
			super.setModel((DayDialogModel)(getBean(DayDialogModel.class)));
		else
			super.setModel(objModel);
	}
}