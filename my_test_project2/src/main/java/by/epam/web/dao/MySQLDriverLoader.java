package by.epam.web.dao;

import org.apache.log4j.Logger;

public class MySQLDriverLoader {
	
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
		
	public static MySQLDriverLoader getInstance() {
		return instance;
	}
}