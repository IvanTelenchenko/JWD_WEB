package by.epam.web.finalproject.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import by.epam.web.validator.Validator;


@RunWith(Parameterized.class)
public class UserValidatorEmailTest {
	
	private String email;
	boolean result;
	
	private Validator validator = new Validator();
	
	
	public UserValidatorEmailTest(String email, boolean result) {
		this.email = email;
		this.result = result;
	}
	
	
//	"^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$"
	@Parameterized.Parameters
	 public static Collection primeNumbers() {
	      return Arrays.asList(new Object[][] {
	         {"ivan@email.com", true },
	         {"ivan@email", false },
	         {"ivan@email.ru", true },
	         {"EMAIL@EMAIL.com", true },
	         {"email.com", false },
	      });
	   }
	
	
	@Test
	   public void testValidEmail() {
	      assertEquals(result, 
	      validator.valiadateEmail(email));
	   }
}