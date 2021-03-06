package net.draconia.church.usherschedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Day extends Observable implements Cloneable, Serializable
{
	private static final long serialVersionUID = -6846377352600685777L;
	
	private Date mDtDate;
	private Integer miId;
	private List<Service> mLstServices;
	
	@JsonIgnore
	private Schedule mObjSchedule;
	
	public Day()
	{ }
	
	public Day(final Schedule objSchedule)
	{
		setSchedule(objSchedule);
	}
	
	public boolean addService(final Service objService)
	{
		boolean bReturnValue = getServicesInternal().add(objService);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void clearServices()
	{
		getServicesInternal().clear();
		
		setChanged();
		notifyObservers();
	}
	
	public Object clone()
	{
		Day objClone = new Day(getSchedule());
		
		if(getId() == null)
			objClone.setId(null);
		else
			objClone.setId(Integer.valueOf(getId()));
		if(getDate() == null)
			objClone.setDate(null);
		else
			objClone.setDate((Date)(getDate().clone()));
		
		for(Service objService : getServices())
			objClone.addService((Service)(objService.clone()));
		
		return(objClone);
	}
	
	public boolean equals(final Object objOther)
	{
		if(objOther instanceof Day)
			{
			Day objDay = ((Day)(objOther));
			
			if(((objDay.getDate() == null) && (getDate() == null)) || (objDay.getDate().equals(getDate())))
				if(((objDay.getId() == null) && (getId() == null)) || (objDay.getId().equals(getId())))
					return(objDay.getServices().equals(getServices()));
				else
					return(false);
			else
				return(false);
			}
		else
			return(false);
	}
	
	public Date getDate()
	{
		return(mDtDate);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public Schedule getSchedule()
	{
		return(mObjSchedule);
	}
	
	public List<Service> getServices()
	{
		return(Collections.unmodifiableList(getServicesInternal()));
	}
	
	protected List<Service> getServicesInternal()
	{
		if(mLstServices == null)
			mLstServices = Collections.synchronizedList(new ArrayList<Service>());
		
		return(mLstServices);
	}
	
	public boolean removeService(final Service objService)
	{
		boolean bReturnValue = getServicesInternal().remove(objService);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void setDate(final Date dtDate)
	{
		mDtDate = dtDate;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public void setSchedule(final Schedule objSchedule)
	{
		mObjSchedule = objSchedule;
	}
}