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
import by.epam.web.service.exception.ImpossibleExecuteException;

/**
 * This class represents access for deleting a car
 * 
 */

public class DeleteCar implements Command{
	
	private static final String MESSAGE_DELETE_CAR = "&messageDeleteCar=message";
	private static final String ERROR_MESSAGE_DELETE_CAR = "&errorDeleteCar=error";
	
	private static final Logger log = Logger.getLogger(DeleteCar.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session timeout");
			return;
		}
		
		if(!(Boolean)session.getAttribute(ConstantParameter.ADMIN)) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The  is null or false");
			return;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceCar serviceCar = provider.getServiceCar();

		int id = (Integer) Integer.parseInt(request.getParameter(ConstantParameter.CARID));
		
			try {
				serviceCar.deleteCar(id);
			}catch (ImpossibleExecuteException e) {
				response.sendRedirect(ConstantPage.GOTOCARSBASE+ERROR_MESSAGE_DELETE_CAR);
				log.info(e);
				return;
			} catch (ServiceException e) {
				response.sendRedirect(ConstantPage.GOTOERROR500);
				log.error("The car hasn't been deleted");
				return;
			}
	
		response.sendRedirect(ConstantPage.GOTOCARSBASE+MESSAGE_DELETE_CAR);
	}
}
