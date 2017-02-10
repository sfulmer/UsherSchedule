package net.draconia.church.usherschedule.windows.day.ui.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.ui.listdetails.model.ListTableModel;

@Component
public class DayListTableModel extends ListTableModel<Day> implements Serializable
{
	private static final long serialVersionUID = 1499492111168568995L;
	
	public Class<?> getColumnClass(final int iColumnIndex)
	{
		switch(iColumnIndex)
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
	
	public String getColumnName(final int iColumnIndex)
	{
		switch(iColumnIndex)
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
	
	public Object getValueAt(final int iRowIndex, final int iColumnIndex)
	{
		Day objRow = getRow(iRowIndex);
		
		switch(iColumnIndex)
			{
			case 0:
				return(objRow.getId());
			case 1:
				return(objRow.getDate());
			case 2:
				return(objRow.getServices().size());
			default:
				return(null);
			}
	}
	
	public boolean isCellEditable(final int iRowIndex, final int iColumnIndex)
	{
		return(false);
	}
	
	public void setValueAt(final Object objValue, final int iRowIndex, final int iColumnIndex)
	{ }
}