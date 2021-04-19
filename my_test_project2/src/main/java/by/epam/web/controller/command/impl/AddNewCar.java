package by.epam.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.web.controller.command.Command;
import by.epam.web.controller.constant.ConstantPage;
import by.epam.web.controller.constant.ConstantParameter;
import by.epam.web.service.ServiceCar;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceProvider;
import by.epam.web.service.exception.NotValidDataException;

/**
 * This class represent access for adding a new car
 * */

public class AddNewCar implements Command {

	private static final String ERROR_MESSAGE = "&errorMessage=error&errorLength=error&errorDouble=error&errorInteger=error";
	private static final String MESSAGE_ADD_CAR = "&messageAddCar=message";
	
	private static final Logger log = Logger.getLogger(AddNewCar.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			return;
		}
		
		String brand = request.getParameter(ConstantParameter.CARBRAND );
		String body = request.getParameter(ConstantParameter.CARBODY);
		String classAuto = request.getParameter(ConstantParameter.CARCLASS);
		String transmission = request.getParameter(ConstantParameter.CARTRANS);
		String fuel = request.getParameter(ConstantParameter.CARFUEL);
		
		String name = request.getParameter(ConstantParameter.CARNAME);
		String price = request.getParameter(ConstantParameter.COSTDAY);
		String engineCapacity = request.getParameter(ConstantParameter.ENGINECAPACITY);
		String numbOfSeats = request.getParameter(ConstantParameter.NUMOFSEATS);
		String photoName = (String) request.getAttribute(ConstantParameter.PHOTONAME);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();
		
		
		try {
			serviceCar.addNewCar(brand, body, transmission, classAuto, fuel, price, name,
					engineCapacity, numbOfSeats, photoName);
		}catch (NotValidDataException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOADDCAR + ERROR_MESSAGE);
			return;
		}catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		response.sendRedirect(ConstantPage.GOTOCARSBASE + MESSAGE_ADD_CAR);
		log.info("New car has been added");
	}
}