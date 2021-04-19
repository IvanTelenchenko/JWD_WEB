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
import by.epam.web.entity.User;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceProvider;
import by.epam.web.service.ServiceUser;


/**
 * This class represents access for forwarding to the Personal data page.
 * 
 */

public class GoToPersonalDataPage implements Command {

	private static final Logger log = Logger.getLogger(GoToPersonalDataPage.class);

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
		ServiceUser serviceUser = provider.getServiceUser();
		
		int id = (Integer) session.getAttribute(ConstantParameter.USERID);
		
		User user;
		try {
			user = serviceUser.getUser(id);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		request.setAttribute(ConstantParameter.USER, user);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.PERSONALDATA);
		dispatcher.forward(request, response);
	}
}
