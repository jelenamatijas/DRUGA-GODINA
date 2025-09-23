public class Student{
	public int brojac;
	private String ime;

	private String prezime;

	private String indeks;

	public Student(){}

	public Student(String ime, String prezime, String indeks, int brojac){
		this.ime = ime;
		this.prezime = prezime;
		this.indeks = indeks;
		this.brojac = brojac;
	}

	public String getime(){
		return this.ime;
	}

	public String getprezime(){
		return this.prezime;
	}

	public String getindeks(){
		return this.indeks;
	}

	private void setime(String ime){
		this.ime = ime;
	}

	private void setprezime(String prezime){
		this.prezime = prezime;
	}

	private void setindeks(String indeks){
		this.indeks = indeks;
	}

	public String toString(){
		return "brojac:" + brojac + "ime:" + ime + "prezime:" + prezime + "indeks:" + indeks ;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null || this.getClass() != obj.getClass()){
			return false;
		}
		Student student = (Student)obj;
		return this.brojac == student.brojac && this.ime.equals(student.ime) && this.prezime.equals(student.prezime) && this.indeks.equals(student.indeks);
	}

}
