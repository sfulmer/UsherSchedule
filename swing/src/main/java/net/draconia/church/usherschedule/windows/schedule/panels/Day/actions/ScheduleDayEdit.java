package net.draconia.church.usherschedule.windows.schedule.panels.Day.actions;

import javax.swing.JTable;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.day.ui.DayDialog;

import net.draconia.ui.subcomponent.actions.Edit;

public class ScheduleDayEdit extends Edit<Schedule, Day>
{
	private static final long serialVersionUID = 2875261154580490384L;
	
	public ScheduleDayEdit(final DayDialog dlgDay)
	{
		super(dlgDay);
	}
	
	public ScheduleDayEdit(final DayDialog dlgDay, final JTable tblList)
	{
		super(dlgDay, tblList);
	}
}