package by.epam.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private static final String DAO_USER_SELECT_PASSWORD = "SELECT password FROM users WHERE id = ?;";
	private static final String DAO_USER_UPDATE_NEW_PASSWORD = "UPDATE users SET password = ? WHERE id = ?;";
	private static final String DAO_USER_CHECK_EMAIL = "SELECT id FROM users " + "WHERE email =? AND id !=?;";
	private static final String DAO_USER_UPDATE_PERSONAL_DATA = "UPDATE users SET firstname = ?, lastname =?, email=?, phone_number=?"
			+ " WHERE id = ?;";
	private static final String DAO_USER_SELECT_USER_BY_ID = "SELECT * FROM users " + "WHERE id =?;";

	private WrapperConnection connection = null;


	@Override
	public boolean registration(String firstname, String lastname, String email, String password, String phoneNumber) throws DAOException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_USER_FIND_EMAIL);

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			} else {
				ps.close();
				ps = connection.prepareStatement(DAO_USER_INSERT_INTO_DB);
				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, email);
				ps.setString(4, password);
				ps.setString(5, phoneNumber);
			}
			return true;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
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
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		User user = null;

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_USER_SELECT_USER);

			ps.setString(1, email);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
			return user;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public String getPassword(int id) throws DAOException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String password = null;
		try {

			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_USER_SELECT_PASSWORD);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				password = rs.getString(1);
			}
			return password;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
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
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_USER_UPDATE_NEW_PASSWORD);

			ps.setString(1, newPassword);
			ps.setInt(2, id);

			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
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
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_USER_CHECK_EMAIL);

			ps.setString(1, email);
			ps.setInt(2, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
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
		
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_USER_UPDATE_PERSONAL_DATA);

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setInt(5, id);

			int i = ps.executeUpdate();
			
			if (i > 0) {
				return true;
			}else {
				return false;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
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
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_USER_SELECT_USER_BY_ID);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
			return user;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}
}