package by.epam.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import by.epam.web.dao.DAOCar;
import by.epam.web.dao.DAOException;
import by.epam.web.dao.DAOProvider;
import by.epam.web.entity.Car;
import by.epam.web.service.ServiceCar;
import by.epam.web.service.ServiceException;
import by.epam.web.service.exception.ImpossibleExecuteException;
import by.epam.web.service.exception.NotValidDataException;
import by.epam.web.util.XssSecurity;
import by.epam.web.validator.Validator;

public final class ServiceCarImpl implements ServiceCar {

	private static final Logger log = Logger.getLogger(ServiceCarImpl.class);

	@Override
	public List<Car> getCars(Map<String, List<String>> map, List<String> date) throws ServiceException {

		List<Car> cars = new ArrayList<Car>();
		try {
			if (map.size() > 0) {

				cars = getCarsWithFilter(map, date);

			} else {
				cars = getCarsWithoutFilter(date);
			}
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The list of cats nasn't been received");
		}
		return cars;
	}

	public List<Car> getCarsWithFilter(Map<String, List<String>> map, List<String> date) throws DAOException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOCar daoCar = provider.getCar();

		List<Car> cars;
		cars = daoCar.getCarsWithFilter(map, date);
		return cars;
	}

	@Override
	public List<Car> getCarsWithoutFilter(List<String> date) throws ServiceException {

		DAOProvider provider = DAOProvider.getInstance();
		DAOCar daoCar = provider.getCar();

		List<Car> cars;
		try {
			cars = daoCar.getAllCars(date);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The list of cars whit filter hasn't been received");
		}

		return cars;
	}

	@Override
	public Car getCar(int id) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOCar daoCar = provider.getCar();

		Car car;
		try {
			car = daoCar.getCar(id);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The car hasn't been received");
		}

		return car;
	}

	@Override
	public List<Car> getCarsBase() throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOCar daoCar = provider.getCar();

		List<Car> cars;
		try {
			cars = daoCar.getCarsBase();
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The car base hasn't been gotten");
		}
		return cars;
	}

	@Override
	public boolean deleteCar(int id) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOCar daoCar = provider.getCar();

		boolean isDeleteCar = false;

		try {
			isDeleteCar = daoCar.deleteCar(id);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The car hasn't been added");
		}

		if (!isDeleteCar) {
			throw new ImpossibleExecuteException("The car hash't been deleted. Check current orders");
		}

		return isDeleteCar;
	}

	@Override
	public boolean addNewCar(String brand, String body, String transmission, String classAuto, String fuel,
			String price, String name, String engineCapacity, String numbOfSeats, String uniqueNamePhoto)
			throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		DAOCar daoCar = provider.getCar();

		boolean isAddCar = false;

		Validator validator = new Validator();

		StringBuilder messageError = new StringBuilder();

		if (!validator.valiadateMaxLength255(name)) {
			log.error("The string length is more than 255 characters");
			messageError.append("&errorLengthName=error");
		}
		if (!validator.valiadateDouble(price)) {
			log.error("The value doesn't match the double value");
			messageError.append("&errorDoublePrice=error");
		}
		if (!validator.valiadateDouble(engineCapacity)) {
			log.error("The value doesn't match the double value");
			messageError.append("&errorDoubleCapacity=error");
		}

		if (!validator.valiadateInteger(numbOfSeats)) {
			log.error("The value doesn't match the integer value");
			messageError.append("&errorIntegerSeats=error");
		}

		if (!validator.valiadateImage(uniqueNamePhoto) && uniqueNamePhoto != null) {
			log.error("This file is not an image");
			messageError.append("&errorImageNull=error");
		}

		if (messageError.toString().length() > 0) {
			throw new NotValidDataException(messageError.toString());
		}

		try {
			isAddCar = daoCar.addNewCar(brand, body, transmission, classAuto, fuel, price, XssSecurity.xssSecurity(name), engineCapacity,
					numbOfSeats, uniqueNamePhoto);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The car hasn't been added");
		}

		if (!isAddCar) {
			throw new ServiceException("The car hasn't been added");
		}

		return isAddCar;
	}

	@Override
	public boolean editCar(String id, String brand, String body, String transmission, String classAuto, String fuel,
			String price, String name, String engineCapacity, String numbOfSeats, String photoName)
			throws ServiceException {

		DAOProvider provider = DAOProvider.getInstance();
		DAOCar daoCar = provider.getCar();

		boolean isEditCar = false;

		Validator validator = new Validator();

		StringBuilder messageError = new StringBuilder();

		if (!validator.valiadateMaxLength255(name)) {
			log.error("The string length is more than 255 characters");
			messageError.append("&errorLengthName=error");
		}
		if (!validator.valiadateDouble(price)) {
			log.error("The value doesn't match the double value");
			messageError.append("&errorDoublePrice=error");
		}
		if (!validator.valiadateDouble(engineCapacity)) {
			log.error("The value doesn't match the double value");
			messageError.append("&errorDoubleCapacity=error");
		}

		if (!validator.valiadateInteger(numbOfSeats)) {
			log.error("The value doesn't match the integer value");
			messageError.append("&errorIntegerSeats=error");
		}

		if (photoName != null) {
			if (!validator.valiadateImage(photoName)) {
				log.error("This file is not an image");
				messageError.append("&errorImageNull=error");
			}
		}

		if (messageError.toString().length() > 0) {
			throw new NotValidDataException(messageError.toString());
		}

		try {
			isEditCar = daoCar.editCar(id, brand, body, transmission, classAuto, fuel, price, XssSecurity.xssSecurity(name), engineCapacity,
					numbOfSeats, photoName);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("The car hasn't been edited");
		}

		if (!isEditCar) {
			throw new ServiceException("The car hasn't been edited");
		}

		return isEditCar;
	}

	@Override
	public Map<String, List<String>> getMapOfCarFilters(Map<String, String[]> mapFilters) {

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		for (Map.Entry<String, String[]> entry : mapFilters.entrySet()) {
			List<String> value = new ArrayList<String>();
			if (entry.getKey().equals("brand") || entry.getKey().equals("class")) {
				for (String str : entry.getValue()) {
					value.add(str);
				}
				map.put(entry.getKey(), value);
			}
		}
		return map;
	}
}
