package net.draconia.church.usherschedule.dao;

import java.util.List;

import net.draconia.church.usherschedule.domain.Day;

import net.draconia.church.usherschedule.domain.Schedule;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("dayDAO")
public class DayDAOImpl implements DayDAO
{
	private static final long serialVersionUID = 493871618220323316L;
	
	@Autowired
	private SessionFactory mObjSessionFactory;
	
	public Day getById(final int iId)
	{
		Day objDay;
		Session objSession = getSessionFactory().openSession();
		
		objDay = ((Day)(objSession.get(Day.class, iId)));
		
		objSession.close();
		
		return(objDay);
	}
	
	protected SessionFactory getSessionFactory()
	{
		return(mObjSessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Day> listDaysInSchedule(final Schedule objSchedule)
	{
		List<Day> lstDays;
		Query objQuery;
		Session objSession = getSessionFactory().openSession();
		
		objQuery = objSession.createQuery("from Day where schedule = :schedule");
		
		objQuery.setParameter("schedule", objSchedule);
		
		lstDays = objQuery.list();
		
		objSession.close();
		
		return(lstDays);
	}
	
	public void remove(final Day objDay)
	{
		Session objSession = getSessionFactory().openSession();
		Transaction objTransaction = objSession.beginTransaction();
		
		objSession.delete(objDay);
		
		objTransaction.commit();
		objSession.close();
	}
	
	public Day save(final Day objDay)
	{
		Session objSession = getSessionFactory().openSession();
		
		objSession.beginTransaction();
		
		objSession.saveOrUpdate(objDay);
		
		objSession.getTransaction().commit();
		
		return(objDay);
	}
}