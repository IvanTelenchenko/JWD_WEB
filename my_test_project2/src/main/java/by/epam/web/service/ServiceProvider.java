package by.epam.web.service;

import by.epam.web.service.impl.ServiceCarImpl;
import by.epam.web.service.impl.ServiceOrderImpl;
import by.epam.web.service.impl.ServiceUserImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {}
	
	private final ServiceUser serviceUser = new ServiceUserImpl();
	private final ServiceCar serviceCar = new ServiceCarImpl();
	private final ServiceOrder serviceOrder = new ServiceOrderImpl();
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public ServiceUser getServiceUser() {
		return serviceUser;
	}
	public ServiceCar getServiceCar() {
		return serviceCar;
	}
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}
}