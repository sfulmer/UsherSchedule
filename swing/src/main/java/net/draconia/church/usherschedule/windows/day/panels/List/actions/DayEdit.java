package net.draconia.church.usherschedule.windows.day.panels.List.actions;

import java.awt.event.ActionEvent;

import net.draconia.church.usherschedule.domain.Day;

import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;

import net.draconia.ui.listdetails.actions.Edit;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DayEdit extends Edit<Day>
{
	private static final long serialVersionUID = -8950289813319145033L;
	
	public DayEdit()
	{ }
	
	@Autowired
	public DayEdit(final DayDetailsPanelModel objModel)
	{
		super(objModel);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		//TODO: Do something here!!
	}
}