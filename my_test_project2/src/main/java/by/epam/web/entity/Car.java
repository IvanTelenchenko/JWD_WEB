package by.epam.web.entity;

import java.io.Serializable;


/**
 * This class represents the Car entity
 * */

public final class Car implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private TypeOfBrand brand;
	private TypeOfBody body;
	private TypeOfTransmission transmission;
	private TypeOfClass classAuto;
	private TypeOfFuel fuel;
	private double price;
	private String name;
	private double engineCapacity;
	private int numbOfSeats;
	private String photo;
	
	
	/**
	 * Creates an instance of a new {@link Car}
	 * */
	public Car() {
	}
	
	/**
	 * Creates an instance of a new {@link Car}
	 * @param id Car ID
	 * @param brand brand type ({@link TypeOfBrand})
	 * @param body body type ({@link TypeOfBody})
	 * @param transmission transmission type ({@link TypeOfTransmission})
	 * @param classAuto class car type ({@link TypeOfClass})
	 * @param fuel fuel type ({@link TypeOfFuel})
	 * @param price the cost per day of booking a car
	 * @param name full name car
	 * @param engineCapacity car engine power
	 * @param numbOfSeats number of seats 
	 * */
	
	public Car(int id, TypeOfBrand brand, TypeOfBody body, TypeOfTransmission transmission, TypeOfClass classAuto,
			TypeOfFuel fuel, double price, String name, double engineCapacity, int numbOfSeats, String photo) {
		this.id = id;
		this.brand = brand;
		this.body = body;
		this.transmission = transmission;
		this.classAuto = classAuto;
		this.fuel = fuel;
		this.price = price;
		this.name = name;
		this.engineCapacity = engineCapacity;
		this.numbOfSeats = numbOfSeats;
		this.photo = photo;
	}
	
	
	/**
	 * Creates an instance of a new {@link Car}
	 * @param id Car ID
	 * @param brand brand type
	 * @param body body type
	 * @param transmission transmission type
	 * @param classAuto class car type
	 * @param fuel fuel type
	 * @param price the cost per day of booking a car
	 * @param name full name car
	 * @param engineCapacity car engine power
	 * @param numbOfSeats number of seats 
	 * */
	public Car(int id, int brand, int body, int transmission, int classAuto, int fuel, double price, String name,
			double engineCapacity, int numbOfSeats, String photo) {
		
		this.id = id;
		this.brand = TypeOfBrand.getBrand(brand);
		this.body = TypeOfBody.getBody(body);
		this.transmission = TypeOfTransmission.getTransmission(transmission);
		this.classAuto = TypeOfClass.getClass(classAuto);
		this.fuel = TypeOfFuel.getFuel(fuel);
		this.price = price;
		this.name = name;
		this.engineCapacity = engineCapacity;
		this.numbOfSeats = numbOfSeats;
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TypeOfBrand getBrand() {
		return brand;
	}

	public void setBrand(TypeOfBrand brand) {
		this.brand = brand;
	}

	public TypeOfBody getBody() {
		return body;
	}

	public void setBody(TypeOfBody body) {
		this.body = body;
	}

	public TypeOfTransmission getTransmission() {
		return transmission;
	}

	public void setTransmission(TypeOfTransmission transmission) {
		this.transmission = transmission;
	}

	public TypeOfClass getClassAuto() {
		return classAuto;
	}

	public void setClassAuto(TypeOfClass classAuto) {
		this.classAuto = classAuto;
	}

	public TypeOfFuel getFuel() {
		return fuel;
	}

	public void setFuel(TypeOfFuel fuel) {
		this.fuel = fuel;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(double engiveCapacity) {
		this.engineCapacity = engiveCapacity;
	}

	public int getNumbOfSeats() {
		return numbOfSeats;
	}

	public void setNumbOfSeats(int numbOfSeats) {
		this.numbOfSeats = numbOfSeats;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((classAuto == null) ? 0 : classAuto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(engineCapacity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numbOfSeats;
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (body != other.body)
			return false;
		if (brand != other.brand)
			return false;
		if (classAuto != other.classAuto)
			return false;
		if (Double.doubleToLongBits(engineCapacity) != Double.doubleToLongBits(other.engineCapacity))
			return false;
		if (fuel != other.fuel)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numbOfSeats != other.numbOfSeats)
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (transmission != other.transmission)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ", " + brand + ", " + body + ", " + transmission
				+ ", " + classAuto + ", " + fuel + ", " + price +", " + name
				+ ", " + engineCapacity + ", " + numbOfSeats + ", " + photo;
	}	
}