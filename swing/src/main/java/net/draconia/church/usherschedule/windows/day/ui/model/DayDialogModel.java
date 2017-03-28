package net.draconia.church.usherschedule.windows.day.ui.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.service.DayService;
import net.draconia.ui.listdetails.model.DialogModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DayDialogModel extends DialogModel<Day> implements Serializable
{
	private static final long serialVersionUID = 1151192860531832247L;
	
	@Autowired
	private DayService mObjDayService;
	
	private DayListDetailPanelModel mObjListDetailPanelModel;
	
	public DayDialogModel()
	{
		super();
	}
	
	public DayDialogModel(final Schedule objCurrentSchedule)
	{
		super();
		
		setCurrentSchedule(objCurrentSchedule);
	}
	
	public Day create()
	{
		return(new Day(getCurrentSchedule()));
	}
	
	public Schedule getCurrentSchedule()
	{
		return(getListDetailPanel().getCurrentSchedule());
	}
	
	protected DayService getDayService()
	{
		return(mObjDayService);
	}
	
	public List<Day> getDaysForSchedule()
	{
		return(getCurrentSchedule().getDays());
	}
	
	public DayListDetailPanelModel getListDetailPanel()
	{
		return(mObjListDetailPanelModel);
	}
	
	protected List<Day> listDAO()
	{
		return(getCurrentSchedule().getDays());
	}
	
	protected void removeDAO(final Day objDay) throws SQLException
	{
		getDayService().remove(objDay);
	}
	
	protected void saveDAO(final Day objDay) throws SQLException
	{
		getDayService().save(objDay);
	}
	
	public void setCurrentSchedule(final Schedule objCurrentSchedule)
	{
		getListDetailPanel().setCurrentSchedule(objCurrentSchedule);
	}
	
	@Autowired
	protected void setListDetailPanelModel(final DayListDetailPanelModel objListDetailPanelModel)
	{
		mObjListDetailPanelModel = objListDetailPanelModel;
	}
}