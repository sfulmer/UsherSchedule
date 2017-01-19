package net.draconia.church.usherschedule.windows.schedule.ui.model;

import net.draconia.church.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.ui.listdetails.model.DialogModel;
import net.draconia.ui.listdetails.model.ListTableModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class ScheduleListTableModel extends ListTableModel<Schedule>
{
	private static final long serialVersionUID = -7600517207996228035L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	public ScheduleListTableModel()
	{
		super();
	}
	
	@Autowired
	public ScheduleListTableModel(final ScheduleDialogModel objDialogModel)
	{
		super(objDialogModel);
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	public Class<?> getColumnClass(final int iColumn)
	{
		switch(iColumn)
			{
			case 0:
				return(Integer.class);
			case 1:
				return(String.class);
			default:
				return(null);
			}
	}
	
	public int getColumnCount()
	{
		return(2);
	}
	
	public String getColumnName(final int iColumn)
	{
		switch(iColumn)
			{
			case 0:
				return("Id");
			case 1:
				return("Name");
			default:
				return(null);
			}
	}
	
	public DialogModel<Schedule> getDialogModel()
	{
		if(super.getDialogModel() == null)
			setDialogModel((ScheduleDialogModel)(getBean(ScheduleDialogModel.class)));
		
		return(super.getDialogModel());
	}
	
	public Object getValueAt(final int iRow, final int iColumn)
	{
		Schedule objSchedule = getRow(iRow);
		
		switch(iColumn)
			{
			case 0:
				return(objSchedule.getId());
			case 1:
				return(objSchedule.getName());
			default:
				return(null);
			}
	}
	
	public boolean isCellEditable(final int iRow, final int iColumn)
	{
		return(false);
	}
	
	@Autowired
	public void setDialogModel(final ScheduleDialogModel objDialogModel)
	{
		if(objDialogModel == null)
			super.setDialogModel((ScheduleDialogModel)(getBean(ScheduleDialogModel.class)));
		else
			super.setDialogModel(objDialogModel);
	}
	
	public void setValueAt(final Object objValue, final int iRow, final int iColumn)
	{ }
}