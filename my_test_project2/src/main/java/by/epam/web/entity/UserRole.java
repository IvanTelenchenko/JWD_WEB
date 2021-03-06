package by.epam.web.entity;


/**
 * This class consist of an enumeration of the user role
 * */
public enum UserRole {
	ADMIN(1),
	USER(2);
	
	private int role;
	
	private UserRole(int role) {
		this.role = role;
	}
	
	public static UserRole getRole(int role) {
		for(UserRole u : UserRole.values()) {
			if(u.role == role) {
				return u;
			}
		}
		return null;
	}
}