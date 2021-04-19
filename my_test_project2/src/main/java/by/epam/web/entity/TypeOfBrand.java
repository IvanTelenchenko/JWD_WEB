package by.epam.web.entity;

public enum TypeOfBrand {
	AUDI(1),
	BMW(2),
	FORD(3),
	HONDA(4),
	HYUNDAI(5),
	KIA(6),
	MAZDA(7),
	MERCEDES(8),
	NISSAN(9),
	RENAULT(10),
	SKODA(11),
	TESLA(12),
	TOYOTA(13),
	MINI(14),
	LANDROVER(15),
	INFINITY(16),
	VOLKSWAGEN(17),
	PORSCHE(18);
		
	
	private int brand;
	
	private TypeOfBrand(int brand) {
		this.brand = brand;
	}
	
	public static TypeOfBrand getBrand(int brand) {
		for(TypeOfBrand b : TypeOfBrand.values()) {
			if(b.brand == brand) {
				return b;
			}
		}
		return null;
	}
}
