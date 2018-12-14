package io.hassaan.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqlDbHelper {
	private String connectionString;
	private String username;
	private String password;
	private Connection connection;

	public SqlDbHelper(String connStr, String user, String pass) {
		connectionString = connStr;
		username = user;
		password = pass;
	}

	public void createConnection() throws SQLException {
		connection = DriverManager.getConnection(connectionString, username, password);
	}

	public ResultSet executeQuery(String command) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(command);
		return stmt.executeQuery();
	}

	public ResultSet executeQuery(String command,List<Object> params) throws SQLException {
		PreparedStatement  stmt = connection.prepareStatement(command);
		int index = 1;
		for (Object obj : params) {			
			stmt.setObject(index++, obj);
		}
		return stmt.executeQuery();
	}

	public void closeConnection() throws SQLException {
		if (!connection.isClosed())
			connection.close();
	}

}
