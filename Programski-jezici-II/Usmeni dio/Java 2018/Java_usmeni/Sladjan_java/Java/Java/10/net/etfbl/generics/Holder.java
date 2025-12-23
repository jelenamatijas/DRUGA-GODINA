package net.etfbl.generics;

public class Holder {
	private Object object;

	public void set(Object object) {
		this.object = object;
	}

	public Object get() {
		return object;
	}

	public static void main(String[] args) {
		Holder integerHolder = new Holder();
		integerHolder.set("10");
		Integer otherInteger = (Integer) integerHolder.get();
		System.out.println(otherInteger);
	}
}
