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
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceOrder;
import by.epam.web.service.ServiceProvider;

/**
 * This class represents access for completing a order 
 * 
 */

public class CompleteOrder implements Command{
	
	private static final String MESSAGE_COMPLETE_ORDER = "&messageComplete=message";
	
	private static final Logger log = Logger.getLogger(CompleteOrder.class);

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
			log.info("The user isn't admin, because attribute /'admin/' is null or false");
			return;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceOrder serviceOrder = provider.getServiceOrder();
		
		int id = (Integer) Integer.parseInt(request.getParameter(ConstantParameter.ORDER_ID));
		
			try {
				serviceOrder.completeOrder(id);
			} catch (ServiceException e) {
				response.sendRedirect(ConstantPage.GOTOERROR500);
				log.info("The car hasn't deleted");
				return;
			}

		response.sendRedirect(ConstantPage.GOTOORDERHISTORY + MESSAGE_COMPLETE_ORDER);
	}
}
