package net.etfbl.object;

public class HeadphonesCloneable implements Cloneable {
	private String manufacturer;

	public HeadphonesCloneable(String manufacturer) {
		super();
		this.manufacturer = manufacturer;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		HeadphonesCloneable headphones = new HeadphonesCloneable("Sony");
		HeadphonesCloneable headphonesClone = (HeadphonesCloneable) headphones.clone();
		System.out.println(headphones.getManufacturer());
		headphones.setManufacturer("Samsung");
		System.out.println(headphones.getManufacturer());
		System.out.println(headphonesClone.getManufacturer());
	}
}
