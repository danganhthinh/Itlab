package com.topica.edu.vn.connectionpool;

import java.sql.Connection;

public class DataPool {
	static JdbcConnectionPool pool = new JdbcConnectionPool();

	public static Connection getConn() {
		Connection connection = pool.getConnPool();
		return connection;
	}

	public static void returnConn(Connection connection) {
		pool.returnConnPool(connection);
	}
	
}
