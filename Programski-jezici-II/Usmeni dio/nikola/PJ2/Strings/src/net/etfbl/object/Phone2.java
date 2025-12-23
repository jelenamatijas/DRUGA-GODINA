package net.etfbl.object;

import java.util.HashSet;
import java.util.Set;

public class Phone2 {
	private String manufacturer;
	private String model;

	public Phone2(String manufacturer, String model) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
	}

	@Override
	public boolean equals(Object mob) {
//		return super.equals(tel);
		Phone2 phone = (Phone2) mob;
		if(manufacturer.equals(phone.manufacturer) && model.equals(phone.model))
			return true;
		return false;
	}
//
//	@Override
//	public int hashCode() {
//		return 10*manufacturer.hashCode() +  model.hashCode();
//	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Phone2 samsung1 = new Phone2("Samsung", "S7");
		Phone2 samsung2 = new Phone2("Samsung", "S7");
		System.out.println(samsung1.equals(samsung2));
		System.out.println(samsung1.hashCode());
		System.out.println(samsung2.hashCode());
		Phone2 nokiaE72 = new Phone2("Nokia", "E72");
		Phone2 xperia = new Phone2("Sony Ericsson", "XPERIA");
		Set<Phone2> telefoni = new HashSet<Phone2>();
		telefoni.add(nokiaE72);
		telefoni.add(xperia);
		telefoni.add(samsung1);
		telefoni.add(samsung2);
		System.out.println(telefoni.size());
		Phone2 p = new Phone2("Nokia", "E72");
		System.out.println(telefoni.contains(p));
		System.out.println(nokiaE72.equals(p));
	}
}