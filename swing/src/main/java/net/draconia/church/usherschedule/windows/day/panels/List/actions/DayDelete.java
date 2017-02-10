package net.draconia.church.usherschedule.windows.day.panels.List.actions;

import java.awt.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;
import net.draconia.ui.listdetails.actions.Delete;

@Component
public class DayDelete extends Delete<Day>
{
	private static final long serialVersionUID = 6241568056250039136L;
	
	public DayDelete()
	{ }
	
	@Autowired
	public DayDelete(final DayDetailsPanelModel objModel)
	{
		super(objModel);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		//TODO: Do something!!
	}
}
