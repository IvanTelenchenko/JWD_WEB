package by.epam.web.dao;

import java.util.List;
import java.util.Map;

import by.epam.web.entity.Order;

public interface DAOOrder {
	
	boolean addOrder(int userId, int carId, List<String> date, double totalCost) throws DAOException;
//	List<Order> getCurrentOrder(int id);
	Map<Order, String> getCurrentsOrder(int id) throws DAOException;
	Map<Order, String> getAllUsersOrders(int id) throws DAOException;
	boolean cancelOrderByUser(int id) throws DAOException;
	boolean cancelOrderByAdmin(int id) throws DAOException;
	Map<Order, String> getCurrentOrdersForAdmin() throws DAOException;
	Map<Order, String> getAllOrders() throws DAOException;
	boolean acceptOrder(int id) throws DAOException;
	boolean completeOrder(int id) throws DAOException;

}
