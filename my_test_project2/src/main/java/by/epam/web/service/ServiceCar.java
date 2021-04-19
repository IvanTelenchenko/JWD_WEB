package by.epam.web.service;

import java.util.List;
import java.util.Map;

import by.epam.web.entity.Car;
import by.epam.web.entity.Order;

public interface ServiceCar {

	
	Car getCar(int id) throws ServiceException;
	
	
	boolean deleteCar(int id) throws ServiceException;
	boolean addNewCar(String brand, String body, String transmission, String classAuto, String fuel, String price,
			String name, String engineCapacity, String numbOfSeats, String uniqueNamePhoto) throws ServiceException;
	boolean editCar(String id, String brand, String body, String transmission, String classAuto, String fuel, String price,
			String name, String engineCapacity, String numbOfSeats, String photoName) throws ServiceException;
	Map<String, List<String>> getMapOfCarFilters(Map<String, String[]> listFilters);
	
	List<Car> getCars(Map<String, List<String>> map, List<String> date) throws ServiceException;
	
	List<Car> getCarsWithoutFilter(List<String> date) throws ServiceException;
//	List<Car> getCarsWithFilter(Map<String, List<String>> map, List<String> date) throws ServiceException;
	
	List<Car> getCarsBase() throws ServiceException;
	
}
