package com.psms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CP {
	static Connection con;

	public static Connection createC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/psms","root","Boomer#5678");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return con;
	}
}
