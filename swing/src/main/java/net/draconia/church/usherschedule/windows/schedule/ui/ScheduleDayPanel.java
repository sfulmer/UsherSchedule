package net.draconia.church.usherschedule.windows.schedule.ui;

import net.draconia.ApplicationContextProvider;
import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.day.ui.DayDialog;

import net.draconia.church.usherschedule.windows.schedule.panels.Day.actions.ScheduleDayAdd;
import net.draconia.church.usherschedule.windows.schedule.panels.Day.actions.ScheduleDayEdit;
import net.draconia.church.usherschedule.windows.schedule.panels.Day.actions.ScheduleDayRemove;

import net.draconia.church.usherschedule.windows.schedule.panels.Day.models.ScheduleDayListTableModel;

import net.draconia.church.usherschedule.windows.schedule.panels.Day.observers.ScheduleDayPanelObserver;

import net.draconia.ui.subcomponent.SubComponentPanel;
import net.draconia.ui.subcomponent.SubComponentTableModel;

import net.draconia.ui.subcomponent.actions.Add;
import net.draconia.ui.subcomponent.actions.Edit;
import net.draconia.ui.subcomponent.actions.Remove;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class ScheduleDayPanel extends SubComponentPanel<Schedule, Day>
{
	private static final long serialVersionUID = 6396244716041428063L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	public ScheduleDayPanel()
	{
		super("Days:");
	}
	
	public ScheduleDayPanel(final Schedule objSchedule)
	{
		super("Day");
		
		getModel().setModel(objSchedule);
	}
	
	protected Add<Schedule, Day> getAddAction()
	{
		if(super.getAddAction() == null)
			setAddAction((ScheduleDayAdd)(getBean(ScheduleDayAdd.class)));
		
		return(super.getAddAction());
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
	
	protected Edit<Schedule, Day> getEditAction()
	{
		if(super.getEditAction() == null)
			setEditAction(new ScheduleDayEdit(((DayDialog)(getBean(DayDialog.class))), getListTable()));
		
		return(super.getEditAction());
	}
	
	protected SubComponentTableModel<Schedule, Day> getListTableModel()
	{
		if(super.getListTableModel() == null)
			super.setListTableModel(new ScheduleDayListTableModel(getModel().getModel()));
		
		return(super.getListTableModel());
	}
	
	protected Remove<Day> getRemoveAction()
	{
		if(super.getRemoveAction() == null)
			setRemoveAction((ScheduleDayRemove)(getBean(ScheduleDayRemove.class)));
		
		return(super.getRemoveAction());
	}
	
	public Schedule getSchedule()
	{
		return(getModel().getModel());
	}
	
	@Autowired
	protected void setAddAction(final ScheduleDayAdd actAdd)
	{
		if(actAdd == null)
			super.setAddAction((ScheduleDayAdd)(getBean(ScheduleDayAdd.class)));
		else
			super.setAddAction(actAdd);
	}
	
	protected void setEditAction(final ScheduleDayEdit actEdit)
	{
		if(actEdit == null)
			super.setEditAction(new ScheduleDayEdit(((DayDialog)(getBean(DayDialog.class))), getListTable()));
		else
			super.setEditAction(actEdit);
	}
	
	protected void setListTableModel(final ScheduleDayListTableModel objTableModel)
	{
		super.setListTableModel(objTableModel);
	}
	
	@Autowired
	protected void setPanelObserver(final ScheduleDayPanelObserver objPanelObserver)
	{
		if(objPanelObserver == null)
			super.setPanelObserver((ScheduleDayPanelObserver)(getBean(ScheduleDayPanelObserver.class)));
		else
			super.setPanelObserver(objPanelObserver);
	}
	
	protected void setRemoveAction(final ScheduleDayRemove actRemove)
	{
		if(actRemove == null)
			super.setRemoveAction((ScheduleDayRemove)(getBean(ScheduleDayRemove.class)));
		else
			super.setRemoveAction(actRemove);
	}
	
	public void setSchedule(final Schedule objSchedule)
	{
		getModel().setModel(objSchedule);
	}
}