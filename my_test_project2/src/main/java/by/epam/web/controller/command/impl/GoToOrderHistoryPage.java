package by.epam.web.controller.command.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.web.controller.command.Command;
import by.epam.web.controller.constant.ConstantPage;
import by.epam.web.controller.constant.ConstantParameter;
import by.epam.web.entity.Order;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceOrder;
import by.epam.web.service.ServiceProvider;


/**
 * This class represents access for forwarding to the OrderHistory page.
 * 
 */

public class GoToOrderHistoryPage implements Command {
	
	private static final Logger log = Logger.getLogger(GoToOrderHistoryPage.class);
	
	private Map<Order, String> orders;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session is null");
			return;
		}
		
		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);
		if(isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The auth is null or false");
			return;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceOrder serviceOrder = provider.getServiceOrder();
		
		boolean isAdmin = (Boolean)session.getAttribute(ConstantParameter.ADMIN);
		int id = (Integer)session.getAttribute(ConstantParameter.USERID);
		
		try {
			orders = serviceOrder.getOrders(isAdmin, id);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		request.setAttribute(ConstantParameter.ORDERS, orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.ORDERHISTORY);
		dispatcher.forward(request, response);
	}
}
