package net.draconia.church.usherschedule.windows.day.panels.Service.actions;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JTable;

import net.draconia.church.usherschedule.domain.Service;

import net.draconia.church.usherschedule.service.ServiceService;
import net.draconia.church.usherschedule.windows.service.ui.model.DayServiceListTableModel;
import net.draconia.ui.subcomponent.actions.Remove;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DayServiceRemove extends Remove<Service>
{
	private static final long serialVersionUID = 1706270023306512670L;
	
	@Autowired
	private ServiceService mObjService;
	
	public DayServiceRemove()
	{ }
	
	public DayServiceRemove(final JTable tblService)
	{
		super(tblService);
	}
	
	protected Service getSelectedService() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		DayServiceListTableModel objTableModel;
		JTable tbl = getListTable();
		
		objTableModel = ((DayServiceListTableModel)(tbl.getModel()));
		
		return(objTableModel.getRows().get(tbl.getSelectedRow()));
	}
	
	protected ServiceService getService()
	{
		return(mObjService);
	}

	protected void removeSubcomponent() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException
	{
		getService().remove(getSelectedService());
	}
}