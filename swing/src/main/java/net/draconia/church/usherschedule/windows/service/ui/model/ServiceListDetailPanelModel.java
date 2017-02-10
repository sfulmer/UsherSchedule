package net.draconia.church.usherschedule.windows.service.ui.model;

import java.io.Serializable;

import java.util.Observable;

import net.draconia.church.usherschedule.domain.Day;

import org.springframework.stereotype.Component;

@Component
public class ServiceListDetailPanelModel extends Observable implements Serializable
{
	private static final long serialVersionUID = 2043098445953824433L;
	
	private Day mObjDay;
	
	public Day getCurrentDay()
	{
		return(mObjDay);
	}
	
	public void setCurrentDay(final Day objDay)
	{
		mObjDay = objDay;
		
		setChanged();
		notifyObservers();
	}
}