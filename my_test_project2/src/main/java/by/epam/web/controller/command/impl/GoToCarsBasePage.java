package by.epam.web.controller.command.impl;

import java.io.IOException;
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

/**
 * This class represents access for forwarding to the CarsBase page.
 * 
 */

public class GoToCarsBasePage implements Command {
	
	private static final Logger log = Logger.getLogger(GoToCarsBasePage.class);
	
	private List<Car> cars;

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
		
		if(!(Boolean)session.getAttribute(ConstantParameter.ADMIN)) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The  is null or false");
			return;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();
		
			try {
				cars = serviceCar.getCarsBase();
			} catch (ServiceException e) {
				log.error(e);
				response.sendRedirect(ConstantPage.GOTOERROR500);
				return;
			}
		
		request.setAttribute(ConstantParameter.CARS, cars);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.CARSBASE);
		dispatcher.forward(request, response);
	}
}
