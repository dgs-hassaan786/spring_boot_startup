package io.hassaan.configs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationManager {

	private static final ConfigurationManager _instance = new ConfigurationManager();

	public String MSSQLConnectionString;
	public String MSSQLUsername;
	public String MSSQLPassword;

	private ConfigurationManager() {
		try {
			getPropValues();
		} catch (Exception e) {
		}
	}

	public static ConfigurationManager Instance() {
		return _instance;
	}

	private void getPropValues() throws IOException {
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			MSSQLConnectionString = prop.getProperty("mssql.connectionString");
			MSSQLUsername = prop.getProperty("mssql.username");
			MSSQLPassword = prop.getProperty("mssql.password");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}

	}

}
