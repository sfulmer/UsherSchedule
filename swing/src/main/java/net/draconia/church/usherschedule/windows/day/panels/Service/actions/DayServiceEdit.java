package net.draconia.church.usherschedule.windows.day.panels.Service.actions;

import javax.swing.JTable;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;
import net.draconia.church.usherschedule.windows.service.ui.ServiceDialog;

import net.draconia.ui.subcomponent.actions.Edit;

public class DayServiceEdit extends Edit<Day, Service>
{
	private static final long serialVersionUID = 3251090280590480508L;
	
	public DayServiceEdit(final ServiceDialog dlgService)
	{
		super(dlgService);
	}
	
	public DayServiceEdit(final ServiceDialog dlgService, final JTable tblService)
	{
		super(dlgService, tblService);
	}
}