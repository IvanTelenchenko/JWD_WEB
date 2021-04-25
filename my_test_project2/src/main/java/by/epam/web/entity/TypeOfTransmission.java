package by.epam.web.entity;

/**
 * This class consist of an enumeration of the transmission type
 * */

public enum TypeOfTransmission {
	MANUAL(1),
	AUTOMATIC(2),
	ROBOT(3);
	
	private int transmission;
	
	private TypeOfTransmission(int transmission){
		this.transmission = transmission;
	}
	
	public static TypeOfTransmission getTransmission(int transmission) {
		for(TypeOfTransmission t : TypeOfTransmission.values()) {
			if(t.transmission == transmission) {
				return t;
			}
		}
		return null;
	}
}