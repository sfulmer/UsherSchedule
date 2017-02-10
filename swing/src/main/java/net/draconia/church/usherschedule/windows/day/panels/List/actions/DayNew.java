package net.draconia.church.usherschedule.windows.day.panels.List.actions;

import java.awt.event.ActionEvent;

import net.draconia.church.usherschedule.domain.Day;

import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;

import net.draconia.ui.listdetails.actions.New;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DayNew extends New<Day>
{
	private static final long serialVersionUID = -2253908752719971276L;
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getModel().setModel(null);
		getModel().setEditing(true);
	}
	
	@Autowired
	protected void setModel(final DayDetailsPanelModel objModel)
	{
		super.setModel(objModel);
	}
}