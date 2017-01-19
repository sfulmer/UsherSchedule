package net.draconia.church.usherschedule.windows.schedule.ui;

import java.awt.BorderLayout;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.draconia.church.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleApply;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleSave;
import net.draconia.church.usherschedule.windows.schedule.panels.List.actions.ScheduleDelete;
import net.draconia.church.usherschedule.windows.schedule.panels.List.actions.ScheduleEdit;
import net.draconia.church.usherschedule.windows.schedule.panels.List.actions.ScheduleNew;
import net.draconia.church.usherschedule.windows.schedule.panels.List.listeners.ScheduleTableSelectionListener;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleListTableModel;

import net.draconia.ui.listdetails.ButtonsPanel;
import net.draconia.ui.listdetails.DetailsPanel;
import net.draconia.ui.listdetails.ListPanel;

import net.draconia.ui.listdetails.actions.Apply;
import net.draconia.ui.listdetails.actions.Delete;
import net.draconia.ui.listdetails.actions.Edit;
import net.draconia.ui.listdetails.actions.New;
import net.draconia.ui.listdetails.actions.Save;

import net.draconia.ui.listdetails.listeners.TableSelectionListener;

import net.draconia.ui.listdetails.model.ListTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class ScheduleListPanel extends ListPanel<Schedule>
{
	private static final long serialVersionUID = -8180702572828407514L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	public ScheduleListPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sName)
	{
		return(getApplicationContext().getBean(sName));
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected Apply<Schedule> getApplyAction()
	{
		if(super.getApplyAction() == null)
			setApplyAction(((ScheduleApply)(getBean(ScheduleApply.class))));
		
		return(super.getApplyAction());
	}
	
	protected ButtonsPanel getButtonsPanel()
	{
		if(super.getButtonsPanel() == null)
			setButtonsPanel(((ButtonsPanel)(getBean("ScheduleListButtonPanel"))));
		
		return(super.getButtonsPanel());
	}
	
	protected Delete<Schedule> getDeleteAction()
	{
		if(super.getDeleteAction() == null)
			setDeleteAction(((ScheduleDelete)(getBean(ScheduleDelete.class))));
		
		return(super.getDeleteAction());
	}
	
	protected DetailsPanel<Schedule> getDetailsPanel()
	{
		if(super.getDetailsPanel() == null)
			setDetailsPanel((ScheduleDetailsPanel)(getBean(ScheduleDetailsPanel.class)));
		
		return(super.getDetailsPanel());
	}
	
	protected Edit<Schedule> getEditAction()
	{
		if(super.getEditAction() == null)
			setEditAction(((ScheduleEdit)(getBean(ScheduleEdit.class))));
		
		return(super.getEditAction());
	}
	
	protected JScrollPane getListScrollPane()
	{
		if(super.getListScrollPane() == null)
			setListScrollPane(((JScrollPane)(getBean("ScheduleListScrollPane"))));
		
		return(super.getListScrollPane());
	}
	
	protected TableSelectionListener<Schedule> getListSelectionListener()
	{
		if(super.getListSelectionListener() == null)
			setListSelectionListener((ScheduleTableSelectionListener)(getBean(ScheduleTableSelectionListener.class)));
		
		return(super.getListSelectionListener());
	}
	
	protected JTable getListTable()
	{
		if(super.getListTable() == null)
			setListTable(((JTable)(getBean("tblScheduleList"))));
		
		return(super.getListTable());
	}
	
	protected ListTableModel<Schedule> getListTableModel()
	{
		if(super.getListTableModel() == null)
			setListTableModel(((ScheduleListTableModel)(getBean(ScheduleListTableModel.class))));
		
		return(super.getListTableModel());
	}
	
	protected New<Schedule> getNewAction()
	{
		if(super.getNewAction() == null)
			setNewAction(((ScheduleNew)(getBean(ScheduleNew.class))));
		
		return(super.getNewAction());
	}
	
	protected Save<Schedule> getSaveAction()
	{
		if(super.getSaveAction() == null)
			setSaveAction(((ScheduleSave)(getBean(ScheduleSave.class))));
		
		return(super.getSaveAction());
	}
		
	@PostConstruct
	protected void initPaneL()
	{
		super.initPanel();
	}
	
	@Autowired
	@Qualifier("ScheduleListButtonPanel")
	protected void setButtonsPanel(final ButtonsPanel pnlButtons)
	{
		super.setButtonsPanel(pnlButtons);
		
		getButtonsPanel().setButtons(new Action[] {getNewAction(), getEditAction(), getDeleteAction()});
	}
	
	@Autowired
	protected void setDeleteAction(final ScheduleDelete actDelete)
	{
		super.setDeleteAction(actDelete);
	}
	
	@Autowired
	protected void setDetailsPanel(final ScheduleDetailsPanel pnlDetails)
	{
		super.setDetailsPanel(pnlDetails);
	}
	
	@Autowired
	protected void setEditAction(final ScheduleEdit actEdit)
	{
		if(actEdit == null)
			super.setEditAction((ScheduleEdit)(getBean(ScheduleEdit.class)));
		else
			super.setEditAction(actEdit);
	}
	
	@Autowired
	@Qualifier("ScheduleListScrollPane")
	protected void setListScrollPane(final JScrollPane pnlList)
	{
		super.setListScrollPane(pnlList);
	}
	
	@Autowired
	protected void setListSelectionListener(final ScheduleTableSelectionListener objListSelectionListener)
	{
		if(objListSelectionListener == null)
			super.setListSelectionListener((ScheduleTableSelectionListener)(getBean(ScheduleTableSelectionListener.class)));
		else
			super.setListSelectionListener(objListSelectionListener);
	}
	
	@Autowired
	@Qualifier("tblScheduleList")
	protected void setListTable(final JTable tblList)
	{
		super.setListTable(tblList);
		
		getListTable().getSelectionModel().addListSelectionListener(getListSelectionListener());
	}
	
	@Autowired
	protected void setListTableModel(final ScheduleListTableModel objTableModel)
	{
		super.setListTableModel(objTableModel);
	}
	
	@Autowired
	protected void setNewAction(final ScheduleNew actNew)
	{
		super.setNewAction(actNew);
	}
}