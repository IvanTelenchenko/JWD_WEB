package by.epam.web.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import by.epam.web.service.ServiceProvider;
import by.epam.web.service.ServiceUser;

/**
 * This class represents access for forwarding to the Main page.
 * 
 */

public class GoToMainPage implements Command {

	private static final Logger log = Logger.getLogger(GoToMainPage.class);

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session == null) {
			response.sendRedirect("Controller?command=error");
			log.info("The session is null");
			return;
		}

		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();
		ServiceUser serviceUser = provider.getServiceUser();

		List<String> date = new ArrayList<String>();

		if (session.getAttribute(ConstantParameter.DATEUSER) == null) {
			date = serviceUser.getDefaultDate();
			session.setAttribute(ConstantParameter.DATEDEF, date);
		} else {
			date = (List<String>) session.getAttribute(ConstantParameter.DATEUSER);
		}

		List<Car> cars;
		try {
			cars = serviceCar.getCarsWithoutFilter(date);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}

		request.setAttribute(ConstantParameter.COUNTCARS, cars.size());
		request.setAttribute(ConstantParameter.ALLCARS, cars);

		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.MAIN);
		dispatcher.forward(request, response);
	}
}