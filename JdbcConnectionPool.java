package com.topica.edu.vn.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnectionPool {
	List<Connection> listConn = new ArrayList<Connection>();

	public JdbcConnectionPool() {
		initializeConnectionPool();
	}

	private void initializeConnectionPool() {
		// TODO Auto-generated method stub

		if (!checkConnPoolIsFull()) {
			listConn.add(createNewConn());
		}
	}

	private Connection createNewConn() {

		Config config = new Config();

		try {
			Class.forName(config.DB_DRIVER);
			Connection connection = DriverManager.getConnection(config.DB_URL, config.DB_USER_NAME, config.DB_PASS);
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private synchronized boolean checkConnPoolIsFull() {
		// TODO Auto-generated method stub
		final int MAX_POOL = Config.getInstance().DB_MAX_CONNECTIONS;

		if (listConn.size() < MAX_POOL) {
			return false;
		}

		return true;
	}

	public synchronized Connection getConnPool() {

		Connection connection = null;

		if (listConn.size() > 0) {
			connection = listConn.get(0);
			listConn.remove(0);
		}
		return connection;
	}

	public synchronized void returnConnPool(Connection connection) {

		listConn.add(connection);
	}

	public void minPool() {
		final int MIN_POOL = Config.getInstance().DB_MIN_CONNECTIONS;

		while (listConn.size() < MIN_POOL) {
			listConn.add(createNewConn());
		}
	}
}
