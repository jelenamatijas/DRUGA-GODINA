package net.etfbl.oop.package7;

public class Test {

	public static void main(String[] args) {
		DayInWeek monday = DayInWeek.MONDAY;
		System.out.println(monday);
		System.out.println("==========");
		for(DayInWeek day : DayInWeek.values()){
			System.out.println(day);
		}
		System.out.println(DayInWeek.valueOf("MONDAY"));
	}

}
