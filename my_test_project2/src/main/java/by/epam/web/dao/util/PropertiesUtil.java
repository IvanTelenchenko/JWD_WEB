package by.epam.web.dao.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * This class is a loading of properties from a file
 * */
public final class PropertiesUtil {
	
	private static final Logger log = Logger.getLogger(PropertiesUtil.class);
	private static final Properties PROPERTIES = new Properties();
	
	static {
		loadProperties();
	}
	
	private PropertiesUtil() {
	}
	
	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}
	
	/**
	 * This method is a loading properties from a file
	 * */
	private static void loadProperties() {
		try(InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")){
			PROPERTIES.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
