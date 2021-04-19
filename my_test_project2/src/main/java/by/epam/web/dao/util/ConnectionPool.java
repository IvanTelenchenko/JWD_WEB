package by.epam.web.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

	private static BlockingQueue<WrapperConnection> pool;
	private static BlockingQueue<Connection> sourcePool;
	private static final int DEFAULT_POOLSIZE = 10;

	static {
		initConnectionPool();
	}

	private ConnectionPool() {
	}

	private static void initConnectionPool() {
		String poolSize = PropertiesUtil.get(DBParametr.DB_POOLSIZE_KEY);
		int size = poolSize == null ? DEFAULT_POOLSIZE : Integer.parseInt(poolSize);

		pool = new ArrayBlockingQueue<WrapperConnection>(size);
		sourcePool = new ArrayBlockingQueue<Connection>(size);

		for (int i = 0; i < size; i++) {
			Connection con = openConnection();
			System.out.println("hello");
			sourcePool.add(con);
			System.out.println("wrapper");
			pool.add(new WrapperConnection(con,pool));
		}
	}

	public static WrapperConnection getConnection(){
			try {
				return pool.take();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
	}

	private static Connection openConnection() throws ConnectionPoolException{
			try {
				System.out.println(PropertiesUtil.get(DBParametr.DB_URL_KEY) +" "+
						PropertiesUtil.get(DBParametr.DB_USERNAME_KEY)+" "+ PropertiesUtil.get(DBParametr.DB_PASSWORD_KEY));
				return DriverManager.getConnection(PropertiesUtil.get(DBParametr.DB_URL_KEY),
						PropertiesUtil.get(DBParametr.DB_USERNAME_KEY), PropertiesUtil.get(DBParametr.DB_PASSWORD_KEY));
			} catch (SQLException e) {
				throw new ConnectionPoolException("Connection hasn't opened", e);
			}
	}

	public  static void closeConnection() throws ConnectionPoolException {
		try {
			for (Connection con : sourcePool) {
				con.close();
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("Connection hasn't closed", e);
		}
	}
}