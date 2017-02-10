/**
 * 
 */
package net.draconia.church.usherschedule.windows.schedule.panels.Day.models;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.ui.subcomponent.SubComponentTableModel;

public class ScheduleDayListTableModel extends SubComponentTableModel<Schedule, Day>
{
	private static final long serialVersionUID = -8264186458946718628L;
	
	public ScheduleDayListTableModel(final Schedule objModel)
	{
		super(objModel, "Day");
	}
	
	public Class<?> getColumnClass(final int iColumn)
	{
		switch(iColumn)
			{
			case 0:
			case 2:
				return(Integer.class);
			case 1:
				return(Date.class);
			default:
				return(null);
			}
	}
	
	public int getColumnCount()
	{
		return(3);
	}
	
	public String getColumnName(final int iColumn)
	{
		switch(iColumn)
			{
			case 0:
				return("Id");
			case 1:
				return("Date");
			case 2:
				return("# of Services");
			default:
				return(null);
			}
	}
	
	public Object getValueAt(final int iRow, final int iColumn)
	{
		Day objDay = null;
		List<Day> lstDays;
		
		try
			{
			lstDays = getRows();
			}
		catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException objException)
			{
			objException.printStackTrace();
			
			lstDays = new ArrayList<Day>();
			}
		
		objDay = lstDays.get(iRow);
		
		switch(iColumn)
			{
			case 0:
				return(objDay.getId());
			case 1:
				return(objDay.getDate());
			case 2:
				return(objDay.getServices().size());
			default:
				return(null);
			}
	}

	public void setValueAt(final Object objValue, final int iRow, final int iColumn)
	{ }
}