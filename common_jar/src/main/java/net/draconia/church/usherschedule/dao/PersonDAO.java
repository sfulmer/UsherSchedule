package net.draconia.church.usherschedule.dao;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.church.usherschedule.domain.Person;
import net.draconia.church.usherschedule.domain.Service;

public interface PersonDAO extends Serializable
{
	public Person getById(final int iId) throws SQLException;
	public List<Person> listPersonsInService(final Service objService) throws SQLException;
	public void remove(final Person objPerson) throws SQLException;
	public Person save(final Person objPerson) throws SQLException;
}