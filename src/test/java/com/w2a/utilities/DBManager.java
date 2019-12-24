package com.w2a.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	public static Connection con=null; //mysql
	public static Connection conn=null; //sql
	
	//sql server
	public static void setMySqlDbConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(TestConfig.mysqldriver);
			conn=DriverManager.getConnection(TestConfig.mysqlurl,TestConfig.dbUserName, TestConfig.dbPassword);
			if (!conn.isClosed()) {
				System.out.println("Successfully connected to MySQL DB");
			}
		}catch(Exception e) {
			
			System.err.println("Canot connect to database server");
			System.out.println(e);
		}
	}
	

}
