package by.epam.web.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
	
	private static final String URL_KEY = "db.url";
	private static final String PASSWORD_KEY = "db.password";
	private static final String USERNAME_KEY = "db.user";
	
	private ConnectionManager(){}
	
	public static Connection openConnection() {
		try {
			return DriverManager.getConnection(
					PropertiesUtil.get(URL_KEY),
					PropertiesUtil.get(USERNAME_KEY),
					PropertiesUtil.get(PASSWORD_KEY)
					);
		} catch (SQLException e) {
			// TODO add MyException
			throw new RuntimeException(e);
		}
	}
}
