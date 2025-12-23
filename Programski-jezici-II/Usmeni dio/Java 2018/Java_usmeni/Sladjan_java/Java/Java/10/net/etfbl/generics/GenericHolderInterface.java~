package net.etfbl.generics;

public interface GenericHolderInterface<E> {
	public void set(E object);
	public E get();
}

class GenericHolder2<T> implements GenericHolderInterface<T>{
	private T object;
	
	public T get() {
		return object;
	}

	public void set(T object) {
		this.object = object;
	}
	
	public static void main(String[] args) {
		GenericHolderInterface<Integer> integerHolder = new GenericHolder2<Integer>();
		integerHolder.set(10);
		Integer otherInteger = integerHolder.get();
		System.out.println(otherInteger);
	}
}