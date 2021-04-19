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
import by.epam.web.service.ServiceProvider;
import by.epam.web.service.ServiceUser;
import by.epam.web.service.exception.DuplicateEmailException;
import by.epam.web.service.exception.NotValidDataException;

/**
 * This class represents access for changing personal data 
 * 
 */

public class ChangePD implements Command {

	private static final String ERROR_MESSAGE_NOT_VALID = "&errorMessage=error&"
					+ "errorEmail=error&errorFirstname=error&errorLastname=error&"
					+ "errorPhone=error&errorPassword=error";
	private static final String ERROR_MESSAGE_DUPLICATE = "&errorMessage=error&errorDuplicate=error";
	private static final String MESSAGE_CHANGE_PD = "&messageChangePD=message";
	
	
	private static final Logger log = Logger.getLogger(ChangePD.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session timeout");
			return;
		}
		
		String firstname = request.getParameter(ConstantParameter.FIRSTNAME);
		String lastname = request.getParameter(ConstantParameter.LASTNAME);
		String email = request.getParameter(ConstantParameter.EMAIL);
		String phone = request.getParameter(ConstantParameter.PHONE);

		int id = (Integer)session.getAttribute(ConstantParameter.USERID);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceUser serviceUser = provider.getServiceUser();
		
		try {
			serviceUser.updatePD(firstname, lastname, email, phone, id);
		}catch (NotValidDataException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.CHANGEPD + ERROR_MESSAGE_NOT_VALID);
			return;
		}catch (DuplicateEmailException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.CHANGEPD + ERROR_MESSAGE_DUPLICATE);
			return;
		}	
		catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		response.sendRedirect(ConstantPage.GOTOPERSONALDATA+MESSAGE_CHANGE_PD);
	}
}