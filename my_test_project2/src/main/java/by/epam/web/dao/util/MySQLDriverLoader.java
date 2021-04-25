package by.epam.web.dao.util;

import org.apache.log4j.Logger;

/**
 * This class loads the MySQL driver
 * */

public final class MySQLDriverLoader {
	
	
	private static final Logger log = Logger.getLogger(MySQLDriverLoader.class);
				
	private static final MySQLDriverLoader instance = new MySQLDriverLoader();
	
	private MySQLDriverLoader() {}
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			log.info("The driver JBDC has been loaded");
		} catch (ClassNotFoundException e) {
			log.error("The driver JDBC hasn't been loaded");
			throw new DBDriverLoaderException(e);
		}
	}
	
	/**
	 * This method represents an instance of {@link MySQLDriverLoader}
	 * */
	
	public static MySQLDriverLoader getInstance() {
		return instance;
	}
}