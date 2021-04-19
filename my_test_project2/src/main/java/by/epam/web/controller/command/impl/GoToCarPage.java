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
 * This class represents access for forwarding to the Car page.
 * 
 */

public class GoToCarPage implements Command{
	
	private static final Logger log = Logger.getLogger(GoToCarPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session is null");
			return;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();
		
		Integer id = Integer.valueOf(request.getParameter(ConstantParameter.CARID));
		
		Car car = null;
		try {
			car = serviceCar.getCar(id);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		request.setAttribute(ConstantParameter.CAR, car);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.CAR);
		dispatcher.forward(request, response);
	}
}
