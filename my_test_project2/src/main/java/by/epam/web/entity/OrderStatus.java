package by.epam.web.entity;

/**
 * This class consist of an enumeration of the order status
 * */

public enum OrderStatus {
	CREATED(1),
	BOOKED(2),
	COMPLETED(3),
	CANCELEDUSER(4),
	CANCELEDADMIN(5);
	
private int type;
	
	private OrderStatus(int type) {
		this.type = type;
	}
	
	public static OrderStatus getStatus(int type) {
		for(OrderStatus b : OrderStatus.values()) {
			if(b.type == type) {
				return b;
			}
		}
		return null;
	}
	
}
