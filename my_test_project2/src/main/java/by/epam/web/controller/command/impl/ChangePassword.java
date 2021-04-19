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
import by.epam.web.service.exception.NotValidDataException;
import by.epam.web.service.exception.OldPasswordNotEqual;
import by.epam.web.service.exception.PasswordsNotEqual;

/**
 * This class represents access for changing password
 * */

public class ChangePassword implements Command {

	private  static final String ERROR_MESSAGE_OLD_PASS = "&errorMessage=error&messageErrorOldPasswordNotEqual=error";
	private  static final String ERROR_MESSAGE_PASS_NOT_EQUAL = "&errorMessage=error&messageErrorPasswordsNotEqual=error";
	private  static final String ERROR_MESSAGE_PASS_NOT_VALID = "&errorMessage=error&messageErrorNotValid=error";
	private  static final String MESSAGE_CHANGE_PASSWORD = "&messageChangePassword=message";
	
	private static final Logger log = Logger.getLogger(ChangePassword.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Boolean isAuth = (Boolean) session.getAttribute(ConstantParameter.AUTH);

		if (isAuth == null || !isAuth) {
			response.sendRedirect(ConstantPage.GOTOSIGNIN);
			log.info("The session timeout");
			return;
		}
		
		String oldPassword = request.getParameter(ConstantParameter.OLDPASSWORD);
		String newPassword = request.getParameter(ConstantParameter.NEWPASSWORD);
		String newPasswordRepeat = request.getParameter(ConstantParameter.NEWPASSWORDREPEAT);

		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceUser serviceUser = provider.getServiceUser();
		
		int id = (Integer)session.getAttribute(ConstantParameter.USERID);
	
		try {
			serviceUser.changePassword(newPassword,oldPassword, newPasswordRepeat, id);
		} catch (OldPasswordNotEqual e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOCHANGEPASSWORD + ERROR_MESSAGE_OLD_PASS);
			return;
		} catch(PasswordsNotEqual e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOCHANGEPASSWORD + ERROR_MESSAGE_PASS_NOT_EQUAL );
			return;
		}catch (NotValidDataException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOCHANGEPASSWORD + ERROR_MESSAGE_PASS_NOT_VALID);
			return;
		}catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(ConstantPage.GOTOERROR500);
			return;
		}
		
		response.sendRedirect(ConstantPage.GOTOPERSONALDATA + MESSAGE_CHANGE_PASSWORD);
	}
}