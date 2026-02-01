package net.etfbl.object;

import java.lang.reflect.Field;

public class PhoneCloneable implements Cloneable {
	private HeadphonesCloneable headphones;
	private String manufacturer;
	private String model;


	public PhoneCloneable(String manufacturer, String model, HeadphonesCloneable h) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.headphones = h;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
//		return super.clone();
		PhoneCloneable t = (PhoneCloneable) super.clone();
		t.headphones = (HeadphonesCloneable) headphones.clone();
		return t;
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		HeadphonesCloneable sony = new HeadphonesCloneable("Sony");
		PhoneCloneable samsungS8 = new PhoneCloneable("Samsung", "S8", sony);
		PhoneCloneable samsungS8Clone = (PhoneCloneable) samsungS8.clone();
		System.out.println("======== Prije izmjene originala =======");
		System.out.println("Original: " + samsungS8.manufacturer + " " + samsungS8.model + " " + samsungS8.headphones.getManufacturer());
		System.out.println("Clone: " + samsungS8Clone.manufacturer + " " + samsungS8Clone.model + " " + samsungS8Clone.headphones.getManufacturer());
		System.out.println("======== headphones hash code =======");
		System.out.println(samsungS8Clone.headphones.hashCode());
		System.out.println(samsungS8.headphones.hashCode());
		samsungS8.setModel("ABC");
		samsungS8.headphones.setManufacturer("XYZ");
		System.out.println("======== Nakon izmjene originala =======");
		System.out.println("Original: " + samsungS8.manufacturer + " " + samsungS8.model + " " + samsungS8.headphones.getManufacturer());
		System.out.println("Clone: " + samsungS8Clone.manufacturer + " " + samsungS8Clone.model + " " + samsungS8Clone.headphones.getManufacturer());
		System.out.println("======== headphones hash code =======");
		System.out.println(samsungS8Clone.headphones.hashCode());
		System.out.println(samsungS8.headphones.hashCode());
		System.out.println("======== class names =======");
		System.out.println(samsungS8.getClass().getCanonicalName());
		System.out.println(samsungS8.getClass().getSimpleName());
		Field[] f = samsungS8.getClass().getDeclaredFields();
		System.out.println("======== declared fields =======");
		for (Field field : f) {
			System.out.println(field.getName());
		}
	}
}
