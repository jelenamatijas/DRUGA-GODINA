import java.util.*;

class Sekcija{
	Integer id;
	static Integer ID = 1;
	ArrayList<Polica> police;
	
	public Sekcija(){
		id = ID++;
		police = new ArrayList<>();
		for(int i=0; i<3; i++){
			police.add(new Polica());
		}
	}
	
	@Override
	public String toString(){
		String k = "";
		for(Polica polica : police){
			k+= "\t\t" + polica.toString() + "\n";
		}
		return "Sekcija -> ID: " + id + "\n" + k ;
	}
}