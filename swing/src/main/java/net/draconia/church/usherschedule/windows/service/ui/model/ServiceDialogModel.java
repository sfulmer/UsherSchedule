package net.draconia.church.usherschedule.windows.service.ui.model;

import java.sql.SQLException;
import java.util.List;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

import net.draconia.church.usherschedule.service.ServiceService;

import net.draconia.ui.listdetails.model.DialogModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ServiceDialogModel extends DialogModel<Service>
{
	private static final long serialVersionUID = -5279911278590351975L;
	
	@Autowired
	private ServiceListDetailPanelModel mObjListDetailPanelModel;
	
	@Autowired
	private ServiceService mObjServiceService;
	
	public Service create()
	{
		Service objService = new Service(getCurrentDay());
		
		return(objService);
	}
	
	public Day getCurrentDay()
	{
		return(getListDetailModel().getCurrentDay());
	}
	
	protected ServiceListDetailPanelModel getListDetailModel()
	{
		return(mObjListDetailPanelModel);
	}
	
	protected ServiceService getServiceService()
	{
		return(mObjServiceService);
	}
	
	protected List<Service> listDAO() throws SQLException
	{
		return(getServiceService().listServicesInDay(getCurrentDay()));
	}
	
	protected void removeDAO(final Service objService) throws SQLException
	{
		getServiceService().remove(objService);
	}
	
	protected void saveDAO(final Service objService) throws SQLException
	{
		getServiceService().save(objService);
	}
	
	public void setCurrentDay(final Day objDay)
	{
		getListDetailModel().setCurrentDay(objDay);
	}
}