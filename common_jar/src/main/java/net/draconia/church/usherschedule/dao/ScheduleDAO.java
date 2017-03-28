package net.draconia.church.usherschedule.dao;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.church.usherschedule.domain.Schedule;

public interface ScheduleDAO extends Serializable
{
	public Schedule getById(final int iId) throws SQLException;
	public List<Schedule> list() throws SQLException;
	public void remove(final Schedule objSchedule) throws SQLException;
	public Schedule save(final Schedule objSchedule) throws SQLException;
}