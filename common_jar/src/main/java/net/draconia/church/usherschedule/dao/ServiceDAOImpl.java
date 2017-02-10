package net.draconia.church.usherschedule.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

@Repository("serviceDAO")
public class ServiceDAOImpl implements ServiceDAO
{
	private static final long serialVersionUID = 6408268280033682130L;
	
	@Autowired
	private SessionFactory mObjSessionFactory;
	
	public Service getById(final int iId)
	{
		Service objService;
		Session objSession = getSessionFactory().openSession();
		
		objService = ((Service)(objSession.get(Service.class, iId)));
		
		objSession.close();
		
		return(objService);
	}
	
	protected SessionFactory getSessionFactory()
	{
		return(mObjSessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Service> listServicesInDay(final Day objDay)
	{
		List<Service> lstServices;
		Query objQuery;
		Session objSession = getSessionFactory().openSession();
		
		objQuery = objSession.createQuery("from Service where day = :day");
		
		objQuery.setParameter("day", objDay);
		
		lstServices = objQuery.list();
		
		objSession.close();
		
		return(lstServices);
	}
	
	public void remove(final Service objService)
	{
		Session objSession = getSessionFactory().openSession();
		Transaction objTransaction = objSession.beginTransaction();
		
		objSession.delete(objService);
		
		objTransaction.commit();
		objSession.close();
	}
	
	public Service save(final Service objService)
	{
		Session objSession = getSessionFactory().openSession();
		
		objSession.beginTransaction();
		
		objSession.saveOrUpdate(objService);
		
		objSession.getTransaction().commit();
		
		return(objService);
	}
}