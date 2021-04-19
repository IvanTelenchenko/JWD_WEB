package by.epam.web.dao.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
	
	private static final Properties PROPERTIES = new Properties();
	
	static {
		loadProperties();
	}
	
	private PropertiesUtil() {
	}
	
	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}

	private static void loadProperties() {
		try(InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")){
			PROPERTIES.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
//		PropertiesUtil.class
		
	}

	 
}
