import java.util.Objects;

class Student implements Podatak{
	String ime, prezime, indeks;
	
	public Student(String ime, String prezime, String indeks){
		this.ime = ime;
		this.prezime = prezime;
		this.indeks = indeks;
	}
	
	public String getIme(){return ime;}
	public String getPrezime(){return prezime;}
	public String getIndex(){return indeks;}
	
	public boolean equals(Object obj){
		if(obj == null || this.getClass() != obj.getClass()){
			return false;
		}else if(this == obj){
			return true;
		}else{
			if(ime.equals(((Student)obj).ime) && prezime.equals(((Student)obj).prezime) && indeks.equals(((Student)obj).indeks)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public int hashCode(){
		return Objects.hash(ime, prezime, indeks);
	}
	
	public String toString(){
		return "Student{Ime: " + ime + " Prezime: " + prezime + " Indeks: " + indeks + "}";
	}
}