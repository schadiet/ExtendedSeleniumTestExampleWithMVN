package de.codingsolo.selenium.configuration;

import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static String filename = "myconfig.properties";
	private static Properties properties = loadProperties();
	
	public static String getBaseUrl() {
		String baseUrl = (String) properties.get("baseUrl");
		throwExeptionWhenNull("baseUrl", baseUrl);
		return baseUrl;
	}
	
	public static String getBrowser(String browsername) {
		String browser = (String) properties.get(browsername);
		throwExeptionWhenNull(browsername, browser);
		return browser;
	}
	
	public static String getBrowserDriver(String drivername) {
		String driver = (String) properties.get(drivername);
		throwExeptionWhenNull(drivername, driver);
		return driver;
	}

	private static void throwExeptionWhenNull(String prop, String param) {
		if (param ==null) {
			throw new RuntimeException("Parameter: "+prop+" nicht in der Config-Datei gefunden.");
		}
	}
	
	private static Properties loadProperties() {

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			Properties props = new Properties();
			InputStream is = loader.getSystemResourceAsStream(filename);
			props.load(is);

			return props;

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Keine Konfigurationsdatei gefudnen", e);
		}
	}

}
