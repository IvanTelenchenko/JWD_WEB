package by.epam.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.web.dao.DAOException;
import by.epam.web.dao.DAOUser;
import by.epam.web.dao.util.ConnectionPool;
import by.epam.web.dao.util.MySQLDriverLoader;
import by.epam.web.dao.util.WrapperConnection;
import by.epam.web.entity.User;

public class DAOUserImpl implements DAOUser {

	private static final Logger log = Logger.getLogger(DAOUserImpl.class);

	static {
		MySQLDriverLoader.getInstance();
	}

	private static final String DAO_USER_FIND_EMAIL = "SELECT id FROM users " + "WHERE email =?;";
	private static final String DAO_USER_SELECT_USER = "SELECT * FROM users " + "WHERE email =? AND password =? ;";

	private static final String DAO_USER_INSERT_INTO_DB = "INSERT INTO users(firstname,lastname,email,password,phone_number)"
			+ "VALUES(?,?,?,?,?);";
//	private static final String DAO_USER_SELECT_ALL_USERS = "SELECT email, password FROM users;";

	private static final String DAO_USER_SELECT_PASSWORD = "SELECT password FROM users WHERE id = ?;";

	private static final String DAO_USER_UPDATE_NEW_PASSWORD = "UPDATE users SET password = ? WHERE id = ?;";

	private static final String DAO_USER_CHECK_EMAIL = "SELECT id FROM users " + "WHERE email =? AND id !=?;";

	private static final String DAO_USER_UPDATE_PERSONAL_DATA = "UPDATE users SET firstname = ?, lastname =?, email=?, phone_number=?"
			+ " WHERE id = ?;";
	
	private static final String DAO_USER_SELECT_USER_BY_ID = "SELECT * FROM users " + "WHERE id =?;";
	/*
	 * UPDATE имя_таблицы SET столбец1 = значение1, столбец2 = значение2, ...
	 * столбецN = значениеN [WHERE условие_обновления]
	 */

	private WrapperConnection connection = null;

//	PreparedStatement ps = null;
//	ResultSet rs = null;

	@Override
	public boolean registration(String firstname, String lastname, String email, String password, String phoneNumber) throws DAOException {

		try {
			System.out.println("DAOUser START");
			connection = ConnectionPool.getConnection();

//		String sqlFindUser = "SELECT id FROM users "
//				+ "WHERE email =? AND password =? ;";

			PreparedStatement ps = connection.prepareStatement(DAO_USER_FIND_EMAIL);

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			} else {
//			String sqlInsertIntoDB = "INSERT INTO users(email,password)"
//					+ "VALUES(?,?);";
				ps = connection.prepareStatement(DAO_USER_INSERT_INTO_DB);
				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, email);
				ps.setString(4, password);
				ps.setString(5, phoneNumber);

//				int i = ps.executeUpdate();
			}

			return true;

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public User authorization(String email, String password) throws DAOException {

		User user = null;

		try {
			connection = ConnectionPool.getConnection();

			PreparedStatement ps = connection.prepareStatement(DAO_USER_SELECT_USER);

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
			return user;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}

//	@Override
//	public List<User> getAllUsers() throws DAOException {
//
//		List<User> list = new ArrayList<User>();
//
//		try {
//			connection = ConnectionPool.getConnection();
//
//			Statement st = connection.createStatement();
//			ResultSet rs = st.executeQuery(DAO_USER_SELECT_ALL_USERS);
//
//			while (rs.next()) {
//				list.add(new User(rs.getString(1), rs.getString(2)));
//			}
//			return list;
//		} catch (SQLException e) {
//			log.error(e);
//			throw new DAOException(e);
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				log.error(e);
//				throw new DAOException(e);
//			}
//		}
//	}

	@Override
	public String getPassword(int id) throws DAOException {

		String password = null;
		System.out.println("getPassword!");
		try {

			connection = ConnectionPool.getConnection();

			PreparedStatement ps = connection.prepareStatement(DAO_USER_SELECT_PASSWORD);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				password = rs.getString(1);
			}
			return password;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public boolean changePassword(String newPassword, int id) throws DAOException {

		try {
			connection = ConnectionPool.getConnection();

			PreparedStatement ps = connection.prepareStatement(DAO_USER_UPDATE_NEW_PASSWORD);

			ps.setString(1, newPassword);
			ps.setInt(2, id);

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println(i);
				return true;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}

		return false;
	}

	@Override
	public boolean checkEmail(String email, int id) throws DAOException {

		try {
			connection = ConnectionPool.getConnection();

			PreparedStatement ps = connection.prepareStatement(DAO_USER_CHECK_EMAIL);

			ps.setString(1, email);
			ps.setInt(2, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public boolean updatePD(String firstname, String lastname, String email, String phone, int id) throws DAOException {

		try {
			connection = ConnectionPool.getConnection();

			PreparedStatement ps = connection.prepareStatement(DAO_USER_UPDATE_PERSONAL_DATA);

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setInt(5, id);

			int i = ps.executeUpdate();
			
			System.out.println("i > " + i);
			if (i > 0) {
				System.out.println(i);
				return true;
			}else {
				return false;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public User getUser(int id) throws DAOException {
		User user = null;

		try {
			connection = ConnectionPool.getConnection();

			PreparedStatement ps = connection.prepareStatement(DAO_USER_SELECT_USER_BY_ID);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			
//			public User(int id, String firstname, String lastname,  String email, String password, String phoneNumber, int role) 
			
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
			return user;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}
}