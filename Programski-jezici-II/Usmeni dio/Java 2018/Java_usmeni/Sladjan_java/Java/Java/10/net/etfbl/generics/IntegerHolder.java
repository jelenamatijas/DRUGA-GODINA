package net.etfbl.generics;

public class IntegerHolder extends GenericHolder<Integer> {
	
	public void add(Integer i){
		Integer temp = get();
		temp += i;
		set(temp);
	}
	
	public static void main (String args[]){
		IntegerHolder holder = new IntegerHolder();
		holder.set(3);
		holder.add(2);
		System.out.println(holder.get());
	}
}
