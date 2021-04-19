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


/**
 * This class represents access for forwarding to the Error page 404.
 * 
 */

public class GoToError404 implements Command{
	
	private static final Logger log = Logger.getLogger(GoToError404.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session is null");
			return;
		}
		
		Boolean isAuth = (Boolean)session.getAttribute(ConstantParameter.AUTH);
		
		if(isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOMAINPAGE);
			log.info("The session is null");
			return;
		}	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantPage.ERROR404);
		dispatcher.forward(request, response);
	}
}