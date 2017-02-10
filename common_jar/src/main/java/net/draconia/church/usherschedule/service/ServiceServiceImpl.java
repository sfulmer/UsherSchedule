package net.draconia.church.usherschedule.service;

import java.util.List;

import net.draconia.church.usherschedule.dao.ServiceDAO;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service("serviceService")
public class ServiceServiceImpl implements ServiceService
{
	private static final long serialVersionUID = 6747027696942745072L;
	
	@Autowired
	private ServiceDAO mObjServiceDAO;
	
	protected ServiceDAO getServiceDAO()
	{
		return(mObjServiceDAO);
	}
	
	public Service getById(int iId)
	{
		return(getServiceDAO().getById(iId));
	}
	
	public List<Service> listServicesInDay(final Day objDay)
	{
		return(getServiceDAO().listServicesInDay(objDay));
	}
	
	public void remove(final Service objService)
	{
		getServiceDAO().remove(objService);
	}
	
	public Service save(final Service objService)
	{
		return(getServiceDAO().save(objService));
	}
}