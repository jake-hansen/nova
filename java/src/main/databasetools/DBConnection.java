package main.databasetools;

import java.sql.Connection;
import java.sql.DriverManager;

import main.utilities.DBPropertiesUtility;

public class DBConnection {
	public static Connection connection = null;

	public static void connect() {
		// Check to see if MySQL JDBC is present
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			System.out.println("Could not find MySQL JDBC Driver.");
		}

		System.out.println("MySQL JDBC driver registered.");

		try {
			DBPropertiesUtility.loadProperty();
			connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
		}
		catch (Exception e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
		}
		
		if (connection != null) {
			System.out.println("Database connection succeeded.");
		}
	}

	static String getURL() {
		String url = DBPropertiesUtility.getProp("url");
		return url;
	}

	static String getUserName() {
		String usr = DBPropertiesUtility.getProp("user");
		return usr;
	}

	static String getPassword() {
		String pwd = DBPropertiesUtility.getProp("password");
		return pwd;
	}
}
