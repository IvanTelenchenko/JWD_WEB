package by.epam.web.entity;

public enum TypeOfClass {
	ECONOMY(1),
	MEDIUM(2),
	BUSINESS(3),
	PREMIUM(4);
	
	private int classAuto;
	
	private TypeOfClass(int classAuto) {
		this.classAuto = classAuto;
	}
	
	public static TypeOfClass getClass(int classAuto) {
		for(TypeOfClass b : TypeOfClass.values()) {
			if(b.classAuto == classAuto) {
				return b;
			}
		}
		return null;
	}
}

