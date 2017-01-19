package net.draconia.church.usherschedule.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Person extends Observable implements Cloneable, Serializable
{
	private static final long serialVersionUID = -8342248505188564260L;
	
	@Column(columnDefinition="integer", insertable=true, name="EndDate", nullable=true, updatable=true)
	private Date mDtEnd;
	
	@Column(columnDefinition="varchar", insertable=true, length=200, name="FirstName", nullable=false, unique=false, updatable=true)
	private String msFirstName;
	
	@Column(columnDefinition="integer", name="Id", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer miId;
	
	@Column(columnDefinition="varchar", insertable=true, length=200, name="LastName", nullable=false, unique=false, updatable=true)
	private String msLastName;
	
	@Column(columnDefinition="integer", insertable=true, name="OrdenationDate", nullable=true, updatable=true)
	private Date mDtOrdenation;
	
	@Column(columnDefinition="integer", insertable=true, name="Individual", nullable=false, unique=false, updatable=true)
	private Service mObjRequestedService;
	
	@Column(columnDefinition="integer", insertable=true, name="StartDate", nullable=true, updatable=true)
	private Date mDtStart;
	
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