package by.epam.web.service;

import java.util.List;

import by.epam.web.entity.User;

public interface ServiceUser {
	
	User authorization(String email, String password) throws ServiceException;
	boolean registration(String firstname, String lastname, String email, String password, String phoneNumber) throws ServiceException;
//	String getPassword(int id);
	boolean changePassword(String newPassword, String oldPassword, String newPasswordRepeat, int id) throws ServiceException;
//	boolean checkEmail(String email, int id);
	boolean updatePD(String firstname, String lastname, String email, String phone, int id) throws ServiceException;
	User getUser(int id) throws ServiceException;
	List<String> getDefaultDate();
	List<String> getUserDate(String dateStart, String dateEnd);
	
	
}
