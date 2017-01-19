package net.draconia.church.usherschedule.ui.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;

import javax.swing.table.TableModel;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class UsherScheduleTableModel implements Serializable, TableModel
{
	private static final long serialVersionUID = -5262513158146417883L;
	
	protected List<TableModelListener> mLstTableModelListeners;
	
	@Autowired
	private Schedule mObjSchedule;
	
	public UsherScheduleTableModel()
	{ }
	
	public void addTableModelListener(final TableModelListener objTableModelListener)
	{
		getTableModelListeners().add(objTableModelListener);
	}
	
	public Class<?> getColumnClass(final int iColumn)
	{
		return(Day.class);
	}
	
	public int getColumnCount()
	{
		return(1);
	}
	
	public String getColumnName(final int iColumn)
	{
		return(null);
	}
	
	protected Schedule getSchedule()
	{
		return(mObjSchedule);
	}
	
	public int getRowCount()
	{
		return(getSchedule().getDays().size());
	}
	
	protected List<TableModelListener> getTableModelListeners()
	{
		if(mLstTableModelListeners == null)
			mLstTableModelListeners = new ArrayList<TableModelListener>();
		
		return(mLstTableModelListeners);
	}
	
	public Object getValueAt(final int iRow, final int iColumn)
	{
		if(iColumn == 0)
			return(getSchedule().getDays().get(iRow));
		else
			return(null);
	}
	
	public boolean isCellEditable(final int iRow, final int iColumn)
	{
		return(false);
	}
	
	public void removeTableModelListener(final TableModelListener objTableModelListener)
	{
		getTableModelListeners().remove(objTableModelListener);
	}
	
	public void setValueAt(final Object objValue, final int iRow, final int iColumn)
	{ }
}