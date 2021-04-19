package by.epam.web.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.web.controller.command.Command;
import by.epam.web.controller.constant.ConstantPage;
import by.epam.web.controller.constant.ConstantParameter;
import by.epam.web.entity.Car;
import by.epam.web.service.ServiceCar;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceOrder;
import by.epam.web.service.ServiceProvider;

/**
 * This class represents access for forwarding to the Verification page.
 * 
 */

public class GoToVerificationPage implements Command {

	private static final String CAR_ID = "&carid=";

	private static final Logger log = Logger.getLogger(GoToVerificationPage.class);

	private static final String ERROR_MESSAGE = "&isAuthMessage=You need to log in to your personal account to book a car";

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session == null) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session is null");
			return;
		}

		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(
					ConstantPage.GOTOCAR + CAR_ID + request.getParameter(ConstantParameter.CARID) + ERROR_MESSAGE);
			log.info("The user haven't logged in! ");
			return;
		}

		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();

		Integer id = Integer.valueOf(request.getParameter(ConstantParameter.CARID));

		Car car;
		try {
			car = serviceCar.getCar(id);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}

		List<String> dateStr;
		List<Date> date = new ArrayList<Date>();

		if (session.getAttribute(ConstantParameter.DATEUSER) == null) {
			dateStr = (List<String>) session.getAttribute(ConstantParameter.DATEDEF);
		} else {
			dateStr = (List<String>) session.getAttribute(ConstantParameter.DATEUSER);
		}

		ServiceOrder serviceOrder = provider.getServiceOrder();

		try {
			date = serviceOrder.getDateOfOrder(dateStr);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}

		double cost = serviceOrder.getTotalCost(date, car.getPrice());

		request.setAttribute(ConstantParameter.DATE, date);
		request.setAttribute(ConstantParameter.COST, cost);
		request.setAttribute(ConstantParameter.CAR, car);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.VERIFICATIONPAGE);
		dispatcher.forward(request, response);
	}
}