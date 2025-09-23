class Student{
	String index;
	int brojPolozenih;
	double prosjek;
	String detalji;
	
	public Student(String index, int brojPolozenih, double prosjek, String detalji){
		super();
		this.index = index;
		this.brojPolozenih = brojPolozenih;
		this.prosjek = prosjek;
		this.detalji = detalji;
	}
	
	@Override
	public String toString(){
		return "Student -> Indeks: " + index + " Broj polozenih ispita: " + brojPolozenih + " Prosjek: " + prosjek + " Detalji: \n\t\t" + detalji; 
	}
}