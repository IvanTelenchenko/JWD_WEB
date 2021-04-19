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


/**
 * This class represents access for log out.
 * 
 */

public class Logout implements Command{
	
	private static final Logger log = Logger.getLogger(Logout.class);
	private static final String MESSAGE_LOGOUT = "&message=logout ok";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session != null) {
			session.removeAttribute(ConstantParameter.AUTH);
			log.info("The session \"auth\" has been closed");
		}
		response.sendRedirect(ConstantPage.GOTOSIGNIN + MESSAGE_LOGOUT);	
	}
}