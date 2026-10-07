package net.etfbl.oop.package7;

public enum DayInWeekWithConstructor {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

	int x;

	public void metoda() {
		System.out.println("metoda");
	}
	
	private DayInWeekWithConstructor() {
		System.out.println("konstruktor...");
	}
	
	public static void main(String[] args) {
		DayInWeekWithConstructor dayInWeekWithConstructor = DayInWeekWithConstructor.FRIDAY;
		dayInWeekWithConstructor.metoda();
	}
}
