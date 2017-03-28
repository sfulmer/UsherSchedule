package net.draconia.church.usherschedule.windows.schedule.panels.Day.actions;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JTable;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.service.DayService;
import net.draconia.church.usherschedule.windows.schedule.panels.Day.models.ScheduleDayListTableModel;

import net.draconia.ui.subcomponent.SubComponentTableModel;
import net.draconia.ui.subcomponent.actions.Remove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDayRemove extends Remove<Day>
{
	private static final long serialVersionUID = 7966429166568060726L;
	
	@Autowired
	private DayService mObjDayService;
	
	public ScheduleDayRemove()
	{ }
	
	public ScheduleDayRemove(final JTable tblList)
	{
		super(tblList);
	}
	
	protected DayService getDayService()
	{
		return(mObjDayService);
	}
	
	protected void removeSubcomponent() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException
	{
		SubComponentTableModel<Schedule, Day> objTableModel = ((ScheduleDayListTableModel)(getListTable().getModel()));
		
		getDayService().remove(objTableModel.getRows().get(getListTable().getSelectedRow()));
	}
	
	protected void setDayService(final DayService objDayService)
	{
		mObjDayService = objDayService;
	}
}