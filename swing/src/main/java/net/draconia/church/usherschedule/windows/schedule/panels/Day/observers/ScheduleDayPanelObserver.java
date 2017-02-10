package net.draconia.church.usherschedule.windows.schedule.panels.Day.observers;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.windows.schedule.panels.Day.models.ScheduleDayListTableModel;
import net.draconia.church.usherschedule.windows.schedule.ui.ScheduleDayPanel;
import net.draconia.ui.subcomponent.SubComponentPanelModel;
import net.draconia.ui.subcomponent.SubComponentPanelObserver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ScheduleDayPanelObserver extends SubComponentPanelObserver<Schedule, Day>
{
	private static final long serialVersionUID = 823037245953386533L;
	
	@Autowired
	public ScheduleDayPanelObserver(final ScheduleDayPanel pnlScheduleDay)
	{
		super(pnlScheduleDay);
	}
	
	protected ScheduleDayListTableModel getListTableModel(final Object objPanel) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Method funcGetListTableModel = getPanel().getClass().getDeclaredMethod("getListTableModel", new Class<?>[0]);
		
		if(!funcGetListTableModel.isAccessible())
			funcGetListTableModel.setAccessible(true);
		
		return((ScheduleDayListTableModel)(funcGetListTableModel.invoke(getPanel(), new Object[0])));
	}
	
	protected void setTableModelModel(final Schedule objModel) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Method funcSetModel = null;
		Method[] arrMethods;
		ScheduleDayListTableModel objTableModel;
		
		objTableModel = getListTableModel(getPanel());
		arrMethods = objTableModel.getClass().getSuperclass().getDeclaredMethods();
		
		for(Method funcMethod : arrMethods)
			if(funcMethod.getName().equalsIgnoreCase("setModel"))
				{
				funcSetModel = funcMethod;
				break;
				}
		
		if(!funcSetModel.isAccessible())
			funcSetModel.setAccessible(true);
		
		funcSetModel.invoke(objTableModel, new Object[] {objModel});
	}
	
	@SuppressWarnings("unchecked")
	public void update(final Observable objObservable, final Object objArgument)
	{
		SubComponentPanelModel<Schedule> objPanelModel = ((SubComponentPanelModel<Schedule>)(objObservable));
		
		try
			{
			setTableModelModel(objPanelModel.getModel());
			}
		catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException objException)
			{
			objException.printStackTrace(System.err);
			}
	}
}