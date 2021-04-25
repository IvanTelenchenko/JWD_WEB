package by.epam.web.entity;

/**
 * This class consist of an enumeration of the fuel type
 * */

public enum TypeOfFuel {

	GASOLINE(1),
	DIESEL(2),
	ELECTRIC(3),
	HYBRID(4);
	
private int fuel;
	
	private TypeOfFuel(int fuel) {
		this.fuel = fuel;
	}
	
	public static TypeOfFuel getFuel(int fuel) {
		for(TypeOfFuel b : TypeOfFuel.values()) {
			if(b.fuel == fuel) {
				return b;
			}
		}
		return null;
	}
}