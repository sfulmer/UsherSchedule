package net.draconia.church.usherschedule.windows.service.ui;

import net.draconia.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Service;
import net.draconia.church.usherschedule.windows.day.ui.DayDialog;
import net.draconia.ui.listdetails.ListDetailsDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ServiceDialog extends ListDetailsDialog<Service>
{
	private static final long serialVersionUID = 2164058256921119812L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	@Autowired
	public ServiceDialog(final DayDialog wndOwner)
	{
		super("Service Details", wndOwner);
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
}