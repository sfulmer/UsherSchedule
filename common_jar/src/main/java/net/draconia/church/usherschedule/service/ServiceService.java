package net.draconia.church.usherschedule.service;

import java.io.Serializable;

import java.util.List;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Service;

public interface ServiceService extends Serializable
{
	public Service getById(final int iId);
	public List<Service> listServicesInDay(final Day objDay);
	public void remove(final Service objService);
	public Service save(final Service objService);
}