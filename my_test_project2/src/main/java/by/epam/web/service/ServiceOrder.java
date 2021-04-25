package by.epam.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import by.epam.web.entity.Order;

/**
 * This interface represents access to the orders. 
 * */

public interface ServiceOrder {
	
	/**
	 * This method allows you to create new order
	 * @param userId ID of the user who created the new order.
	 * @param carId ID of the car to be booked.
	 * @param date consist of a list of strings where first element is the booking start date and second element is the booking end date.
	 * @param totalCost total cost of this order.
	 * @return returns true if order has been created else return false
	 */
	boolean addOrder(int userId, int carId, List<String> date, double totalCost) throws ServiceException;
	
	/**
	 * This method allows the admin or user to cancel the current order
	 * @param id ID of the order that the admin or user wants to cancel
	 * @param isAdmin indicates if the user is an administrator
	 * @return returns true if order has been canceled else false
	 */
	boolean cancelOrder(int id, boolean isAdmin) throws ServiceException;
	
	/**
	 * This method allows you to get the order history
	 * @param id ID of the user who wants to receive order history
	 * @param isAdmin indicates if the user is an administrator
	 * @return returns Map<Order, String> of the orders where Order - order, String - car name
	 */
	Map<Order, String> getOrders(boolean isAdmin, int id) throws ServiceException;

	/**
	 * This method allows the admin to accept the order
	 * @param id ID of the order that the admin wants to accept
	 * @return returns true if order has been accepted else false
	 */
	boolean acceptOrder(int id) throws ServiceException;
	
	
	/**
	 * This method allows the admin or user to get the currents orders
	 * @param id ID of the user who wants to receive currents orders.
	 * @param isAdmin indicates if the user is an administrator
	 * @return returns Map<Order, String> of the orders where Order - order, String - car name
	 */
	Map<Order, String> getCurrentOrder(boolean isAdmin, int id) throws ServiceException;
	
	/**
	 * This method allows you to get the string list of order dates
	 * @param dateString string list of dates
	 * @return returns list of order dates
	 */
	List<Date> getDateOfOrder(List<String> dateString) throws ServiceException;
	
	/**
	 * This method allows you to get the total cost of the order
	 * @param date consist of a list of strings where first 
	 *  element is the booking start date and second element is the booking end date.
	 * @param price price per day
	 * @return returns total cost of the order
	 */
	double getTotalCost(List<Date> date, double price);
	
	/**
	 * This method allows the admin to complete the order
	 * @param id ID of the order that the admin wants to complete
	 * @return returns true if order has been completed else false
	 */
	boolean completeOrder(int id) throws ServiceException;
}
