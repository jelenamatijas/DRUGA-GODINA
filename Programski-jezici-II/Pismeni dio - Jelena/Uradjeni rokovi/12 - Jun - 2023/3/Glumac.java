import java.util.*;

class Glumac{
	String ime, prezime;
	List<String> imena = new ArrayList<>();
	List<String> prezimena = new ArrayList<>();
	
	Glumac(){
		for(int i=1;i<=20;i++){
			imena.add("Ime_" + i);
			prezimena.add("Prezime_" + i);
		}
		int x = Main.rand.nextInt(0,20);
		ime = imena.get(x);
		prezime = prezimena.get(x);
	}
	
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		}
		if(o==null || getClass() != o.getClass()){
			return false;
		}
		Glumac ob = (Glumac)o;
		return ime.equals(ob.ime) && prezime.equals(ob.prezime);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(ime, prezime);
	}

}