package net.draconia.church.usherschedule.windows.day.panels.Details.actions;

import net.draconia.church.usherschedule.domain.Day;

import net.draconia.ui.listdetails.actions.Save;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DaySave extends Save<Day>
{
	private static final long serialVersionUID = 5670355686291436789L;
	
	public DaySave()
	{ }
	
	@Autowired
	public DaySave(final DayApply actApply)
	{
		super(actApply);
	}
}