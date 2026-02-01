package net.etfbl.object;

public class Phone3 {
	Headphones headphones;
	String manufacturer;
	String model;

	public Phone3() {
		model = "Default";
		manufacturer = "Default";
		headphones = new Headphones("Default");
	}
	
	public Phone3(String manufacturer, String model, Headphones h) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.headphones = h;
	}

	public static void main(String[] args) {
		Headphones sony = new Headphones("Sony");
		Phone3 nokiaE72 = new Phone3("Nokia", "E72", sony);
		System.out.println(nokiaE72.toString());
		System.out.println(nokiaE72.getClass().getName() + "@" + Integer.toHexString(nokiaE72.hashCode()));
		Phone4 nokiaE72_2 = new Phone4("Nokia", "E72", sony);
		System.out.println(nokiaE72_2);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}

class Phone4 extends Phone3{
	public Phone4(String manufacturer, String model, Headphones h) {
		super(manufacturer, model, h);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return manufacturer + ": " + model;
	}
}
