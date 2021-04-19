package by.epam.web.entity;

public enum OrderStatus {
//	1	Created(in processing)
//	2	Booked
//	3	Executed
//	4	Canceled(User)
//	5	Canceled(Admin)
	
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


//private static final String PASSWORD_REGEX = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$";
//private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";