package by.epam.web.dao;

import java.util.List;
import java.util.Map;

import by.epam.web.entity.Order;

/**
 * This interface represents user's access to the orders. 
 * */

public interface DAOOrder {
	/**
	 * This method allows you to create new order
	 * @param userId ID of the user who created the new order.
	 * @param carId ID of the car to be booked.
	 * @param date consist of a list of strings where first element is the booking start date and second element is the booking end date.
	 * @param totalCost total cost of this order.
	 * @return returns true if order has been created else return false
	 */
	boolean addOrder(int userId, int carId, List<String> date, double totalCost) throws DAOException;
	/**
	 * This method allows the user to get the currents orders
	 * @param id ID of the user who wants to receive currents orders.
	 * @return returns Map<Order, String> of the orders where Order - order, String - car name
	 */
	Map<Order, String> getCurrentsOrder(int id) throws DAOException;
	
	/**
	 * This method allows you to get the order history
	 * @param id ID of the user who wants to receive order history
	 * @return returns Map<Order, String> of the orders where Order - order, String - car name
	 */
	Map<Order, String> getAllUsersOrders(int id) throws DAOException;
	
	/**
	 * This method allows the user to cancel the current order
	 * @param id ID of the order that the user wants to cancel
	 * @return returns true if order has been canceled else false
	 */
	boolean cancelOrderByUser(int id) throws DAOException;
	
	/**
	 * This method allows the admin to cancel the current order
	 * @param id ID of the order that the admin wants to cancel
	 * @return returns true if order has been canceled else false
	 */
	boolean cancelOrderByAdmin(int id) throws DAOException;
	/**
	 * This method allows the admin to get the currents orders
	 * @return returns Map<Order, String> of the orders where Order - order, String - car name
	 */
	Map<Order, String> getCurrentOrdersForAdmin() throws DAOException;
	/**
	 * This method allows the admin to get the order history
	 * @return returns Map<Order, String> of the orders where Order - order, String - car name
	 */
	Map<Order, String> getAllOrders() throws DAOException;
	
	/**
	 * This method allows the admin to accept the order
	 * @param id ID of the order that the admin wants to accept
	 * @return returns true if order has been accepted else false
	 */
	boolean acceptOrder(int id) throws DAOException;
	
	/**
	 * This method allows the admin to complete the order
	 * @param id ID of the order that the admin wants to complete
	 * @return returns true if order has been completed else false
	 */
	boolean completeOrder(int id) throws DAOException;

}
