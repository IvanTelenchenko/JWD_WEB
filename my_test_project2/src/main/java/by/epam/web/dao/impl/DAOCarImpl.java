package by.epam.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import by.epam.web.dao.DAOCar;
import by.epam.web.dao.DAOException;
import by.epam.web.dao.util.ConnectionPool;
import by.epam.web.dao.util.MySQLDriverLoader;
import by.epam.web.dao.util.WrapperConnection;
import by.epam.web.entity.Car;

public final class DAOCarImpl implements DAOCar {

	static {
		MySQLDriverLoader.getInstance();
	}

	private static final Logger log = Logger.getLogger(DAOCarImpl.class);

	private static final String DAO_CAR_SELECT_ALL_CARS = "SELECT * FROM cars ORDER BY brand;";
	private static final String DAO_CAR_SELECT_CAR = "SELECT * FROM cars WHERE id = ?;";
	private static final String DAO_CAR_SELECT_CAR_WITH_FILTER = "SELECT * FROM cars WHERE ";
	private static final String DAO_CAR_SELECT_CAR_WITH_DATE = "SELECT *FROM cars	" + "WHERE cars.id " + "NOT IN "
			+ "(SELECT orders.car_id FROM orders WHERE orders.end_date >= ? AND orders.start_date <= ? AND orders.status IN (1,2));";

	private static final String DAO_CAR_DELETE_CAR = "DELETE FROM cars "
			+ "WHERE cars.id=? and cars.id NOT IN (SELECT orders.car_id FROM orders WHERE orders.status in(1,2));";

	private static final String DAO_CAR_ADD_NEW_CAR = "INSERT INTO `garage`.`cars` "
			+ "( `brand`, `body`, `transmission`, `class`,`fuel`,`price`,"
			+ "  `full_name` ,`engine_capacity`, `numb_of_seats`,  `photo`) " + "  VALUES (?,?,?,?,?,?,?,?,?,?);";

	private static final String DAO_CAR_EDIT_CAR_WITHOUT_PHOTO = "UPDATE cars SET " + "brand = ?," + "body = ?,"
			+ "transmission = ?," + "class = ?," + "fuel = ?," + "price = ?," + "full_name = ?,"
			+ "engine_capacity = ?," + "numb_of_seats = ? " + "WHERE id = ?;";

	private static final String DAO_CAR_EDIT_CAR_WITH_PHOTO = "UPDATE cars SET " + "brand = ?," + "body = ?,"
			+ "transmission = ?," + "class = ?," + "fuel = ?," + "price = ?," + "full_name = ?,"
			+ "engine_capacity = ?," + "numb_of_seats = ?," + "photo = ? " + "WHERE id = ?;";

	private WrapperConnection connection;

	@Override
	public List<Car> getAllCars(List<String> date) throws DAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Car> list = new ArrayList<Car>();

		try {
			connection = ConnectionPool.getConnection();
			
			// Converting a date value from a string to a class date
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = null;
			Date endDate = null;
			try {
				startDate = format.parse(date.get(0));
				endDate = format.parse(date.get(1));
			} catch (ParseException e) {
				log.error(e);
				throw new DAOException(e);
			}

			ps = connection.prepareStatement(DAO_CAR_SELECT_CAR_WITH_DATE);
			ps.setTimestamp(1, new Timestamp(startDate.getTime()));
			ps.setTimestamp(2, new Timestamp(endDate.getTime()));

			rs = ps.executeQuery();

			while (rs.next()) {

				Car car = new Car(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getDouble(7), rs.getString(8), rs.getDouble(9), rs.getInt(10), rs.getString(11));
				list.add(car);
			}

			return list;

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
	public Car getCar(int id) throws DAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Car car = null;

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_CAR_SELECT_CAR);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				car = new Car(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getDouble(7), rs.getString(8), rs.getDouble(9), rs.getInt(10), rs.getString(11));
			}
			return car;

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
	public List<Car> getCarsWithFilter(Map<String, List<String>> map, List<String> date) throws DAOException {

		Statement st = null;
		ResultSet rs = null;
		
		List<Car> list = new ArrayList<Car>();
		
		// Creating a part of a custom request for database with filter parameters
		// The map consists of keys - categories to search for(class, brand)
		// and values - a list of points for each category
		
		StringBuilder value = new StringBuilder();

		for (Entry<String, List<String>> entry : map.entrySet()) {
			value.append(entry.getKey() + " IN(");
			for (String i : entry.getValue()) {
				value.append(i + ",");
			}
			value.delete(value.length() - 1, value.length());
			value.append(") AND ");
		}
		value.delete(value.length() - 4, value.length());
		
		// Converting a date value from a string to a class date
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = format.parse(date.get(0));
			endDate = format.parse(date.get(1));
		} catch (ParseException e) {
			log.error(e);
			throw new DAOException(e);
		}
		
		// Creating a part of a custom request with dates as string dates
		
		String start = new Timestamp(startDate.getTime()).toString();
		String end = new Timestamp(endDate.getTime()).toString();
		
		// Creating custom request from parts
		
		String customRequest = value.toString();
		customRequest += "AND cars.id NOT IN (SELECT orders.car_id FROM orders " + " WHERE orders.end_date >= '" + start
				+ "' AND orders.start_date <= '" + end + "' )";
		try {

			connection = ConnectionPool.getConnection();

			st = connection.createStatement();

			rs = st.executeQuery(DAO_CAR_SELECT_CAR_WITH_FILTER + customRequest);

			while (rs.next()) {
				Car car = new Car(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getDouble(7), rs.getString(8), rs.getDouble(9), rs.getInt(10), rs.getString(11));
				list.add(car);
			}

			return list;

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
	public List<Car> getCarsBase() throws DAOException {

		Statement st = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<Car>();

		try {
			connection = ConnectionPool.getConnection();

			st = connection.createStatement();
			rs = st.executeQuery(DAO_CAR_SELECT_ALL_CARS);

			while (rs.next()) {
				Car car = new Car(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getDouble(7), rs.getString(8), rs.getDouble(9), rs.getInt(10), rs.getString(11));
				list.add(car);
			}
			return list;

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
	public boolean deleteCar(int id) throws DAOException {

		PreparedStatement ps = null;

		boolean isDeleteCar = false;
		int row;

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_CAR_DELETE_CAR);

			ps.setInt(1, id);

			row = ps.executeUpdate();

			if (row > 0) {
				isDeleteCar = true;
			}

			return isDeleteCar;

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
	}

	@Override
	public boolean addNewCar(String brand, String body, String transmission, String classAuto, String fuel,
			String price, String name, String engineCapacity, String numbOfSeats, String uniqueNamePhoto)
			throws DAOException {

		PreparedStatement ps = null;

		boolean isAddCar = false;
		int row;
		
		// Double value conversion
		if (price.indexOf(',') != -1) {
			price = price.replace(',', '.');
		}
		
		// Double value conversion
		if (engineCapacity.indexOf(',') != -1) {
			engineCapacity = engineCapacity.replace(',', '.');
		}

		int brandInt = (Integer) Integer.parseInt(brand);
		int bodyInt = (Integer) Integer.parseInt(body);
		int transmissionInt = (Integer) Integer.parseInt(transmission);
		int classInt = (Integer) Integer.parseInt(classAuto);
		int fuelInt = (Integer) Integer.parseInt(fuel);
		double priceDoub = (Double) Double.parseDouble(price);
		double capacityDoub = (Double) Double.parseDouble(engineCapacity);
		int seatsInt = (Integer) Integer.parseInt(numbOfSeats);

		try {
			connection = ConnectionPool.getConnection();

			ps = connection.prepareStatement(DAO_CAR_ADD_NEW_CAR);

			ps.setInt(1, brandInt);
			ps.setInt(2, bodyInt);
			ps.setInt(3, transmissionInt);
			ps.setInt(4, classInt);
			ps.setInt(5, fuelInt);
			ps.setDouble(6, priceDoub);
			ps.setString(7, name);
			ps.setDouble(8, capacityDoub);
			ps.setInt(9, seatsInt);
			ps.setString(10, uniqueNamePhoto);

			row = ps.executeUpdate();

			if (row > 0) {
				isAddCar = true;
			}

			return isAddCar;

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
	}

	@Override
	public boolean editCar(String id, String brand, String body, String transmission, String classAuto, String fuel,
			String price, String name, String engineCapacity, String numbOfSeats, String photoName)
			throws DAOException {

		PreparedStatement ps = null;

		boolean isEditCar = false;
		int row;
		
		// Double value conversion
		if (price.indexOf(',') != -1) {
			price = price.replace(',', '.');
		}
		
		// Double value conversion
		if (engineCapacity.indexOf(',') != -1) {
			engineCapacity = engineCapacity.replace(',', '.');
		}

		int idInt = (Integer) Integer.parseInt(id);
		int brandInt = (Integer) Integer.parseInt(brand);
		int bodyInt = (Integer) Integer.parseInt(body);
		int transmissionInt = (Integer) Integer.parseInt(transmission);
		int classInt = (Integer) Integer.parseInt(classAuto);
		int fuelInt = (Integer) Integer.parseInt(fuel);
		double priceDoub = (Double) Double.parseDouble(price);
		double capacityDoub = (Double) Double.parseDouble(engineCapacity);
		int seatsInt = (Integer) Integer.parseInt(numbOfSeats);

		try {
			connection = ConnectionPool.getConnection();

			if (photoName == null) {

				ps = connection.prepareStatement(DAO_CAR_EDIT_CAR_WITHOUT_PHOTO);

				ps.setInt(1, brandInt);
				ps.setInt(2, bodyInt);
				ps.setInt(3, transmissionInt);
				ps.setInt(4, classInt);
				ps.setInt(5, fuelInt);
				ps.setDouble(6, priceDoub);
				ps.setString(7, name);
				ps.setDouble(8, capacityDoub);
				ps.setInt(9, seatsInt);
				ps.setInt(10, idInt);
				row = ps.executeUpdate();
			} else {

				ps = connection.prepareStatement(DAO_CAR_EDIT_CAR_WITH_PHOTO);

				ps.setInt(1, brandInt);
				ps.setInt(2, bodyInt);
				ps.setInt(3, transmissionInt);
				ps.setInt(4, classInt);
				ps.setInt(5, fuelInt);
				ps.setDouble(6, priceDoub);
				ps.setString(7, name);
				ps.setDouble(8, capacityDoub);
				ps.setInt(9, seatsInt);
				ps.setString(10, photoName);
				ps.setInt(11, idInt);
				row = ps.executeUpdate();
			}

			if (row > 0) {
				isEditCar = true;
			}

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

		return isEditCar;
	}
}