package net.draconia.church.usherschedule.windows.schedule.panels.List.listeners;

import net.draconia.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.windows.schedule.panels.List.actions.ScheduleDelete;
import net.draconia.church.usherschedule.windows.schedule.panels.List.actions.ScheduleEdit;
import net.draconia.church.usherschedule.windows.schedule.ui.ScheduleDetailsPanel;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDialogModel;
import net.draconia.ui.listdetails.DetailsPanel;
import net.draconia.ui.listdetails.actions.Delete;
import net.draconia.ui.listdetails.actions.Edit;
import net.draconia.ui.listdetails.listeners.TableSelectionListener;

import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class ScheduleTableSelectionListener extends TableSelectionListener<Schedule>
{
	private static final long serialVersionUID = -7350110335749593971L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationConextProvider;
	
	private ScheduleDialogModel mObjDialogModel;
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationConextProvider);
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected Delete<Schedule> getDeleteAction()
	{
		if(super.getDeleteAction() == null)
			setDeleteAction((ScheduleDelete)(getBean(ScheduleDelete.class)));
		
		return(super.getDeleteAction());
	}
	
	protected DetailsPanel<Schedule> getDetailsPanel()
	{
		if(super.getDetailsPanel() == null)
			setDetailsPanel((ScheduleDetailsPanel)(getBean(ScheduleDetailsPanel.class)));
		
		return(super.getDetailsPanel());
	}
	
	@Autowired
	protected void setDetailsPanel(final ScheduleDetailsPanel pnlDetails)
	{
		if(pnlDetails == null)
			super.setDetailsPanel((ScheduleDetailsPanel)(getBean(ScheduleDetailsPanel.class)));
		else
			super.setDetailsPanel(pnlDetails);
	}
	
	protected ScheduleDialogModel getDialogModel()
	{
		return(mObjDialogModel);
	}
	
	protected Edit<Schedule> getEditAction()
	{
		if(super.getEditAction() == null)
			setEditAction((ScheduleEdit)(getBean(ScheduleEdit.class)));
		
		return(super.getEditAction());
	}
	
	@Autowired
	protected void setDeleteAction(final ScheduleDelete actDelete)
	{
		if(actDelete == null)
			super.setDeleteAction((ScheduleDelete)(getBean(ScheduleDelete.class)));
		else
			super.setDeleteAction(actDelete);
	}
	
	protected JTable getListTable()
	{
		if(super.getListTable() == null)
			setListTable((JTable)(getBean("tblScheduleList")));
		
		return(super.getListTable());
	}
	
	@Autowired
	protected void setDialogModel(final ScheduleDialogModel objDialogModel)
	{
		mObjDialogModel = objDialogModel;
	}
	
	@Autowired
	protected void setEditAction(final Edit<Schedule> actEdit)
	{
		if(actEdit == null)
			super.setEditAction((ScheduleEdit)(getBean(ScheduleEdit.class)));
		else
			super.setEditAction(actEdit);
	}
	
	@Autowired
	@Qualifier("tblScheduleList")
	protected void setListTable(final JTable tblList)
	{
		if(tblList == null)
			super.setListTable((JTable)(getBean("tblScheduleList")));
		else
			super.setListTable(tblList);
	}
}