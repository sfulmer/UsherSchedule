package net.draconia.church.usherschedule.service;

import java.io.Serializable;

import java.util.List;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

public interface DayService extends Serializable
{
	public Day getById(final int iId);
	public List<Day> listDaysInSchedule(final Schedule objSchedule);
	public void remove(final Day objDay);
	public Day save(final Day objDay);
}