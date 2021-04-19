package by.epam.web.dao;

import by.epam.web.dao.impl.DAOCarImpl;
import by.epam.web.dao.impl.DAOOrderImpl;
import by.epam.web.dao.impl.DAOUserImpl;

public final class DAOProvider {
	
	private static final DAOProvider instance = new DAOProvider();
	
	private DAOProvider() {}
	
	
	private static DAOUser daoUser = new DAOUserImpl();
	private static DAOCar daoCar = new DAOCarImpl();
	private static DAOOrder daoOrder = new DAOOrderImpl();
	
	
	public static DAOProvider getInstance() {
		return instance;
	}
	
	public DAOUser getUser() {
		return daoUser;
	}
	
	public DAOCar getCar() {
		return daoCar;
	}
	
	public DAOOrder getOrder() {
		return daoOrder;
	}
}
