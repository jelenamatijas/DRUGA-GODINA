package net.etfbl.object;

public class Phone implements Cloneable {
	private Headphones headphones;
	private String manufacturer;
	private String model;

	public Phone() {
		model = "Default";
		manufacturer = "Default";
		headphones = new Headphones("Default");
	}
	
	public Phone(String manufacturer, String model, Headphones h) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.headphones = h;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Headphones sonyHeadphones = new Headphones("Sony");
		Phone s7 = new Phone("Samsung", "S7", sonyHeadphones);
		Phone clone = (Phone) s7.clone();
		System.out.println(clone.manufacturer + " " + clone.model + " " + clone.headphones.getManufacturer());
		sonyHeadphones.setManufacturer("Samsung");
		System.out.println(sonyHeadphones.getManufacturer());
		System.out.println(clone.manufacturer + " " + clone.model + " " + clone.headphones.getManufacturer());
		s7.headphones.setManufacturer("Bose");
		System.out.println(s7.headphones.getManufacturer());
		System.out.println(clone.manufacturer + " " + clone.model + " " + clone.headphones.getManufacturer());
		System.out.println(clone.headphones.hashCode());
		System.out.println(s7.headphones.hashCode());
	}
}
