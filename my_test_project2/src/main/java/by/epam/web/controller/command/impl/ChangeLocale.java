package by.epam.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.web.controller.command.Command;
import by.epam.web.controller.constant.ConstantParameter;

/**
 * This class represents access for changing localization
 * 
 * */


public class ChangeLocale implements Command{
	
	private static final Logger log = Logger.getLogger(ChangeLocale.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute(ConstantParameter.LOCALE, request.getParameter(ConstantParameter.LOCAL));
		
		response.sendRedirect(request.getHeader(ConstantParameter.REFERER));
		System.out.println(request.getHeader(ConstantParameter.REFERER));
		log.info("The locale has been changed " + request.getParameter(ConstantParameter.LOCAL));
	}
}