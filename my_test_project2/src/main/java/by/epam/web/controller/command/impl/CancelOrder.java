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
 * This class represents access for canceling order
 * */

public class CancelOrder implements Command{
	
	private static final String MESSAGE_CANCEL = "&messageCancel=message";
	
	private static final Logger log = Logger.getLogger(CancelOrder.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session timeout");
			return;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceOrder serviceOrder = provider.getServiceOrder();
		
		boolean isCancelOrder = false;
		
		int id = (Integer) Integer.parseInt(request.getParameter(ConstantParameter.ORDER_ID_CANCEL));
		
		boolean isAdmin = (Boolean)session.getAttribute(ConstantParameter.ADMIN);
		
		try {
			isCancelOrder = serviceOrder.cancelOrder(id, isAdmin);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		if(!isCancelOrder) {
			response.sendRedirect(ConstantPage.GOTOORDERHISTORY);
			return;
		}
		
		response.sendRedirect(ConstantPage.GOTOORDERHISTORY + MESSAGE_CANCEL);
	}
}