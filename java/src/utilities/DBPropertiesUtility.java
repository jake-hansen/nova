package utilities;

import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DBPropertiesUtility {
	static Properties prop = new Properties();

	public static void loadProperty() throws Exception {
		InputStream inputStream = DBPropertiesUtility.class.getResourceAsStream("config.properties.private");
		
		if (inputStream == null) {
			throw new FileNotFoundException();
		}
		
		prop.load(inputStream);
	}

	public static String getProp(String key) {
		return prop.getProperty(key).trim();
	}
}
