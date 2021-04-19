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
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.service.ServiceUser;
import by.epam.web.service.exception.ObjectNullPointerException;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceProvider;


/**
 * This class represents access for authorization
 * 
 * */

public class Authorization implements Command {
	
	private static final String ERROR_MESSAGE = "&messageError=Email or password has entered incorrectly";
	
	private static final Logger log = Logger.getLogger(Authorization.class);
	
	boolean isAdmin = false;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter(ConstantParameter.EMAIL);
		String password = request.getParameter(ConstantParameter.PASSWORD);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceUser serviceUser = provider.getServiceUser();
		
		User user;
		
		try {
			user = serviceUser.authorization(email, password);
		} catch (ObjectNullPointerException e) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN + ERROR_MESSAGE);
			log.info("Email or password has entered incorrectly");
			return;
		} catch (ServiceException e) {
			response.sendRedirect(ConstantPage.GOTOERROR500);
			log.error("User hasn't been authorized");
			return;
		}
		
		
		if(user.getRole().equals(UserRole.ADMIN) && UserRole.ADMIN != null) {
			isAdmin = true;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute(ConstantParameter.AUTH, true);
		session.setAttribute(ConstantParameter.USERID, user.getId());
		session.setAttribute(ConstantParameter.ADMIN, isAdmin);
		response.sendRedirect(ConstantPage.GOTOMAINPAGE);
		log.info("User has been authorized");
	}
}