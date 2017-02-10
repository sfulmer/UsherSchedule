package net.draconia.church.usherschedule.windows.day.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;

import net.draconia.church.ApplicationContextProvider;

import net.draconia.church.usherschedule.domain.Day;

import net.draconia.church.usherschedule.windows.day.panels.List.actions.DayDelete;
import net.draconia.church.usherschedule.windows.day.panels.List.actions.DayEdit;
import net.draconia.church.usherschedule.windows.day.panels.List.actions.DayNew;

import net.draconia.church.usherschedule.windows.day.ui.model.DayListTableModel;

import net.draconia.ui.listdetails.ListPanel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class DayListPanel extends ListPanel<Day>
{
	private static final long serialVersionUID = 5686608945243314989L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	public DayListPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	@PostConstruct
	protected void initPanel()
	{
		super.initPanel();
		
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	@Autowired
	protected void setDeleteAction(final DayDelete actDelete)
	{
		if(actDelete == null)
			super.setDeleteAction((DayDelete)(getBean(DayDelete.class)));
		else
			super.setDeleteAction(actDelete);
	}
	
	@Autowired
	protected void setEditAction(final DayEdit actEdit)
	{
		if(actEdit == null)
			super.setEditAction((DayEdit)(getBean(DayEdit.class)));
		else
			super.setEditAction(actEdit);
	}
	
	@Autowired
	protected void setListTableModel(final DayListTableModel objTableModel)
	{
		if(objTableModel == null)
			super.setListTableModel((DayListTableModel)(getBean(DayListTableModel.class)));
		else
			super.setListTableModel(objTableModel);
	}
	
	@Autowired
	protected void setNewAction(final DayNew actNew)
	{
		if(actNew == null)
			super.setNewAction((DayNew)(getBean(DayNew.class)));
		else
			super.setNewAction(actNew);
	}
}