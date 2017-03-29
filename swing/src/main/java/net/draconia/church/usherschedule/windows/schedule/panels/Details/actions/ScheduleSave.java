package net.draconia.church.usherschedule.windows.schedule.panels.Details.actions;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.service.ScheduleService;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;
import net.draconia.ui.listdetails.actions.Save;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ScheduleSave extends Save<Schedule>
{
	private static final long serialVersionUID = -7712568924584807729L;
	
	@Autowired
	private ScheduleService mObjScheduleService;
	
	@Autowired
	private ScheduleDetailsPanelModel mObjModel;
	
	public void actionPerformed(ActionEvent objActionEvent)
	{
		super.actionPerformed(objActionEvent);
		
		try
			{
			getScheduleService().save(getModel().getModel());
			}
		catch(SQLException objSQLException)
			{
			objSQLException.printStackTrace(System.err);
			}
		
		getModel().getWorkingModel().setId(getModel().getModel().getId());
	}
	
	protected ScheduleDetailsPanelModel getModel()
	{
		return(mObjModel);
	}
	
	protected ScheduleService getScheduleService()
	{
		return(mObjScheduleService);
	}
	
	@Autowired
	protected void setApplyAction(final ScheduleApply actApply)
	{
		super.setApplyAction(actApply);
	}
}