package net.draconia.church.usherschedule.domain;

import java.io.Serializable;

import java.util.Observable;

import org.springframework.stereotype.Component;

@Component
public class Model extends Observable implements Serializable
{
	private static final long serialVersionUID = 3305423744998883321L;
	
	private Schedule mObjSchedule;
	
	public Model()
	{ }
	
	public Schedule getSchedule()
	{
		return(mObjSchedule);
	}
	
	public void setSchedule(final Schedule objSchedule)
	{
		mObjSchedule = objSchedule;
		
		setChanged();
		notifyObservers();
	}
}