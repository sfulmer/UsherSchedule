package net.draconia.church.usherschedule.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;

public interface DayService extends Serializable
{
	public Day getById(final int iId) throws SQLException;
	public List<Day> listDaysInSchedule(final Schedule objSchedule) throws SQLException;
	public void remove(final Day objDay) throws SQLException;
	public Day save(final Day objDay) throws SQLException;
}