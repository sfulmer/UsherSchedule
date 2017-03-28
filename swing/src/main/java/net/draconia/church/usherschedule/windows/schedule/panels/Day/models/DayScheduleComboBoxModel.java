package net.draconia.church.usherschedule.windows.schedule.panels.Day.models;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import net.draconia.ApplicationContextProvider;
import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.service.ScheduleService;

public class DayScheduleComboBoxModel implements ComboBoxModel<Schedule>, Serializable
{
	private static final long serialVersionUID = -3289373643758455195L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	@Autowired
	private ScheduleService mObjService;
	
	private Integer miSelectedIndex = -1;
	private List<ListDataListener> mLstListDataListeners;
	private List<Schedule> mLstSchedules;
	
	public DayScheduleComboBoxModel()
	{ }
	
	public void addListDataListener(final ListDataListener objListDataListener)
	{
		getListDataListeners().add(objListDataListener);
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
	
	protected List<Schedule> getSchedules() throws SQLException
	{
		if(mLstSchedules == null)
			mLstSchedules = getScheduleService().list();
		
		return(mLstSchedules);
	}

	public Schedule getElementAt(final int iIndex)
	{
		try
			{
			return(getSchedules().get(iIndex));
			}
		catch(SQLException objSQLException)
			{
			objSQLException.printStackTrace(System.err);
			
			return(null);
			}
	}
	
	protected List<ListDataListener> getListDataListeners()
	{
		if(mLstListDataListeners == null)
			mLstListDataListeners = new ArrayList<ListDataListener>();
		
		return(mLstListDataListeners);
	}
	
	protected ScheduleService getScheduleService()
	{
		return(mObjService);
	}
	
	public int getSize()
	{
		try
			{
			return(getSchedules().size());
			}
		catch(SQLException objSQLException)
			{
			objSQLException.printStackTrace(System.err);
			
			return(0);
			}
	}
	
	public void removeListDataListener(final ListDataListener objListDataListener)
	{
		getListDataListeners().remove(objListDataListener);
	}
	
	public Object getSelectedItem()
	{
		if(miSelectedIndex == -1)
			return(null);
		else
			try
				{
				return(getSchedules().get(miSelectedIndex));
				}
			catch(SQLException objSQLException)
				{
				objSQLException.printStackTrace(System.err);
				
				return(null);
				}
	}
	
	public void setSelectedItem(final Object objSchedule)
	{
		try
			{
			miSelectedIndex = getSchedules().indexOf(objSchedule);
			}
		catch(SQLException objSQLException)
			{
			objSQLException.printStackTrace(System.err);
			
			miSelectedIndex = -1;
			}
	}
}