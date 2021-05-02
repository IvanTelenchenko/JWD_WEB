package by.epam.web.finalproject.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import by.epam.web.validator.Validator;


@RunWith(Parameterized.class)
public class UserValidatorPhoneTest {
	
	private String phone;
	boolean result;
	
	private Validator validator = new Validator();
	
	
	public UserValidatorPhoneTest(String phone, boolean result) {
		this.phone = phone;
		this.result = result;
	}
	//"^(\\+[0-9]{9,15}|[0-9]{9,15})$";
	@Parameterized.Parameters
	 public static Collection<Object[]> primeNumbers() {
	      return Arrays.asList(new Object[][] {
	         {"375291111111", true },
	         {"1111111", false },
	         {"291111111", true },
	         {"792222", false },
	         {"8033111111111111", false },
	         {"80447321549", true }
	      });
	   }
	
	
	@Test
	   public void testValidPhone() {
	      assertEquals(result, 
	      validator.valiadatePhone(phone));
	   }
}