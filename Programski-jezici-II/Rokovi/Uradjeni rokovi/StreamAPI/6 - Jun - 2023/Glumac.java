import java.util.Objects;

class Glumac{
	String ime, prezime;
	public Glumac(){
		int x = Main.rand.nextInt(1, 11);
		ime = "Ime_" + x;
		prezime = "Prezime_" + x;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || this.getClass() != obj.getClass()){
			return false;
		}else{
			Glumac g = (Glumac)obj;
			return (ime.equals(g.ime) && prezime.equals(g.prezime));
		}
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(ime, prezime);
	}
	
	@Override
	public String toString(){
		return "\tGlumac{" + ime + " " + prezime + "}";
	}
}