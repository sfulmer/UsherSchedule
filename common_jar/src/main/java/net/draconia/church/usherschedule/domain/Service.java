package net.draconia.church.usherschedule.domain;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Service")
public class Service extends Observable implements Cloneable, Serializable
{
	private static final long serialVersionUID = 7214970852431246879L;
	
	@Column(columnDefinition="integer", insertable=true, name="Day", nullable=false, unique=false, updatable=true)
	private Day mObjDay;
	
	@Column(columnDefinition="integer", name="Id", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer miId;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ServicePerson", joinColumns=@JoinColumn(name="PersonId"), inverseJoinColumns=@JoinColumn(name="ServiceId"))
	private List<ServicePerson> mLstPerson;
	
	@Column(columnDefinition="varchar", insertable=true, length=150, name="ServiceLabel", nullable=false, unique=false, updatable=true)
	private String msLabel;
	
	public Service()
	{ }
	
	public Service(final Day objDay)
	{
		setDay(objDay);
	}
	
	public boolean addPerson(final Person objPerson, final Integer iCommunionPosition, final Integer iUsherPosition)
	{
		boolean bReturnValue = getPersonsInternal().add(new ServicePerson(this, objPerson, iCommunionPosition, iUsherPosition));
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public boolean addPerson(final ServicePerson objPerson)
	{
		boolean bReturnValue = getPersonsInternal().add(objPerson);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public Object clone()
	{
		Service objClone = new Service();
		
		objClone.setId(getId());
		objClone.setLabel(getLabel());
		
		for(ServicePerson objPerson : getPersons())
			addPerson((ServicePerson)(objPerson.clone()));
		
		return(objClone);
	}
	
	public boolean equals(final Object objOther)
	{
		if(objOther instanceof Service)
			{
			Service objService = ((Service)(objOther));
			
			if(objService.getId().equals(getId()))
				if(objService.getLabel().equals(getLabel()))
					return(objService.getPersons().equals(getPersons()));
				else
					return(false);
			else
				return(false);
			}
		else
			return(false);
	}
	
	public Day getDay()
	{
		return(mObjDay);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public String getLabel()
	{
		return(msLabel);
	}
	
	public List<ServicePerson> getPersons()
	{
		return(Collections.unmodifiableList(getPersonsInternal()));
	}
	
	protected List<ServicePerson> getPersonsInternal()
	{
		if(mLstPerson == null)
			mLstPerson = Collections.synchronizedList(new ArrayList<ServicePerson>());
		
		return(mLstPerson);
	}
	
	public boolean removePerson(final Person objPerson)
	{
		boolean bReturnValue = getPersonsInternal().remove(objPerson);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void setDay(final Day objDay)
	{
		mObjDay = objDay;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public void setLabel(final String sLabel)
	{
		msLabel = sLabel;
		
		setChanged();
		notifyObservers();
	}
}