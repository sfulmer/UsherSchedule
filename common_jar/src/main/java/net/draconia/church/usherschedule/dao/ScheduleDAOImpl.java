package net.draconia.church.usherschedule.dao;

import java.util.List;

import net.draconia.church.usherschedule.domain.Schedule;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("scheduleDAO")
public class ScheduleDAOImpl implements ScheduleDAO
{
	private static final long serialVersionUID = 8022679655981503817L;
	
	@Autowired
	private SessionFactory mObjSessionFactory;
	
	public ScheduleDAOImpl()
	{ }
	
	public Schedule getById(final int iId)
	{
		Schedule objSchedule;
		Session objSession = getSessionFactory().openSession();
		
		objSchedule = ((Schedule)(objSession.get(Schedule.class, iId)));
		
		objSession.close();
		
		return(objSchedule);
	}
	
	protected SessionFactory getSessionFactory()
	{
		return(mObjSessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Schedule> list()
	{
		List<Schedule> lstSchedules;
		Session objSession = getSessionFactory().openSession();
		
		lstSchedules = objSession.createQuery("from Schedule").list();
		
		objSession.close();
		
		return(lstSchedules);
	}
	
	public void remove(final Schedule objSchedule)
	{
		Session objSession = getSessionFactory().openSession();
		Transaction objTransaction = objSession.beginTransaction();
		
		objSession.delete(objSchedule);
		
		objTransaction.commit();
		objSession.close();
	}
	
	public Schedule save(final Schedule objSchedule)
	{
		Session objSession = getSessionFactory().openSession();
		
		objSession.beginTransaction();
		
		objSession.saveOrUpdate(objSchedule);
		
		objSession.getTransaction().commit();
		
		return(objSchedule);
	}
}