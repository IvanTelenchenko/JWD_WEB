package by.epam.web.service;

import java.util.List;

import by.epam.web.entity.User;

/**
 * This interface represents access to data management. 
 * */
public interface ServiceUser {
	
	/**
	 * This method allows you to log in
	 * @param email User's email address
	 * @param password Password from this account
	 * @return returns {@link User}
	 */
	User authorization(String email, String password) throws ServiceException;
	
	/**
	 * This method allows you to register a new user and create a new account
	 * @param firstname User name
	 * @param lastname User surname
	 * @param email User's email address
	 * @param password Password from this account
	 * @param phoneNumber User number phone number
	 * @return returns true if account has been created else returns false
	 */
	boolean registration(String firstname, String lastname, String email, String password, String phoneNumber) throws ServiceException;
	
	/**
	 * This method allows you to change the password
	 * @param newPassword New password
	 * @param id User ID
	 * @return returns true if password has been changed
	 */
	boolean changePassword(String newPassword, String oldPassword, String newPasswordRepeat, int id) throws ServiceException;
	
	/**
	 * This method allows you to change the personal data
	 * @param firstname User name
	 * @param lastname User surname
	 * @param email User's email address
	 * @param phone User number phone number
	 * @return returns true if personal data has been changed
	 */
	boolean updatePD(String firstname, String lastname, String email, String phone, int id) throws ServiceException;
	
	/**
	 * This method allows you to receive1 the {@link User}
	 * @param firstname User name
	 * @param lastname User surname
	 * @param email User's email address
	 * @param phone User number phone number
	 * @return returns {@link User}
	 */
	User getUser(int id) throws ServiceException;
	
	/**
	 * This method allows you to get the default date list
	 * @return returns default date list
	 */
	List<String> getDefaultDate();
	
	/**
	 * This method allows you to get a list of the user's dates
	 * @return returns a list of the user's dates
	 */
	List<String> getUserDate(String dateStart, String dateEnd);
}
