package de.yetanothercalendar.model.dao;

import de.yetanothercalendar.model.database.User;

public interface UserDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yetanothercalendar.model.dao.impl.EventDAO#createUserTable()
	 */
	public abstract void createUserTable();

	public abstract User createUser(User user);

	public abstract User isUserDataCorrect(String email, String password);

}