package org.unibl.etf.pj2.model;

public class Student {

	private String index;
	private double firstYear;
	private double secondYear;
	private double thirdYear;
	private double fourthYear;
	private double average;

	public Student() {
		super();
	}

	public Student(String index, double firstYear, double secondYear, double thirdYear, double fourthYear) {
		super();
		this.index = index;
		this.firstYear = firstYear;
		this.secondYear = secondYear;
		this.thirdYear = thirdYear;
		this.fourthYear = fourthYear;
		this.average = (firstYear+secondYear+thirdYear+fourthYear)/4;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public double getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(double firstYear) {
		this.firstYear = firstYear;
	}

	public double getSecondYear() {
		return secondYear;
	}

	public void setSecondYear(double secondYear) {
		this.secondYear = secondYear;
	}

	public double getThirdYear() {
		return thirdYear;
	}

	public void setThirdYear(double thirdYear) {
		this.thirdYear = thirdYear;
	}

	public double getFourthYear() {
		return fourthYear;
	}

	public void setFourthYear(double fourthYear) {
		this.fourthYear = fourthYear;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Uspjeh u toku studija – " + String.format("%.2f", average);
	}

}
