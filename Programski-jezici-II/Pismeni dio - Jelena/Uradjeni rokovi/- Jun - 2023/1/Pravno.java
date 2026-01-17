import java.util.*;

class Pravno extends Osoba{
	String jib;
	List<Vozilo> v = new ArrayList<>();
	
	Pravno(List<Vozilo> v){
		super(v);
		jib =  generateJIB();
	}
	
	String generateJIB(){
		
		int m = Main.rand.nextInt(1,14);
		String jib="";
		int dan;
		if(m==2){
			dan = Main.rand.nextInt(1,31);
			
		}else{
			dan = Main.rand.nextInt(1,32);
		}
		if(dan<10){
			jib += "0" + dan;
		}else{
			jib += dan;
		}
		
		if(m<10){
			jib += "0" + m;
		}else{
			jib += m;
		}
		
		jib += Main.rand.nextInt(1950, 2009) %1000;
		jib += Main.rand.nextInt(112222, 222999);
		return jib;
	}
}