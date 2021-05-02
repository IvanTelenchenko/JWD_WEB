package by.epam.web.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.web.controller.command.Command;
import by.epam.web.controller.constant.ConstantPage;
import by.epam.web.controller.constant.ConstantParameter;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceOrder;
import by.epam.web.service.ServiceProvider;

/**
 * This class represents access for creating a new order 
 * 
 */

public class CreateOrder implements Command {
	
	private static final String MESSAGE_REPEAT_ORDER = "&messageCteateOrderRepeat=message";
	private static final String MESSAGE_CREATE_ORDER = "&messageCreateOrder=message";
	
	private static final Logger log = Logger.getLogger(CreateOrder.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session timeout");
			return;
		}

		boolean isAddOrder = false;
		
		String idCar = request.getParameter(ConstantParameter.CARID);
		String cost = request.getParameter(ConstantParameter.TOTALCOST);
		
		int userId = (Integer)session.getAttribute(ConstantParameter.USERID);
		int carId = Integer.parseInt(idCar);
		double totalCost = Double.parseDouble(cost);
		 
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceOrder serviceOrder = provider.getServiceOrder();
		
		List<String> date = new ArrayList<String>();
		
		if(session.getAttribute(ConstantParameter.DATEUSER) == null) {
			date = (List<String>) session.getAttribute(ConstantParameter.DATEDEF);
		}else {
			date = (List<String>) session.getAttribute(ConstantParameter.DATEUSER);
		}
		
		try {
			isAddOrder = serviceOrder.addOrder(userId, carId, date, totalCost);
		} catch (ServiceException e) {
			response.sendRedirect(ConstantPage.GOTOERROR500);
			log.error("The order hasn't been added");
			return;
		}
		
		if(!isAddOrder) {
			response.sendRedirect(ConstantPage.GOTOACCOUNT+MESSAGE_REPEAT_ORDER);
			log.info("The order hasn't been added. Value equal null");
			return;
		}
		
		response.sendRedirect(ConstantPage.GOTOACCOUNT+MESSAGE_CREATE_ORDER);
	}
}