package by.epam.web.dao;

import java.util.List;

import by.epam.web.entity.User;

public interface DAOUser {
	
	boolean registration(String firstname, String lastname, String email, String password, String phoneNumber) throws DAOException;
	User authorization(String email, String password) throws DAOException;
	List<User> getAllUsers() throws DAOException;
	String getPassword(int id) throws DAOException;
	boolean changePassword(String newPassword, int id) throws DAOException;
	boolean checkEmail(String email, int id) throws DAOException;
	boolean updatePD(String firstname, String lastname, String email, String phone, int id) throws DAOException;
	User getUser(int id) throws DAOException;
}
