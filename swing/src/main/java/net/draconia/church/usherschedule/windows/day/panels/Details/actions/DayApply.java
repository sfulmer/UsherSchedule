package net.draconia.church.usherschedule.windows.day.panels.Details.actions;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;
import net.draconia.ui.listdetails.actions.Apply;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DayApply extends Apply<Day>
{
	private static final long serialVersionUID = 5807629348391324362L;
	
	public DayApply()
	{ }
	
	@Autowired
	public DayApply(final DayDetailsPanelModel objModel)
	{
		super(objModel);
	}
}