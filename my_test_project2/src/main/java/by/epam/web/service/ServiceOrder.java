package by.epam.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import by.epam.web.dao.DAOException;
import by.epam.web.entity.Order;

public interface ServiceOrder {

	boolean addOrder(int userId, int carId, List<String> date, double totalCost) throws ServiceException;
	
	boolean cancelOrder(int id, boolean isAdmin) throws ServiceException;
//	boolean cancelOrderByUser(int id);
//	boolean cancelOrderByAdmin(int id);
	
	Map<Order, String> getOrders(boolean isAdmin, int id) throws ServiceException;
//	Map<Order, String> getAllOrders() throws ServiceException;
//	Map<Order, String> getAllUsersOrders(int id) throws ServiceException;

	boolean acceptOrder(int id) throws ServiceException;
	
	Map<Order, String> getCurrentOrder(boolean isAdmin, int id) throws ServiceException;
//	Map<Order, String> getCuurentOrdersForAdmin();
//	Map<Order, String> getCurrentOrdersForUser(int id);
	
	
	List<Date> getDateOfOrder(List<String> dateStr) throws ServiceException;
	double getTotalCost(List<Date> date, double price);
	boolean completeOrder(int id) throws ServiceException;
}
