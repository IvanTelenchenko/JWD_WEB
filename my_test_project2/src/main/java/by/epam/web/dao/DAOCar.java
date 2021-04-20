package by.epam.web.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.web.entity.Car;


/**
 * This interface represents user's accept to the database car. 
 * */

public interface DAOCar {
	
	/**
	 * This method allows you to get cars by user dates
	 * @param date consist of a list of strings where first element is start date and second element is end date.
	 * @return return a list of cars
	 */
	List<Car> getAllCars(List<String> date) throws DAOException;
	
	/**
	 * This method allows you to get car by id
	 * @param id {@link Car}
	 * @return return a {@link Car}
	 */
	Car getCar(int id) throws DAOException;
	
	/**
	 * This method allows you to get cars by user filter
	 * @param The map consist of a {@link String} key that representing name filter and a {@link List}<{@link String}> 
	 * value representing the values of that filter.
	 * @param date consist of a list of strings where first element is start date and second element is end date.
	 * @return return a list of cars
	 */
	List<Car> getCarsWithFilter(Map<String, List<String>> map,List<String> date) throws DAOException;
	
	/**
	 * This method this method allows you to get all cars from database
	 * @return return a list of cars
	 */
	List<Car> getCarsBase() throws DAOException;
	
	/**
	 * This method allows you to delete car by id
	 * @param id {@link Car}
	 * @return return true if {@link Car} has been deleted else false
	 */
	boolean deleteCar(int id) throws DAOException;
	
	/**
	 * This method allows you to add new car to database
	 * @param brand represents a string type of brand
	 * @param body represents a string type of body
	 * @param transmission represents a string type of transmission
	 * @param classAuto represents a string type of classAuto
	 * @param fuel represents a string type of fuel
	 * @param price represents a string type of price
	 * @param name represents a string type of name
	 * @param engineCapacity represents a string type of engineCapacity
	 * @param numbOfSeats represents a string type of numbOfSeats
	 * @param uniqueNamePhoto represents a string type of uniqueNamePhoto
	 * 
	 * @return return true if {@link Car} has been added else false
	 */
	boolean addNewCar(String brand, String body, String transmission, String classAuto, String fuel, String price,
			String name, String engineCapacity, String numbOfSeats, String uniqueNamePhoto) throws DAOException;
	
	/**
	 * This method allows you to edit car in database
	 * @param id represents a string type of brand
	 * @param brand represents a string type of brand
	 * @param body represents a string type of body
	 * @param transmission represents a string type of transmission
	 * @param classAuto represents a string type of classAuto
	 * @param fuel represents a string type of fuel
	 * @param price represents a string type of price
	 * @param name represents a string type of name
	 * @param engineCapacity represents a string type of engineCapacity
	 * @param numbOfSeats represents a string type of numbOfSeats
	 * @param photoName represents a string type of photoName
	 * 
	 * @return return true if {@link Car} has been edited else false
	 */
	boolean editCar(String id, String brand, String body, String transmission, String classAuto, String fuel, String price,
			String name, String engineCapacity, String numbOfSeats, String photoName) throws DAOException;
}
