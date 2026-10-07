import java.util.*;

class GrupaGostiju {
	List<Gost> gosti = new ArrayList<>();
	static int ID=1;
	int id;
	
	GrupaGostiju(){
		int x = Main.rand.nextInt(1, 7);
		for(int i=0;i<x;i++){
			if( i% 3 == 0){
				gosti.add(new Vegetarijanac());
			}else if( i% 3==1){
				gosti.add(new Pusac());
			}else{
				gosti.add(new Standard());
			}
		}
		id = ID++;
	}
	
	public int ukupanIznos(){
		int novac = 0;
		for(Gost g: gosti){
			novac+= g.iznos;
		}
		
		return novac;
	}
	
	public boolean imaPusaca(){
		for(Gost g: gosti){
			if(g instanceof PusacInterface){
				return true;
			}
		}
		return false;
	}
	
	public boolean imaVegetarijanaca(){
		for(Gost g: gosti){
			if(g instanceof VegetarijanacInterface){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		String s = "";
		for(Gost g: gosti){
			s+= g.toString() + "\n";
		}
		return "GRUPA " + id + ":\n" + s;
	}
}