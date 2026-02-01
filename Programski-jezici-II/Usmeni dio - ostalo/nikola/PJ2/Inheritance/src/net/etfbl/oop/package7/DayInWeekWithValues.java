package net.etfbl.oop.package7;

public enum DayInWeekWithValues {
    MONDAY (1), 
    TUESDAY (2), 
    WEDNESDAY (3),
    THURSDAY (4), 
    FRIDAY (5), 
    SATURDAY (6), 
    SUNDAY (7);


    private final int dayCode;

    private DayInWeekWithValues(int dayCode) {
        this.dayCode = dayCode;
    }
    
    public static void main(String[] args) {
		for(DayInWeekWithValues day: DayInWeekWithValues.values()){
			System.out.println(day + ": " + day.dayCode);
		}
	}
}