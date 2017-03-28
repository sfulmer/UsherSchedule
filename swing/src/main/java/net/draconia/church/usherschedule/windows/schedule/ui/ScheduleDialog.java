package net.draconia.church.usherschedule.windows.schedule.ui;

import net.draconia.ApplicationContextProvider;

import javax.annotation.PostConstruct;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.ui.UsherScheduleMainFrame;
import net.draconia.church.usherschedule.windows.schedule.observers.ScheduleDialogModelListObserver;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDialogModel;

import net.draconia.ui.listdetails.ListDetailsDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDialog extends ListDetailsDialog<Schedule>
{
	private static final long serialVersionUID = 561491306850540449L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	private ScheduleDialogModel mObjDialogModel;
	
	@Autowired
	public ScheduleDialog(final UsherScheduleMainFrame wndOwner)
	{
		super("Schedule", wndOwner, ModalityType.DOCUMENT_MODAL);
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
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
	
	protected ScheduleDialogModel getDialogModel()
	{
		if(mObjDialogModel == null)
			mObjDialogModel = ((ScheduleDialogModel)(getBean(ScheduleDialogModel.class)));
		
		return(mObjDialogModel);
	}
	
	@PostConstruct
	protected void initDialog()
	{
		super.initDialog();
		
		setCentered(true);
	}
	
	@Autowired
	protected void setListDetailsPanel(final ScheduleListDetailsPanel pnlListDetails)
	{
		super.setListDetailsPanel(pnlListDetails);
	}
	
	@Autowired
	protected void setDialogModel(final ScheduleDialogModel objDialogModel)
	{
		if(mObjDialogModel != null)
			mObjDialogModel.deleteObservers();
		
		if(objDialogModel == null)
			mObjDialogModel = ((ScheduleDialogModel)(getBean(ScheduleDialogModel.class)));
		else
			mObjDialogModel = objDialogModel;
		
		mObjDialogModel.addObserver((ScheduleDialogModelListObserver)(getBean(ScheduleDialogModelListObserver.class)));
	}
	
}