package com.topica.edu.vn.connectionpool;

public class Config {
	public String DB_USER_NAME;
	public String DB_PASS;
	public String DB_URL;
	public String DB_DRIVER;
	public Integer DB_MAX_CONNECTIONS = 10;
	public Integer DB_MIN_CONNECTIONS = 5;

	public Config() {
		init();
	}

	private static Config config = new Config();

	public static Config getInstance() {
		return config;
	}

	private void init() {
		// TODO Auto-generated method stub
		DB_USER_NAME = "root";
		DB_PASS = "10819960";
		DB_URL = "jdbc:mysql://localhost:3306/anhthinh";
		DB_DRIVER = "com.mysql.jdbc.Driver";
		DB_MAX_CONNECTIONS = 5;
	}
}
