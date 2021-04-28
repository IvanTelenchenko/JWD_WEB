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
import by.epam.web.service.exception.ImpossibleExecuteException;
import by.epam.web.service.exception.NotValidDataException;


/**
 *This class represents access for registration.
 * 
 */

public class Registration implements Command{
	
	private static final Logger log = Logger.getLogger(Registration.class);
	private static final String ERROR_MESSAGE_NOT_VALID = "&errorMessage=error";
	private static final String ERROR_MESSAGE_EMAIL_BUSY = "&errorEmailBusy=error";
	private static final String MESSAGE_REG = "&messageReg=message";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		String firstname = request.getParameter(ConstantParameter.FIRSTNAME);
		String lastname = request.getParameter(ConstantParameter.LASTNAME);
		String email = request.getParameter(ConstantParameter.EMAIL);
		String password = request.getParameter(ConstantParameter.PASSWORD);
		String phoneNumber = request.getParameter(ConstantParameter.PHONENUMBER);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceUser serviceReg = provider.getServiceUser();
		
		try {
			serviceReg.registration(firstname, lastname, email, password, phoneNumber);
		} catch (NotValidDataException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOREGISTRATION + ERROR_MESSAGE_NOT_VALID + e.getMessage());
			return;
		}catch (ImpossibleExecuteException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOREGISTRATION + ERROR_MESSAGE_EMAIL_BUSY);
			return;
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		session.setAttribute(ConstantParameter.REG, true);
		response.sendRedirect(ConstantPage.GOTOSIGNIN + MESSAGE_REG);
	}
}