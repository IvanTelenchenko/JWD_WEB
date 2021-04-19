package by.epam.web.dao;

import java.util.List;
import java.util.Map;

import by.epam.web.entity.Car;

public interface DAOCar {
	
	List<Car> getAllCars(List<String> date) throws DAOException;
	Car getCar(int id) throws DAOException;
	List<Car> getCarsWithFilter(Map<String, List<String>> map,List<String> date) throws DAOException;
	List<Car> getCarsBase() throws DAOException;
	boolean deleteCar(int id) throws DAOException;
	boolean addNewCar(String brand, String body, String transmission, String classAuto, String fuel, String price,
			String name, String engineCapacity, String numbOfSeats, String uniqueNamePhoto) throws DAOException;
	boolean editCar(String id, String brand, String body, String transmission, String classAuto, String fuel, String price,
			String name, String engineCapacity, String numbOfSeats, String photoName) throws DAOException;
}
