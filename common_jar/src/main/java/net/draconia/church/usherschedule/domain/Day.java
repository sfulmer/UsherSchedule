package net.draconia.church.usherschedule.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Day")
public class Day extends Observable implements Cloneable, Serializable
{
	private static final long serialVersionUID = -6846377352600685777L;
	
	@Column(columnDefinition="integer", insertable=true, name="ScheduleDate", nullable=false, updatable=true)
	private Date mDtDate;
	
	@Column(columnDefinition="integer", name="Id", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer miId;
	
	@OneToMany(mappedBy="mObjDay")
	private List<Service> mLstServices;
	
	@Column(name="Schedule")
	@JoinColumn(name="Schedule")
	private Schedule mObjSchedule;
	
	public Day()
	{ }
	
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
		Day objClone = new Day();
		
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
}