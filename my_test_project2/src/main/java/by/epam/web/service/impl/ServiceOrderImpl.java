package by.epam.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import by.epam.web.dao.DAOException;
import by.epam.web.dao.DAOOrder;
import by.epam.web.dao.DAOProvider;
import by.epam.web.entity.Order;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceOrder;

public class ServiceOrderImpl implements ServiceOrder {

	private static final Logger log = Logger.getLogger(ServiceOrderImpl.class);

	@Override
	public boolean addOrder(int userId, int carId, List<String> date, double totalCost) throws ServiceException {

		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		boolean isAddOrder = false;

		try {
			isAddOrder = daoOrder.addOrder(userId, carId, date, totalCost);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The order hasn't been created");
		}

//		if (!isAddOrder) {
//			throw new ServiceException("The order hasn't been created");
//		}

		return isAddOrder;
	}

	@Override
	public Map<Order, String> getCurrentOrder(boolean isAdmin, int id) throws ServiceException {

		Map<Order, String> orders;
		try {
			if (!isAdmin) {
				orders = getCurrentOrdersForUser(id);
			} else {

				orders = getCuurentOrdersForAdmin();
			}
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The map of current orders wasn't got");
		}

		return orders;
	}

	public Map<Order, String> getCurrentOrdersForUser(int id) throws DAOException {

		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		Map<Order, String> orders;
		orders = daoOrder.getCurrentsOrder(id);

		return orders;
	}

	public Map<Order, String> getCuurentOrdersForAdmin() throws DAOException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		Map<Order, String> orders = daoOrder.getCurrentOrdersForAdmin();

		return orders;
	}

	@Override
	public Map<Order, String> getOrders(boolean isAdmin, int id) throws ServiceException {

		Map<Order, String> orders;
		try {
			if (!isAdmin) {
				orders = getAllUsersOrders(id);
			} else {
				orders = getAllOrders();
			}
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The map of order wasn't got");
		}

		return orders;
	}

	public Map<Order, String> getAllUsersOrders(int id) throws DAOException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		Map<Order, String> orders;
		orders = daoOrder.getAllUsersOrders(id);

		return orders;
	}

	public Map<Order, String> getAllOrders() throws DAOException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		Map<Order, String> orders;
		orders = daoOrder.getAllOrders();

		return orders;
	}

	@Override
	public boolean cancelOrder(int id, boolean isAdmin) throws ServiceException {

		boolean isCancelOrder = false;
		try {
			if (!isAdmin) {

				isCancelOrder = cancelOrderByUser(id);

			} else {
				isCancelOrder = cancelOrderByAdmin(id);
			}
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The order wasn't canceled");
		}

//		if (!isCancelOrder) {
//			throw new ServiceException("The order hasn't been canceled");
//		}

		return isCancelOrder;
	}

	public boolean cancelOrderByUser(int id) throws DAOException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		return daoOrder.cancelOrderByUser(id);
	}

	public boolean cancelOrderByAdmin(int id) throws DAOException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		return daoOrder.cancelOrderByAdmin(id);
	}

	@Override
	public boolean acceptOrder(int id) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		boolean isAcceptOrder = false;

		try {
			isAcceptOrder = daoOrder.acceptOrder(id);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The order wasn't accepted");
		}

//		if (!isAcceptOrder) {
//		}

		return isAcceptOrder;
	}

	@Override
	public List<Date> getDateOfOrder(List<String> dateStr) throws ServiceException {

		List<Date> date = new ArrayList<Date>();
		Date dateStart = null;
		Date dateEnd = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			dateStart = format.parse(dateStr.get(0));
			date.add(dateStart);
			dateEnd = format.parse(dateStr.get(1));
			date.add(dateEnd);
		} catch (ParseException e) {
			log.error(e);
			throw new ServiceException("The date wasn't parse");
		}

		return date;
	}

	@Override
	public double getTotalCost(List<Date> date, double price) {

		double totalCost;

		int days;

		long time = date.get(1).getTime() - date.get(0).getTime();
		if (time == 0) {
			days = 1;
		} else {
			days = ((int) (time / 3600000 / 24)) + 1;
		}

		totalCost = days * price;

		return totalCost;
	}

	@Override
	public boolean completeOrder(int id) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOOrder daoOrder = provider.getOrder();

		boolean isCompleteOrder = false;

		try {
			isCompleteOrder = daoOrder.completeOrder(id);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The order wasn't completed");
		}

//		if (!isCompleteOrder) {
//		}

		return isCompleteOrder;
	}
}
