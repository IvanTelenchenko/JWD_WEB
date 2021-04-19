package by.epam.web.finalproject.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import by.epam.web.validator.Validator;


@RunWith(Parameterized.class)
public class UserValidatorPasswordTest {
	
	private String password;
	boolean result;
	
	private Validator validator = new Validator();
	
	
	public UserValidatorPasswordTest(String password, boolean result) {
		this.password = password;
		this.result = result;
	}

	//"(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,20}";
	@Parameterized.Parameters
	 public static Collection primeNumbers() {
	      return Arrays.asList(new Object[][] {
	         {"Password1", true },
	         {"password1", false },
	         {"password", false },
	         {"Password", false },
	         {"paSS12", true },
	         {"12345t", false },
	         {"12345tT", true },
	      });
	   }
	
	
	@Test
	   public void testValidPassword() {
	      assertEquals(result, 
	      validator.valiadatePassword(password));
	   }
}