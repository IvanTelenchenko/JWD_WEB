package by.epam.web.finalproject.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import by.epam.web.dao.DAOException;
import by.epam.web.dao.util.ConnectionPool;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceProvider;
import by.epam.web.service.ServiceUser;

@RunWith(Parameterized.class)
public class ServiceUserTest {
	
	private static ServiceProvider provider;
	private static ServiceUser serviceUser;

	private Integer id;
	boolean result;
	
	public ServiceUserTest(Integer id, boolean result) {
		this.id = id;
		this.result = result;
	}
	
	@Parameterized.Parameters
	 public static Collection<Object[]> primeNumbers() {
	      return Arrays.asList(new Object[][] {
	         {1, false },
	         {5, false },
	         {3, false },
	      });
	   }
	
	@BeforeClass
	public static void init() throws DAOException {
		provider = ServiceProvider.getInstance();
		serviceUser = provider.getServiceUser();
	}
	
	@AfterClass
	public static void close() {
		ConnectionPool.closeConnection();
	}
	
	 @Test
	   public void testValidGetCar() throws DAOException, ServiceException {
	      assertEquals(result, 
	      serviceUser.getUser(id).equals(null));
	   }
}
