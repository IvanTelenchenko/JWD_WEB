package by.epam.web.validator;

import java.nio.file.Paths;

import javax.servlet.http.Part;

/**
 * This class allows you to validate input data
 * */

public final class Validator {
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$"; 
	private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,20}";
	private static final String NAME_REGEX = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
	private static final String PHONE_REGEX = "^(\\+[0-9]{9,15}|[0-9]{9,15})$";
	private static final String INT_REGEX = "[0-9]+";
	private static final String DOUBLE_REGEX = "[0-9]+([,\\.][0-9]{1,2})?";
	private static final String IMAGE_REGEX = ".+\\.(jpg|jpeg|png)$";
	
	
	/**
	 * Creates an instance of a new Validator
	 * */
	public Validator() {
	}
	
	
	/**
	 * This method allows you to check the email address
	 * @param email a email address
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateEmail(String email) {
		return email.matches(EMAIL_REGEX);
	}
	
	/**
	 * This method allows you to check the password
	 * @param password a password
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadatePassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}
	
	/**
	 * This method allows you to check the name
	 * @param email a name
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateName(String name) {
		return name.matches(NAME_REGEX);
	}
	
	/**
	 * This method allows you to check the phone number
	 * @param phone a phone number
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadatePhone(String phone) {
		return phone.matches(PHONE_REGEX);
	}
	
	/**
	 * This method allows you to check the integer value
	 * @param digit a digit
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateInteger(String digit) {
		return digit.matches(INT_REGEX);
	}
	
	/**
	 * This method allows you to check the double value
	 * @param digit a digit
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateDouble(String digit) {
		return digit.matches(DOUBLE_REGEX);
	}
	
	/**
	 * This method allows you to check the image file
	 * @param image a image file
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateImage(Part image) {
		
		String name = Paths.get(image.getSubmittedFileName()).getFileName().toString();
		
		return name.matches(IMAGE_REGEX);
	}
	
	/**
	 * This method allows you to check the image file
	 * @param image a image file
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateImage(String image) {
		
		return image.matches(IMAGE_REGEX);
	}
	
	/**
	 * This method allows you to check the string for max value
	 * @param srt a string
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateMaxLength100(String str) {
		return str.length() <= 100 ? true : false;
		
	}
	
	/**
	 * This method allows you to check the string for max value
	 * @param srt a string
	 * @return returns true if data has been checked successfully else false
	 * */
	public boolean valiadateMaxLength255(String str) {
		return str.length() <= 255 ? true : false;
	}
}
