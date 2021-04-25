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
 * This class represents access for editing car 
 * 
 */

public class EditCar implements Command {
	
	
	private static final String ERROR_MESSAGE_EDIT_CAR = "&errorMessage=error&carid=";
	private static final String MESSAGE_EDIT_CAR = "&messageEditCar=message";
	private static final Logger log = Logger.getLogger(EditCar.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			return;
		}
		
		String id = request.getParameter(ConstantParameter.CARID);
		String brand = request.getParameter(ConstantParameter.CARBRAND);
		String body = request.getParameter(ConstantParameter.CARBODY);
		String classAuto = request.getParameter(ConstantParameter.CARCLASS);
		String transmission = request.getParameter(ConstantParameter.CARTRANS);
		String fuel = request.getParameter(ConstantParameter.CARFUEL);
		
		String name = request.getParameter(ConstantParameter.CARNAME);
		String price = request.getParameter(ConstantParameter.COSTDAY);
		String engineCapacity = request.getParameter(ConstantParameter.ENGINECAPACITY);
		String numbOfSeats = request.getParameter(ConstantParameter.NUMOFSEATS);
		String photoName;
		
		if(request.getAttribute(ConstantParameter.PHOTONAME) == null) {
			photoName = null;
		}else {
			photoName = (String) request.getAttribute(ConstantParameter.PHOTONAME);
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();
		
		try {
			serviceCar.editCar(id, brand, body, transmission, classAuto, fuel, price, name,
					engineCapacity, numbOfSeats, photoName);
		}catch (NotValidDataException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOEDITCAR+ERROR_MESSAGE_EDIT_CAR+id + e.getMessage());
			return;
		}catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		response.sendRedirect(ConstantPage.GOTOCARSBASE+MESSAGE_EDIT_CAR);
	}
}