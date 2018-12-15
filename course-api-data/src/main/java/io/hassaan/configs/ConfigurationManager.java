package io.hassaan.configs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class ConfigurationManager {

	private static final ConfigurationManager _instance = new ConfigurationManager();

	public String MSSQLConnectionString;
	public String MSSQLUsername;
	public String MSSQLPassword;
	public List<String> CorsDomains;
	public List<String> CorsHeaders;
	public List<String> CorsMethods;
	public List<String> CorsExposedHeaders;
	public boolean CorsAllowCredentials;
	public long CorsMaxAge;

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
			CorsDomains = new ArrayList<String>(Arrays.asList(prop.getProperty("cors.domains").split(",")));
			CorsHeaders = new ArrayList<String>(Arrays.asList(prop.getProperty("cors.headers").split(",")));
			CorsMethods = new ArrayList<String>(Arrays.asList(prop.getProperty("cors.methods").split(",")));
			CorsExposedHeaders = new ArrayList<String>(Arrays.asList(prop.getProperty("cors.exposedHeaders").split(",")));
			CorsAllowCredentials = Boolean.parseBoolean(prop.getProperty("cors.allowCredentials", "false"));
			CorsMaxAge = Long.parseLong(prop.getProperty("cors.maxAge", "3600"));
			

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}

	}

}
