package by.epam.web.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

/**
 * This class represents a connection pool 
 * */

public final class ConnectionPool {
	
	private static final Logger log = Logger.getLogger(ConnectionPool.class);
	
	private static final int DEFAULT_POOLSIZE = 10;
	private static BlockingQueue<WrapperConnection> pool;
	private static BlockingQueue<Connection> sourcePool;

	static {
		initConnectionPool();
	}
	
	/**
	 * Instantiates a new connection pool
	 * */
	private ConnectionPool() {
	}
	
	/**
	 * This method initialize {@link Connection} in the connection pool
	 * */
	private static void initConnectionPool() {
		String poolSize = PropertiesUtil.get(DBParametr.DB_POOLSIZE_KEY);
		int size = poolSize == null ? DEFAULT_POOLSIZE : Integer.parseInt(poolSize);

		pool = new ArrayBlockingQueue<WrapperConnection>(size);
		sourcePool = new ArrayBlockingQueue<Connection>(size);

		for (int i = 0; i < size; i++) {
			Connection con = openConnection();
			sourcePool.add(con);
			pool.add(new WrapperConnection(con,pool));
			log.info(" The connection number " + i + " has been added to connection pool");
		}
	}

	
	/**
	 * This method allows you to receive the {@link WrapperConnection}
	 * */
	public static WrapperConnection getConnection(){
			try {
				return pool.take();
			} catch (InterruptedException e) {
				log.error("WrapperConnection hasn't been gotten");
				throw new RuntimeException(e);
			}
	}

	/**
	 * This method allows you to create the new {@link Connection} with help {@link DriverManager}
	 * */
	private static Connection openConnection() throws ConnectionPoolException{
			try {
				return DriverManager.getConnection(PropertiesUtil.get(DBParametr.DB_URL_KEY),
						PropertiesUtil.get(DBParametr.DB_USERNAME_KEY), PropertiesUtil.get(DBParametr.DB_PASSWORD_KEY));
			} catch (SQLException e) {
				log.error("The connection hasn't been created");
				throw new ConnectionPoolException("The connection hasn't been created");
			}
	}
	
	/**
	 * This method allows you to close the {@link ConnectionPool}
	 * */
	public  static void closeConnection() throws ConnectionPoolException {
		try {
			for (Connection con : sourcePool) {
				con.close();
			}
			log.info("The connection pool has been closed");
		} catch (SQLException e) {
			log.error("The connection pool hasn't been closed");
			throw new ConnectionPoolException("The connection pool hasn't been closed");
		}
	}
}