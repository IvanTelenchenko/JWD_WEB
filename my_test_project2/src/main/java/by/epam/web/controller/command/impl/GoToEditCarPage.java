package by.epam.web.controller.command.impl;

import java.io.IOException;

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
 * This class represents access for forwarding to the EditCar page.
 * 
 */

public class GoToEditCarPage implements Command{
	
	private static final Logger log = Logger.getLogger(GoToEditCarPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);
		Boolean isAdmin = (Boolean) session.getAttribute(ConstantParameter.ADMIN);
		
		if(isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The attribute \"auth\" of session equals null or false");
			return;
		}
		
		if(isAdmin == null || !isAdmin) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The attribute \"admin\" of session equals null or false");
			return;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();
		
		Integer carId = Integer.valueOf(request.getParameter(ConstantParameter.CARID));
		
		Car car;
		try {
			car = serviceCar.getCar(carId);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		request.setAttribute("car", car);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.EDITCAR);
		dispatcher.forward(request, response);
	}
}