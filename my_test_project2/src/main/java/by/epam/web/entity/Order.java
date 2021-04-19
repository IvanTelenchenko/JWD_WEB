package by.epam.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 9003788335741322938L;
	
	int id;
	int userId;
	int carId;
	OrderStatus status;
	Date start;
	Date finish;
	double totalCost;
	
	public Order(int id, int userId, int car, OrderStatus status, Date start, Date finish, double totalCost) {
		super();
		this.id = id;
		this.userId = userId;
		this.carId = car;
		this.status = status;
		this.start = start;
		this.finish = finish;
		this.totalCost = totalCost;
	}
	
	public Order(int id, int userId, int car, int status, Date start, Date finish, double totalCost) {
		super();
		this.id = id;
		this.userId = userId;
		this.carId = car;
		this.status = OrderStatus.getStatus(status);
		this.start = start;
		this.finish = finish;
		this.totalCost = totalCost;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}
	public Date getFinish() {
		return finish;
	}
	
	public void setFinish(Date finish) {
		this.finish = finish;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + ((finish == null) ? 0 : finish.hashCode());
		result = prime * result + id;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userId;
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
		Order other = (Order) obj;
		if (carId != other.carId)
			return false;
		if (finish == null) {
			if (other.finish != null)
				return false;
		} else if (!finish.equals(other.finish))
			return false;
		if (id != other.id)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id + ", " + userId + ", " + carId + ", " + status + ", " + start
				+ ", " + finish+ ", " + totalCost;
	}
}