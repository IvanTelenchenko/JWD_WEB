package by.epam.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import by.epam.web.dao.DAOException;
import by.epam.web.dao.DAOOrder;
import by.epam.web.dao.util.ConnectionPool;
import by.epam.web.dao.util.MySQLDriverLoader;
import by.epam.web.dao.util.WrapperConnection;
import by.epam.web.entity.Order;

public class DAOOrderImpl implements DAOOrder {

	static {
		MySQLDriverLoader.getInstance();
	}

	private static final Logger log = Logger.getLogger(DAOOrder.class);
	private WrapperConnection connection = null;
	private static final String DAO_ORDER_ADD_NEW_ORDER = "INSERT INTO `garage`.`orders`"
			+ " (`user_id`, `car_id`,`status`,start_date,end_date, total_cost) " + "VALUES ( ?, ?, '1',?,?,?);";

	private static final String DAO_ORDER_CHECK_NEW_ORDER = "SELECT car_id FROM orders WHERE status IN(1,2) AND car_id = ? AND"
			+ " orders.end_date >= ? AND orders.start_date <= ?;";
	private static final String DAO_ORDER_GET_CURRENT_ORDERS = "SELECT orders.id, orders.user_id, orders.car_id, orders.status,"
			+ "orders.start_date, orders.end_date, orders.total_cost, cars.full_name " + "FROM orders "
			+ "LEFT JOIN  cars ON cars.id = orders.car_id " + "WHERE user_id = ? AND orders.status IN (1,2) "
			+ "ORDER BY orders.start_date;";
	private static final String DAO_ORDER_GET_CURRENT_ORDERS_FOR_ADMIN = "SELECT orders.id, orders.user_id, orders.car_id, orders.status,"
			+ "orders.start_date, orders.end_date, orders.total_cost, cars.full_name " + "FROM orders "
			+ "LEFT JOIN  cars ON cars.id = orders.car_id " + "WHERE orders.status IN (1,2) "
			+ "ORDER BY orders.start_date;";
	private static final String DAO_ORDER_GET_ALL_USERS_ORDERS = "SELECT orders.id, orders.user_id, orders.car_id, orders.status,"
			+ "orders.start_date, orders.end_date, orders.total_cost, cars.full_name " + "FROM orders "
			+ "LEFT JOIN  cars ON cars.id = orders.car_id " + "WHERE user_id = ? " + "ORDER BY orders.start_date;";
	private static final String DAO_ORDER_GET_ALL_ORDERS = "SELECT orders.id, orders.user_id, orders.car_id, orders.status,"
			+ "orders.start_date, orders.end_date, orders.total_cost, cars.full_name " + "FROM orders "
			+ "LEFT JOIN  cars ON cars.id = orders.car_id " + "ORDER BY orders.start_date;";
	private static final String DAO_ORDER_CENCEL_ORDER_BY_USER = "UPDATE orders SET status = 4 WHERE status IN(1,2) AND id = ?;";
	private static final String DAO_ORDER_CENCEL_ORDER_BY_ADMIN = "UPDATE orders SET status = 5 WHERE status IN(1,2) AND id = ?;";
	private static final String DAO_ORDER_ACCEPT_ORDER_BY_ADMIN = "UPDATE orders SET status = 2 WHERE status IN(1) AND id = ?;";
	private static final String DAO_ORDER_COMPLETE_ORDER_BY_ADMIN = "UPDATE orders SET status = 3 WHERE status IN(2) AND id = ?;";

	@Override
	public boolean addOrder(int userId, int carId, List<String> date, double totalCost) throws DAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start;
		Date end;
		try {
			start = format.parse(date.get(0));
			end = format.parse(date.get(1));
		} catch (ParseException e1) {
			log.error(e1);
			throw new DAOException(e1);
		}
		
		boolean isAddOrder = false;
		int i;

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_ORDER_CHECK_NEW_ORDER);
			ps.setInt(1, carId);
			ps.setTimestamp(2, new Timestamp(start.getTime()));
			ps.setTimestamp(3, new Timestamp(end.getTime()));

			rs = ps.executeQuery();

			if (rs.next()) {
				return isAddOrder;
			} else {
				ps.close();
				ps = connection.prepareStatement(DAO_ORDER_ADD_NEW_ORDER);
				ps.setInt(1, userId);
				ps.setInt(2, carId);
				ps.setTimestamp(3, new Timestamp(start.getTime()));
				ps.setTimestamp(4, new Timestamp(end.getTime()));
				ps.setDouble(5, totalCost);
				i = ps.executeUpdate();
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}

		if (i > 0) {
			isAddOrder = true;
		}

		return isAddOrder;
	}

	@Override
	public Map<Order, String> getCurrentsOrder(int id) throws DAOException {

		ResultSet rs = null;
		PreparedStatement ps = null;

		Map<Order, String> orders = new LinkedHashMap<Order, String>();

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_ORDER_GET_CURRENT_ORDERS);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getDouble(7));
				String carName = rs.getString(8);
				orders.put(order, carName);
			}
			return orders;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public Map<Order, String> getCurrentOrdersForAdmin() throws DAOException {

		Statement st = null;
		ResultSet rs = null;

		Map<Order, String> orders = new LinkedHashMap<Order, String>();

		try {
			connection = ConnectionPool.getConnection();

			st = connection.createStatement();
			rs = st.executeQuery(DAO_ORDER_GET_CURRENT_ORDERS_FOR_ADMIN);
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getDouble(7));
				String carName = rs.getString(8);
				orders.put(order, carName);
			}
			return orders;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public Map<Order, String> getAllUsersOrders(int id) throws DAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Map<Order, String> orders = new LinkedHashMap<Order, String>();

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_ORDER_GET_ALL_USERS_ORDERS);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getDouble(7));
				String carName = rs.getString(8);
				orders.put(order, carName);
			}
			return orders;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public Map<Order, String> getAllOrders() throws DAOException {

		Statement st = null;
		ResultSet rs = null;

		Map<Order, String> orders = new LinkedHashMap<Order, String>();

		try {
			connection = ConnectionPool.getConnection();

			st = connection.createStatement();
			rs = st.executeQuery(DAO_ORDER_GET_ALL_ORDERS);

			while (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getDouble(7));
				String carName = rs.getString(8);
				orders.put(order, carName);
			}
			return orders;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	@Override
	public boolean cancelOrderByUser(int id) throws DAOException {

		PreparedStatement ps = null;

		boolean isCancelOrder = false;
		int i;
		try {
			connection = ConnectionPool.getConnection();
			ps = connection.prepareStatement(DAO_ORDER_CENCEL_ORDER_BY_USER);
			ps.setInt(1, id);
			i = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
		if (i > 0) {
			isCancelOrder = true;
		}
		return isCancelOrder;
	}

	public boolean cancelOrderByAdmin(int id) throws DAOException {

		PreparedStatement ps = null;

		boolean isCancelOrder = false;
		int i;
		try {
			connection = ConnectionPool.getConnection();
			ps = connection.prepareStatement(DAO_ORDER_CENCEL_ORDER_BY_ADMIN);
			ps.setInt(1, id);
			i = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
		if (i > 0) {
			isCancelOrder = true;
		}
		return isCancelOrder;
	}

	public boolean acceptOrder(int id) throws DAOException {

		PreparedStatement ps = null;

		boolean isAcceptOrder = false;
		int i;
		try {
			connection = ConnectionPool.getConnection();
			ps = connection.prepareStatement(DAO_ORDER_ACCEPT_ORDER_BY_ADMIN);
			ps.setInt(1, id);
			i = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
		if (i > 0) {
			isAcceptOrder = true;
		}
		return isAcceptOrder;
	}

	@Override
	public boolean completeOrder(int id) throws DAOException {

		PreparedStatement ps = null;

		boolean isCompleteOrder = false;
		int i;
		try {
			connection = ConnectionPool.getConnection();
			ps = connection.prepareStatement(DAO_ORDER_COMPLETE_ORDER_BY_ADMIN);
			ps.setInt(1, id);
			i = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new DAOException(e);
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
		if (i > 0) {
			isCompleteOrder = true;
		}
		return isCompleteOrder;
	}
}
