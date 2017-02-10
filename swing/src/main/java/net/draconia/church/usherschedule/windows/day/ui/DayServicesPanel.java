package net.draconia.church.usherschedule.windows.day.ui;

import net.draconia.church.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

import net.draconia.church.usherschedule.windows.day.panels.Service.actions.DayServiceAdd;
import net.draconia.church.usherschedule.windows.day.panels.Service.actions.DayServiceEdit;
import net.draconia.church.usherschedule.windows.day.panels.Service.actions.DayServiceRemove;

import net.draconia.church.usherschedule.windows.service.ui.ServiceDialog;

import net.draconia.church.usherschedule.windows.service.ui.model.DayServiceListTableModel;

import net.draconia.ui.subcomponent.SubComponentPanel;
import net.draconia.ui.subcomponent.SubComponentTableModel;

import net.draconia.ui.subcomponent.actions.Add;
import net.draconia.ui.subcomponent.actions.Edit;
import net.draconia.ui.subcomponent.actions.Remove;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class DayServicesPanel extends SubComponentPanel<Day, Service>
{
	private static final long serialVersionUID = -2729998517275697397L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	private Day mObjDay;
	
	public DayServicesPanel()
	{
		super("Services");
	}
	
	protected Add<Day, Service> getAddAction()
	{
		if(super.getAddAction() == null)
			setAddAction((DayServiceAdd)(getBean(DayServiceAdd.class)));
		
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
	
	public Day getDay()
	{
		return(mObjDay);
	}
	
	protected Edit<Day, Service> getEditAction()
	{
		if(super.getEditAction() == null)
			setEditAction(new DayServiceEdit(((ServiceDialog)(getBean(ServiceDialog.class))), getListTable()));
		
		return(super.getEditAction());
	}
	
	protected SubComponentTableModel<Day, Service> getListTableModel()
	{
		if(super.getListTableModel() == null)
			super.setListTableModel(new DayServiceListTableModel(getDay()));
		
		return(super.getListTableModel());
	}
	
	protected Remove<Service> getRemoveAction()
	{
		if(super.getRemoveAction() == null)
			setRemoveAction(new DayServiceRemove(getListTable()));
		
		return(super.getRemoveAction());
	}
	
	public void setDay(final Day objDay)
	{
		mObjDay = objDay;
	}
}