package by.epam.web.entity;

/**
 * This class consist of an enumeration of the car body
 * */

public enum TypeOfBody {

	HATCHBACK(1),
	STATIONWAGON(2),
	SEDAN(3),
	MINIVAN(4),
	CABRIOLET(5),
	OFF_ROAD(6),
	CROSSOVER(7),
	COUPE(8);
	
	private int type;
	
	private TypeOfBody(int type) {
		this.type = type;
	}
	
	public static TypeOfBody getBody(int type) {
		for(TypeOfBody b : TypeOfBody.values()) {
			if(b.type == type) {
				return b;
			}
		}
		return null;
	}
}