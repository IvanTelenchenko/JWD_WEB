package by.epam.web.entity;

import java.io.Serializable;

/**
 * This class represents the User entity
 * */

public final class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private UserRole role;
	
	
	/**
	 * Creates an instance of a new {@link User}
	 * */
	public User() {
	}
	
	/**
	 * Creates an instance of a new {@link User}
	 * @param id user ID
	 * @param email user's email address 
	 * @param password user account password
	 * @param firstname user name
	 * @param lastname user surname
	 * @param phoneNumber user's phone number
	 * @param role user role {@link UserRole}
	 * */
	public User(int id, String email, String password, String firstname, String lastname,  String phoneNumber, UserRole role) {
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
	
	/**
	 * Creates an instance of a new {@link User}
	 * @param id user ID
	 * @param email user's email address 
	 * @param password user account password
	 * @param firstname user name
	 * @param lastname user surname
	 * @param phoneNumber user's phone number
	 * @param role user role
	 * */
	public User(int id, String email, String password, String firstname, String lastname, String phoneNumber, int role) {
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = UserRole.getRole(role);
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ", " + firstName + ", " + lastName + ", " + email
				+ ", " +password + ", " + phoneNumber + ", " + role;
	}
}