package by.epam.web.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import by.epam.web.entity.Car;
import by.epam.web.service.ServiceCar;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceProvider;
import by.epam.web.service.ServiceUser;


/**
 *This class is a redirect access to the main page to search for cars with filter.
 * 
 */

public class MainPageWithFilter implements Command{
	
	private static final Logger log = Logger.getLogger(MainPageWithFilter.class);
	

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Car> cars = null;
		boolean isCheckboxFilter = false;
		
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session is null");
			return;
		}
		
		Map<String, String[]> mapFilters = request.getParameterMap();
		Map<String, List<String>> map = new HashMap<String,List<String>>();
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();
		
		map = serviceCar.getMapOfCarFilters(mapFilters);
		 
		List<String> date = new ArrayList<String>();

		ServiceUser serviceUser = provider.getServiceUser();
		 
		if(session.getAttribute(ConstantParameter.DATEUSER) == null) {
			if(session.getAttribute(ConstantParameter.DATEDEF) == null) {
				date = serviceUser.getDefaultDate();
				session.setAttribute(ConstantParameter.DATEDEF, date);
			}else{
				date = (List<String>) session.getAttribute(ConstantParameter.DATEDEF);
			}
		}else {
			date = (List<String>) session.getAttribute(ConstantParameter.DATEUSER);
		}
		
		try {
			cars = serviceCar.getCars(map, date);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		session.setAttribute(ConstantParameter.CHECKBOXFILTER, isCheckboxFilter);
		session.setAttribute(ConstantParameter.FILTERCARS, map);
		request.setAttribute(ConstantParameter.COUNTCARS, cars.size());
		request.setAttribute(ConstantParameter.ALLCARS, cars);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.MAIN);
		dispatcher.forward(request, response);
	}
}