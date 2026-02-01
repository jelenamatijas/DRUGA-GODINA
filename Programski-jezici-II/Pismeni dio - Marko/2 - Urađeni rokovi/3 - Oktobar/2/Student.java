public class Student {

	public int brojac;

	private String ime;

	private String prezime;

	private String indeks;

	public Student() {}

	public Student(String ime, String prezime, String indeks, int brojac) {
		this.ime = ime;
		this.prezime = prezime;
		this.indeks = indeks;
		this.brojac = brojac;
	}

	public void setime(String ime) {
		this.ime = ime;
	}

	public void setprezime(String prezime) {
		this.prezime = prezime;
	}

	public void setindeks(String indeks) {
		this.indeks = indeks;
	}

	public String getime() {
		return this.ime;
	}
	public String getprezime() {
		return this.prezime;
	}
	public String getindeks() {
		return this.indeks;
	}
	@Override
	public String toString(){
		return "Student:" + "ime=" + this.ime + ", " + "prezime=" + this.prezime + ", " + "indeks=" + this.indeks + ", " + "brojac=" + this.brojac;

	}
	@Override
	public boolean equals(Object object) {
		if (this.getClass() == object.getClass()) {
			Student student = (Student) object;
			return this.ime.equals(student.ime) && this.prezime.equals(student.prezime) && this.indeks.equals(student.indeks) && this.brojac == student.brojac;
		}
		return false;
	}
}
