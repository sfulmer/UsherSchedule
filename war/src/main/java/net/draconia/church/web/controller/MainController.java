package net.draconia.church.web.controller;

import net.draconia.ApplicationContextProvider;

import net.draconia.church.usherschedule.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class MainController
{
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	@Autowired
	private ScheduleService mObjScheduleService;
	
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
	
	protected ScheduleService getScheduleService()
	{
		return(mObjScheduleService);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index()
	{
		return("index");
	}
}