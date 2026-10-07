public class Predmet{
	private String naziv;

	protected String sifra;

	public Predmet(){}

	public Predmet(String naziv, String sifra){
		this.naziv = naziv;
		this.sifra = sifra;
	}

	public String getnaziv(){
		return this.naziv;
	}

	public String getsifra(){
		return this.sifra;
	}

	private void setnaziv(String naziv){
		this.naziv = naziv;
	}

	private void setsifra(String sifra){
		this.sifra = sifra;
	}

	public String toString(){
		return "naziv:" + naziv + "sifra:" + sifra ;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null || this.getClass() != obj.getClass()){
			return false;
		}
		Predmet predmet = (Predmet)obj;
		return this.naziv.equals(predmet.naziv) && this.sifra.equals(predmet.sifra);
	}

}
