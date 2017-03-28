package net.draconia.church.usherschedule.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.Observable;

public class Person extends Observable implements Cloneable, Serializable
{
	private static final long serialVersionUID = -8342248505188564260L;
	
	private Date mDtEnd, mDtOrdenation, mDtStart;
	private Integer miId;
	private Service mObjRequestedService;
	private String msFirstName, msLastName;
	
	public Person()
	{ }
	
	public Person(final String sFirstName, final String sLastName)
	{
		setFirstName(sFirstName);
		setLastName(sLastName);
	}
	
	public Object clone()
	{
		Person objClone = new Person();
		
		objClone.setEndDate(getEndDate());
		objClone.setFirstName(getFirstName());
		objClone.setId(getId());
		objClone.setLastName(getLastName());
		objClone.setRequestedService(getRequestedService());
		objClone.setStartDate(getStartDate());
		
		return(objClone);
	}
	
	public boolean equals(final Object objOther)
	{
		if(objOther instanceof Person)
			{
			Person objPerson = ((Person)(objOther));
			
			if(objPerson.getEndDate().equals(getEndDate()))
				if(objPerson.getFirstName().equals(getFirstName()))
					if(objPerson.getId().equals(getId()))
						if(objPerson.getLastName().equals(getLastName()))
							if(objPerson.getRequestedService().equals(getRequestedService()))
								return(objPerson.getStartDate().equals(getStartDate()));
							else
								return(false);
						else
							return(false);
					else
						return(false);
				else
					return(false);
			else
				return(false);
			}
		else
			return(false);
	}
	
	public Date getEndDate()
	{
		return(mDtEnd);
	}
	
	public String getFirstName()
	{
		if(msFirstName == null)
			msFirstName = "";
		
		return(msFirstName);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public String getLastName()
	{
		if(msLastName == null)
			msLastName = "";
		
		return(msLastName);
	}
	
	public Date getOrdenationDate()
	{
		return(mDtOrdenation);
	}
	
	public Service getRequestedService()
	{
		return(mObjRequestedService);
	}
	
	public Date getStartDate()
	{
		return(mDtStart);
	}
	
	public void setEndDate(final Date dtEnd)
	{
		mDtEnd = dtEnd;
	}
	
	public void setFirstName(final String sFirstName)
	{
		if(sFirstName == null)
			msFirstName = "";
		else
			msFirstName = sFirstName;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
	}
	
	public void setLastName(final String sLastName)
	{
		if(sLastName == null)
			msLastName = "";
		else
			msLastName = sLastName;
		
		setChanged();
		notifyObservers();
	}
	
	public void setOrdenationDate(final Date dtOrdenation)
	{
		mDtOrdenation = dtOrdenation;
	}
	
	public void setRequestedService(final Service objService)
	{
		mObjRequestedService = objService;
		
		setChanged();
		notifyObservers();
	}
	
	public void setStartDate(final Date dtStart)
	{
		mDtStart = dtStart;
	}
}