package net.draconia.church.usherschedule.windows.service.ui.model;

import java.lang.reflect.InvocationTargetException;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

import net.draconia.ui.subcomponent.SubComponentTableModel;

public class DayServiceListTableModel extends SubComponentTableModel<Day, Service>
{
	private static final long serialVersionUID = 4014208563762632814L;
	
	public DayServiceListTableModel(final Day objDay)
	{
		super(objDay, "Services");
	}
	
	public Class<?> getColumnClass(final int iColumn)
	{
		switch(iColumn)
			{
			case 0:
			case 2:
				return(Integer.class);
			case 1:
				return(String.class);
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
				return("Label");
			case 2:
				return("# Persons Scheduled");
			default:
				return(null);
			}
	}
	
	public Object getValueAt(final int iRow, final int iColumn)
	{
		try
			{
			Service objService = getRows().get(iRow);
			
			switch(iColumn)
				{
				case 0:
					return(objService.getId());
				case 1:
					return(objService.getLabel());
				case 2:
					return(objService.getPersons().size());
				default:
					return(null);
				}
			}
		catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException objException)
			{
			objException.printStackTrace();
			
			return(null);
			}
	}
	
	public void setValueAt(final Object objValue, final int iRow, final int iColumn)
	{ }
}