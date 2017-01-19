package net.draconia.church.usherschedule.windows.schedule.panels.List.actions;

import java.awt.event.ActionEvent;

import net.draconia.church.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleApply;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleSave;

import net.draconia.church.usherschedule.windows.schedule.ui.ScheduleDetailsPanel;

import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;

import net.draconia.ui.listdetails.actions.Edit;

import net.draconia.ui.listdetails.model.DetailsPanelModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class ScheduleEdit extends Edit<Schedule>
{
	private static final long serialVersionUID = -3256864178995955294L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	@Autowired
	private ScheduleApply mActApply;
	
	@Autowired
	private ScheduleDetailsPanel mPnlDetails;
	
	@Autowired
	private ScheduleSave mActSave;
	
	public ScheduleEdit()
	{ }
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		if(getModel().isEditing())
			getModel().setEditing(false);
		
		getModel().setEditing(true);
		
		getDetailsPanel().requestFocusInWindow();
		
		getApplyAction().setEnabled(true);
		getApplyAction().setEnabled(false);
		getSaveAction().setEnabled(true);
		getSaveAction().setEnabled(false);
		
		setEnabled(false);
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected ScheduleApply getApplyAction()
	{
		if(mActApply == null)
			mActApply = ((ScheduleApply)(getBean(ScheduleApply.class)));
		
		return(mActApply);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sName)
	{
		return(getApplicationContext().getBean(sName));
	}
	
	protected ScheduleDetailsPanel getDetailsPanel()
	{
		if(mPnlDetails == null)
			mPnlDetails = ((ScheduleDetailsPanel)(getBean(ScheduleDetailsPanel.class)));
		
		return(mPnlDetails);
	}
	
	protected DetailsPanelModel<Schedule> getModel()
	{
		if(super.getModel() == null)
			setModel((ScheduleDetailsPanelModel)(getBean(ScheduleDetailsPanelModel.class)));
		
		return(super.getModel());
	}
	
	protected ScheduleSave getSaveAction()
	{
		if(mActSave == null)
			mActSave = ((ScheduleSave)(getBean(ScheduleSave.class)));
		
		return(mActSave);
	}
	
	protected void setApplyAction(final ScheduleApply actApply)
	{
		if(actApply == null)
			mActApply = ((ScheduleApply)(getBean(ScheduleApply.class)));
		else
			mActApply = actApply;
	}
	
	protected void setDetailsPanel(final ScheduleDetailsPanel pnlDetails)
	{
		if(pnlDetails == null)
			mPnlDetails = ((ScheduleDetailsPanel)(getBean(ScheduleDetailsPanel.class)));
		else
			mPnlDetails = pnlDetails;
	}
	
	@Autowired
	protected void setModel(final ScheduleDetailsPanelModel objModel)
	{
		if(objModel == null)
			super.setModel((ScheduleDetailsPanelModel)(getBean(ScheduleDetailsPanelModel.class)));
		else
			super.setModel(objModel);
	}
	
	protected void setSaveAction(final ScheduleSave actSave)
	{
		if(actSave == null)
			mActSave = ((ScheduleSave)(getBean(ScheduleSave.class)));
		else
			mActSave = actSave;
	}
}