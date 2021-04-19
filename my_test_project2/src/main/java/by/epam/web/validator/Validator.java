package by.epam.web.validator;

import java.nio.file.Paths;

import javax.servlet.http.Part;

public class Validator {
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$"; 
	private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,20}";
	private static final String NAME_REGEX = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
	private static final String PHONE_REGEX = "^(\\+[0-9]{9,15}|[0-9]{9,15})$";
	private static final String INT_REGEX = "[0-9]+";
	private static final String DOUBLE_REGEX = "[0-9]+([,\\.][0-9]{1,2})?";
	private static final String IMAGE_REGEX = ".+\\.(jpg|jpeg|png)$";

	public boolean valiadateEmail(String email) {
		return email.matches(EMAIL_REGEX);
	}
	
	public boolean valiadatePassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}
	
	public boolean valiadateName(String name) {
		return name.matches(NAME_REGEX);
	}
	
	public boolean valiadatePhone(String phone) {
		return phone.matches(PHONE_REGEX);
	}
	
	public boolean valiadateInteger(String digit) {
		return digit.matches(INT_REGEX);
	}
	
	public boolean valiadateDouble(String digit) {
		return digit.matches(DOUBLE_REGEX);
	}
	
	public boolean valiadateImage(Part image) {
		
		String name = Paths.get(image.getSubmittedFileName()).getFileName().toString();
		
		return name.matches(IMAGE_REGEX);
	}
	
	public boolean valiadateMaxLength45(String str) {
		return str.length() <= 45 ? true : false;
	}
	public boolean valiadateMaxLength100(String str) {
		return str.length() <= 100 ? true : false;
		
	}	public boolean valiadateMaxLength255(String str) {
		return str.length() <= 255 ? true : false;
	}

	
	
	


}
