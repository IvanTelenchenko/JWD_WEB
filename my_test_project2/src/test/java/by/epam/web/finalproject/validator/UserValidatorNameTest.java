package by.epam.web.finalproject.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import by.epam.web.validator.Validator;


@RunWith(Parameterized.class)
public class UserValidatorNameTest {
	
	private String name;
	boolean result;
	
	private Validator validator = new Validator();
	
	public UserValidatorNameTest(String name, boolean result) {
		this.name = name;
		this.result = result;
	}

	@Parameterized.Parameters
	 public static Collection primeNumbers() {
	      return Arrays.asList(new Object[][] {
	         {"Ivan", true },
	         {"ivan", false },
	         {"Иван", true },
	         {"иВаН", false },
	         {"ИВАН", false },
	         {"Alex", true }
	      });
	   }
	
	@Test
	   public void testValidName() {
	      assertEquals(result, 
	      validator.valiadateName(name));
	   }
}