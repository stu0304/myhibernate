package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
// singleton
	private static DbUtil util=null;
	
	private DbUtil()
	{
		
	}
	
	public static DbUtil getDbInstance()
	{
		if(util==null)
		{
			util = new DbUtil();	
		}
		
		return util;
	}
	
	public Connection getConn() throws ClassNotFoundException, SQLException
	{
		final String mysqlDriver="com.mysql.jdbc.Driver";
		Class.forName(mysqlDriver);
		
		final String mysqlURL="jdbc:mysql://localhost:3306/gongda?useUnicode=true&amp;characterEncoding=UTF-8";
		final String userName="root";
		final String passWord="root";
		
		Connection conn = DriverManager.getConnection(mysqlURL,userName, passWord);
		
		return conn;
	}
}
