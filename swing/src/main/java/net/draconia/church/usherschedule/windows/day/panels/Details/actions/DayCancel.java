package net.draconia.church.usherschedule.windows.day.panels.Details.actions;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.windows.day.ui.DayDetailsPanel;

import net.draconia.ui.listdetails.actions.Cancel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DayCancel extends Cancel<Day>
{
	private static final long serialVersionUID = -6336711638735579301L;
	
	public DayCancel()
	{ }
	
	@Autowired
	public DayCancel(final DayDetailsPanel pnlDetails)
	{
		super(pnlDetails);
	}
}