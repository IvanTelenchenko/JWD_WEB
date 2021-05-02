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
import by.epam.web.service.ServiceCar;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceProvider;


@RunWith(Parameterized.class)
public class ServiceCarTest {
	
	private static ServiceProvider provider;
	private static ServiceCar serviceCar;
	
	private Integer id;
	boolean result;
	
	@BeforeClass
	public static void init() throws DAOException {
		provider = ServiceProvider.getInstance();
		serviceCar = provider.getServiceCar();
	}
	
	@AfterClass
	public static void close() {
		ConnectionPool.closeConnection();
	}
	
	public ServiceCarTest(Integer id, boolean result) {
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
	
	 @Test
	   public void testValidGetCar() throws ServiceException {
	      assertEquals(result, 
	      serviceCar.getCar(id).equals(null));
	   }
}
