package by.epam.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.web.dao.DAOException;
import by.epam.web.dao.DAOProvider;
import by.epam.web.dao.DAOUser;
import by.epam.web.entity.User;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceUser;
import by.epam.web.service.exception.DuplicateEmailException;
import by.epam.web.service.exception.ImpossibleExecuteException;
import by.epam.web.service.exception.NotValidDataException;
import by.epam.web.service.exception.ObjectNullPointerException;
import by.epam.web.service.exception.OldPasswordNotEqual;
import by.epam.web.service.exception.PasswordsNotEqual;
import by.epam.web.validator.Validator;

public class ServiceUserImpl implements ServiceUser {
	
	private static final Logger log = Logger.getLogger(ServiceUserImpl.class);

	@Override
	public User authorization(String email, String password) throws ServiceException {

		DAOProvider provider = DAOProvider.getInstance();
		DAOUser daoUser = provider.getUser();
		
		User user;
		try {
			user = daoUser.authorization(email, password);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Authorization wasn't executed");
		}
		
		if(user == null) {
			throw new ObjectNullPointerException("The user is null");
		}
		
		return user;
	}

	@Override
	public boolean registration(String firstname, String lastname, String email, String password, String phoneNumber) throws ServiceException {

		DAOProvider provider = DAOProvider.getInstance();
		DAOUser daoUser = provider.getUser();
		
		boolean isReg = false;
		
		Validator validator = new Validator();
		
		StringBuilder messageError = new StringBuilder();
		
//		if(!validator.valiadateEmail(email) || !validator.valiadateMaxLength100(email)) {
//			log.info("Email has been entered incorrectly");
//			throw new NotValidDataException("Email has been entered incorrectly");
//		}
//
//		if(!validator.valiadateName(firstname)) {
//			log.info("Firstname has been entered incorrectl");
//			throw new NotValidDataException("Firstname has been entered incorrectl");
//		}
//		
//		if(!validator.valiadateName(lastname)) {
//			log.info("Lastname has been entered incorrectly");
//			throw new NotValidDataException("Lastname has been entered incorrectly");
//		}
//		
//		if(!validator.valiadatePhone(phoneNumber)) {
//			log.info("Phone number has been entered incorrectly");
//			throw new NotValidDataException("Phone number has been entered incorrectly");
//		}
//		
//		if(!validator.valiadatePassword(password)) {
//			log.info("Phone number has been entered incorrectly");
//			throw new NotValidDataException("Phone number has been entered incorrectly");
//		}
		
//		private static final String ERROR_MESSAGE_NOT_VALID = "&errorMessage=error&"
//				+ "errorEmail=error&errorFirstname=error&errorLastname=error&"
//				+ "errorPhone=error&errorPassword=error";
		
		if (!validator.valiadateEmail(email) || !validator.valiadateMaxLength100(email)) {
			log.info("Email has been entered incorrectly");
//			throw new NotValidDataException("Email has been entered incorrectly");
			messageError.append("&errorEmail=error");
		}

		if (!validator.valiadateName(firstname)) {
			log.info("Firstname has been entered incorrectl");
//			throw new NotValidDataException("Firstname has been entered incorrectl");
			messageError.append("&errorFirstname=error");
		}

		if (!validator.valiadateName(lastname)) {
			log.info("Lastname has been entered incorrectly");
//			throw new NotValidDataException("Lastname has been entered incorrectly");
			messageError.append("&errorLastname=error");
		}

		if (!validator.valiadatePhone(phoneNumber)) {
			log.info("Phone number has been entered incorrectly");
//			throw new NotValidDataException("Phone number has been entered incorrectly");
			messageError.append("&errorPhone=error");
		}

		if (!validator.valiadatePassword(password)) {
			log.info("Phone number has been entered incorrectly");
//			throw new NotValidDataException("Phone number has been entered incorrectly");
			messageError.append("&errorPassword=error");
		}
		
		if(messageError.toString().length() > 0) {
			throw new NotValidDataException(messageError.toString());
		}
		
		try {
			isReg = daoUser.registration(firstname, lastname, email, password, phoneNumber);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Registration wasn't executed");
		}
		
		if(!isReg) {
			throw new ImpossibleExecuteException("This email address already has been using");
		}
		
		return isReg;
	}

	
	public String getPassword(int id) {
		
		DAOProvider provider = DAOProvider.getInstance();
		DAOUser daoUser = provider.getUser();
		
		String password = null;
		try {
			password = daoUser.getPassword(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}

	@Override
	public boolean changePassword(String newPassword, String oldPassword,String newPasswordRepeat, int id) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOUser daoUser = provider.getUser();
		
		Validator validator = new Validator();
		
//		StringBuilder messageError = new StringBuilder();
		
		if(!validator.valiadatePassword(oldPassword)) {
//			validMessage.put("validOldPassword", "Password has been entered incorrectly (Example: Password1, 123pasSword). Min length 6 letters and max length 20 letters");
			log.info("Old password has been entered incorrectly");
			throw new NotValidDataException("&messageErrorNotValidOldPassword=error");
		}
		
		if(!validator.valiadatePassword(newPassword)) {
//			validMessage.put("validNewPassword", "Password has been entered incorrectly (Example: Password1, 123pasSword). Min length 6 letters and max length 20 letters");
			log.info("New password has been entered incorrectly");
			throw new NotValidDataException("&messageErrorNotValidNewPassword=errory");
//			messageError.append("&messageErrorNotValidNewPassword=error");
		}
		
//		if(!newPassword.equals(newPasswordRepeat)){
////			validMessage.put("validPasswordsEqual", "New password and new password repeat are't equals");
//			log.info("New password has been entered incorrectly");
//			throw new NotValidDataException("New password has been entered incorrectly");
//		}
		
		boolean isChangePassword = false;
		
		if(oldPassword.equals(getPassword(id))){
			if(newPassword.equals(newPasswordRepeat)){
				try {
					isChangePassword = daoUser.changePassword(newPassword,id);
				} catch (DAOException e) {
					log.error(e);
					throw new ServiceException("Password wasn't changed");
				}
			}else {
				throw new PasswordsNotEqual("The new passwords are not equal");
			}
		}else{
			throw new OldPasswordNotEqual("The old password has been enterred incorrectly");
		}
		
		if(!isChangePassword) {
			throw new ServiceException();
//			response.sendRedirect("Controller?command=error&messageErrorChangePassword=The password hasn't been changed");
//			log.error("The password hasn't been changed");
//			return;
		}
		
		
//		if(validMessage.size() > 0) {
//			request.setAttribute("validMessage",validMessage);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ChangePassword.jsp");
//			dispatcher.forward(request, response);
//			return;
//		}
		
		return isChangePassword;
	}

	public boolean checkEmail(String email, int id) throws DAOException {
		
		DAOProvider provider = DAOProvider.getInstance();
		DAOUser daoUser = provider.getUser();
		
		return daoUser.checkEmail(email, id);
	}

	@Override
	public boolean updatePD(String firstname, String lastname, String email, String phone, int id) throws ServiceException {
		
		DAOProvider provider = DAOProvider.getInstance();
		DAOUser daoUser = provider.getUser();	
		
		boolean isChangePD = false;
		
		Validator validator = new Validator();
		
		StringBuilder messageError = new StringBuilder();
		
		if (!validator.valiadateEmail(email) || !validator.valiadateMaxLength100(email)) {
			log.info("Email has been entered incorrectly");
//			throw new NotValidDataException("Email has been entered incorrectly");
			messageError.append("&errorEmail=error");
		}

		if (!validator.valiadateName(firstname)) {
			log.info("Firstname has been entered incorrectl");
//			throw new NotValidDataException("Firstname has been entered incorrectl");
			messageError.append("&errorFirstname=error");
		}

		if (!validator.valiadateName(lastname)) {
			log.info("Lastname has been entered incorrectly");
//			throw new NotValidDataException("Lastname has been entered incorrectly");
			messageError.append("&errorLastname=error");
		}

		if (!validator.valiadatePhone(phone)) {
			log.info("Phone number has been entered incorrectly");
//			throw new NotValidDataException("Phone number has been entered incorrectly");
			messageError.append("&errorPhone=error");
		}

		
		if(messageError.toString().length() > 0) {
			throw new NotValidDataException(messageError.toString());
		}
		
		
		
//		if(!validator.valiadateEmail(email) || !validator.valiadateMaxLength100(email)) {
////			validMessage.put("validEmail", "The email has been entered incorrectly (Example: email@email.com). Max length 100 letters.");
//			log.info("Email has been entered incorrectly");
//			throw new NotValidDataException("Email has been entered incorrectly");
//		}
//
//		if(!validator.valiadateName(firstname)) {
////			validMessage.put("validFirstname", "Firstname has been entered incorrectly (Example: Ivanov,Иванов ). Max length 23 letters.");
//			log.info("Firstname has been entered incorrectl");
//			throw new NotValidDataException("Firstname has been entered incorrectl");
//		}
//		
//		if(!validator.valiadateName(lastname)) {
////			validMessage.put("validLastname", "Lastname has been entered incorrectly (Example: Ivan,Иван). Max length 23 letters.");
//			log.info("Lastname has been entered incorrectly");
//			throw new NotValidDataException("Lastname has been entered incorrectly");
//		}
//		
//		if(!validator.valiadatePhone(phone)) {
////			validMessage.put("validPhone", "Phone number has been entered incorrectly (Example: +375447621975, 80447621975). Min length 9 letters and max length 15 letters");
//			log.info("Phone number has been entered incorrectly");
//			throw new NotValidDataException("Phone number has been entered incorrectly");
//		}
		
		try {
			if(!checkEmail(email, id)){
				log.error("This email address already has been registered");
				throw new DuplicateEmailException("This email address already has been registered");
//			response.sendRedirect("Controller?command=gotochangepd&messageError=The email address has already registered ");
//			return;
			}
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Email wasn't checked");
		}
		
		try {
			isChangePD = daoUser.updatePD(firstname, lastname, email, phone, id);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The personal data haven't been changed");
		}
		
		if(!isChangePD) {
			log.error("The personal data haven't been changed");
			throw new ServiceException("The personal data haven't been changed");
//			response.sendRedirect("Controller?command=error&messageErrorChangePassword=The personal data haven't been changed");
//			return;
		}

		
		return isChangePD;
	}

	@Override
	public User getUser(int id) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOUser daoUser = provider.getUser();

		User user;
		try {
			user = daoUser.getUser(id);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("User hasn't been received");
		}
		return user;
	}

	@Override
	public List<String> getDefaultDate() {
		
		List<String> date = new ArrayList<String>();
		
		Date dateStart = new Date();
		Date dateEnd = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		dateStart = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		dateEnd = calendar.getTime();
	
//		System.out.println("str" + dateStart);
//		System.out.println("end" + dateEnd);
		
		date.add(formatDate.format(dateStart));
		date.add(formatDate.format(dateEnd));
		
		return date;
	}

	@Override
	public List<String> getUserDate(String dateStart, String dateEnd) {
		
		List<String> dateUser = new ArrayList<String>();
		
		if(dateStart.compareTo(dateEnd) > 0) {
			dateUser.add(dateEnd);
			dateUser.add(dateStart);
		}else {
			dateUser.add(dateStart);
			dateUser.add(dateEnd);
		}
		
		return dateUser;
	}
}