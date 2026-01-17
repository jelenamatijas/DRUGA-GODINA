import java.util.*;

class Polica{
	Integer id;
	ArrayList<Knjiga> knjige;
	static Integer ID = 1;
	
	public Polica(){
		knjige = new ArrayList<>();
		for(int i=0; i<Main.rand.nextInt(6)+1; i++){
			knjige.add(new Knjiga());
		}
		
		id = ID++;
	}
	
	@Override
	public String toString(){
		String k = "";
		for(Knjiga knjiga : knjige){
			k+= "\n\t\t\t" + knjiga;
		}
		return "Polica -> ID: " + id + "\t" + k;
	}
}