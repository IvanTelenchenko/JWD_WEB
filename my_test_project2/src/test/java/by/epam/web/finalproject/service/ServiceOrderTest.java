package by.epam.web.finalproject.service;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.web.dao.DAOException;
import by.epam.web.dao.util.ConnectionPool;
import by.epam.web.service.ServiceException;
import by.epam.web.service.ServiceOrder;
import by.epam.web.service.ServiceProvider;


public class ServiceOrderTest {
	
	private static ServiceProvider provider;
	private static ServiceOrder serviceOrder;

	
	@BeforeClass
	public static void init() throws DAOException {
		provider = ServiceProvider.getInstance();
		serviceOrder = provider.getServiceOrder();
	}
	
	@AfterClass
	public static void close() {
		ConnectionPool.closeConnection();
	}
	
	 @Test
	   public void testValidGetCar() throws DAOException, ServiceException {
	      assertEquals(false, 
	      serviceOrder.getOrders(true, 12).isEmpty());
	   }
}
