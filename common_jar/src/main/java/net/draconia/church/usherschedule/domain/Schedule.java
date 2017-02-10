package net.draconia.church.usherschedule.domain;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Schedule")
public class Schedule extends Observable implements Cloneable, Serializable
{
	private static final long serialVersionUID = 3305423744998883321L;
	
	@Column(columnDefinition="integer", name="Id", nullable=false, unique=true)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer miId;
	
	@Column(columnDefinition="varchar", insertable=true, length=150, name="ScheduleName", nullable=false, unique=false, updatable=true)
	private String msName;
	
	@OneToMany(mappedBy="mObjSchedule")
	private List<Day> mLstDays;
	
	public Schedule()
	{ }
	
	public boolean addDay(final Day objDay)
	{
		boolean bReturnValue = getDaysInternal().add(objDay);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void clearDays()
	{
		getDaysInternal().clear();
	}
	
	public Object clone()
	{
		Schedule objClone = new Schedule();
		
		objClone.setId(getId());
		objClone.setName(getName());
		
		for(Day objDay : getDays())
			objClone.addDay((Day)(objDay.clone()));
		
		return(objClone);
	}
	
	public boolean equals(final Object objOther)
	{
		if(objOther instanceof Schedule)
			{
			Schedule objSchedule = ((Schedule)(objOther));
			
			if(((getId() != null) && (getId().equals(objSchedule.getId()))) || ((getId() == null) && (objSchedule.getId() == null)))
				if(getName().equals(objSchedule.getName()))
					if(getDays().size() == objSchedule.getDays().size())
						{
						for(int iLength = getDays().size(), iLoop = 0; iLoop < iLength; iLoop++)
							if(!getDays().get(iLoop).equals(objSchedule.getDays().get(iLoop)))
								return(false);
						
						return(true);
						}
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
	
	public List<Day> getDays()
	{
		return(Collections.unmodifiableList(getDaysInternal()));
	}
	
	protected List<Day> getDaysInternal()
	{
		if(mLstDays == null)
			mLstDays = Collections.synchronizedList(new ArrayList<Day>());
		
		return(mLstDays);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public String getName()
	{
		if(msName == null)
			msName = "";
		
		return(msName);
	}
	
	public boolean removeDay(final Day objDay)
	{
		boolean bReturnValue = getDaysInternal().remove(objDay);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void setDays(final List<Day> lstDays)
	{
		getDaysInternal().clear();
		getDaysInternal().addAll(lstDays);
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public void setName(final String sName)
	{
		if(sName == null)
			msName = "";
		else
			msName = sName;
		
		setChanged();
		notifyObservers();
	}
	
	public String toString()
	{
		return(getName());
	}
}