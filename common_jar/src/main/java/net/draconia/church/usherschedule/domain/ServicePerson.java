package net.draconia.church.usherschedule.domain;

import java.io.Serializable;

import java.util.Observable;

import org.springframework.stereotype.Component;

@Component
public class ServicePerson extends Observable implements Cloneable, Serializable
{
	private static final long serialVersionUID = -5664408907998589372L;
	
	private Integer miCommunionPosition, miId, miUsherPosition;
	private Person mObjPerson;
	private Service mObjService;
	private String msNotes;
	
	public ServicePerson()
	{ }
	
	public ServicePerson(final Service objService, final Person objPerson, final Integer iCommunionPosition, final Integer iUsherPosition)
	{
		setService(objService);
		setPerson(objPerson);
		setCommunionPosition(iCommunionPosition);
		setUsherPosition(iUsherPosition);
	}
	
	public ServicePerson(final Integer iId, final Service objService, final Person objPerson, final Integer iCommunionPosition, final Integer iUsherPosition, final String sNotes)
	{
		this(objService, objPerson, iCommunionPosition, iUsherPosition);
		
		setId(iId);
		setNotes(sNotes);
	}
	
	public Object clone()
	{
		ServicePerson objClone = new ServicePerson();
		
		objClone.setId(getId());
		objClone.setNotes(getNotes());
		objClone.setPerson((Person)(getPerson().clone()));
		objClone.setService((Service)(getService().clone()));
		objClone.setUsherPosition(getUsherPosition());
		
		return(objClone);
	}
	
	public boolean equals(final Object objOther)
	{
		if(objOther instanceof ServicePerson)
			{
			ServicePerson objServicePerson = ((ServicePerson)(objOther));
			
			if(objServicePerson.getCommunionPosition().equals(getCommunionPosition()))
				if(objServicePerson.getId().equals(getId()))
					if(objServicePerson.getNotes().equals(getNotes()))
						if(objServicePerson.getPerson().equals(getPerson()))
							if(objServicePerson.getService().equals(getService()))
								return(objServicePerson.getUsherPosition().equals(getUsherPosition()));
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
	
	public Integer getCommunionPosition()
	{
		if(miCommunionPosition == null)
			miCommunionPosition = 0;
		
		return(miCommunionPosition);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public String getNotes()
	{
		if(msNotes == null)
			msNotes = "";
		
		return(msNotes);
	}
	
	public Person getPerson()
	{
		return(mObjPerson);
	}
	
	public Service getService()
	{
		return(mObjService);
	}
	
	public Integer getUsherPosition()
	{
		if(miUsherPosition == null)
			miUsherPosition = 0;
		
		return(miUsherPosition);
	}
	
	public void setCommunionPosition(final Integer iCommunionPosition)
	{
		if(iCommunionPosition == null)
			miCommunionPosition = 0;
		else
			miCommunionPosition = iCommunionPosition;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public void setNotes(final String sNotes)
	{
		msNotes = sNotes;
		
		setChanged();
		notifyObservers();
	}
	
	public void setPerson(final Person objPerson)
	{
		mObjPerson = objPerson;
		
		setChanged();
		notifyObservers();
	}
	
	public void setService(final Service objService)
	{
		mObjService = objService;
		
		setChanged();
		notifyObservers();
	}
	
	public void setUsherPosition(final Integer iUsherPosition)
	{
		if(iUsherPosition == null)
			miUsherPosition = 0;
		else
			miUsherPosition = iUsherPosition;
		
		setChanged();
		notifyObservers();
	}
}