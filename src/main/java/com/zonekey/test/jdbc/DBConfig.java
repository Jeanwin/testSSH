package com.zonekey.test.jdbc;

import java.io.IOException;
import java.util.Properties;

public class DBConfig {
	private static Properties prop = new Properties();
	static {
		try {
			// 加载dbconfig.properties配置文件
			prop.load(DBConfig.class.getResourceAsStream("/property/dbconfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 设置常量
	public static final String CLASS_NAME = prop.getProperty("CLASS_NAME");
	public static final String DATABASE_URL = prop.getProperty("DATABASE_URL");
	public static final String SERVER_IP = prop.getProperty("SERVER_IP");
	public static final String SERVER_PORT = prop.getProperty("SERVER_PORT");
	public static final String DATABASE_SID = prop.getProperty("DATABASE_SID");
	public static final String USERNAME = prop.getProperty("USERNAME");
	public static final String PASSWORD = prop.getProperty("PASSWORD");
    
	public static void main(String[] args) {
		System.out.println(CLASS_NAME);
	}

}