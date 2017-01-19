package net.draconia.church.usherschedule.dao;

import java.io.Serializable;

import java.util.List;

import net.draconia.church.usherschedule.domain.Schedule;

public interface ScheduleDAO extends Serializable
{
	public Schedule getById(final int iId);
	public List<Schedule> list();
	public void remove(final Schedule objSchedule);
	public Schedule save(final Schedule objSchedule);
}