package by.epam.web.dao;

import by.epam.web.entity.User;

/**
 * This interface represents user's access to data management. 
 * */

public interface DAOUser {
	
	/**
	 * This method allows you to register a new user and create a new account
	 * @param firstname User name
	 * @param lastname User surname
	 * @param email User's email address
	 * @param password Password from this account
	 * @param phoneNumber User number phone number
	 * @return returns true if account has been created else returns false
	 */
	
	boolean registration(String firstname, String lastname, String email, String password, String phoneNumber) throws DAOException;
	
	/**
	 * This method allows you to log in
	 * @param email User's email address
	 * @param password Password from this account
	 * @return returns {@link User}
	 */
	User authorization(String email, String password) throws DAOException;
	
	/**
	 * This method allows you to receive password
	 * @param id User ID
	 * @return returns user password
	 */
	String getPassword(int id) throws DAOException;
	
	/**
	 * This method allows you to change the password
	 * @param newPassword New password
	 * @param id User ID
	 * @return returns true if password has been changed
	 */
	boolean changePassword(String newPassword, int id) throws DAOException;
	
	/**
	 * This method allows you to check email address
	 * @param email Email address
	 * @param id User ID
	 * @return returns true if this email address hasn't been found in the database else returns false
	 */
	boolean checkEmail(String email, int id) throws DAOException;
	
	/**
	 * This method allows you to change the personal data
	 * @param firstname User name
	 * @param lastname User surname
	 * @param email User's email address
	 * @param phone User number phone number
	 * @return returns true if personal data has been changed
	 */
	boolean updatePD(String firstname, String lastname, String email, String phone, int id) throws DAOException;
	
	/**
	 * This method allows you to receive1 the {@link User}
	 * @param firstname User name
	 * @param lastname User surname
	 * @param email User's email address
	 * @param phone User number phone number
	 * @return returns {@link User}
	 */
	User getUser(int id) throws DAOException;
}
