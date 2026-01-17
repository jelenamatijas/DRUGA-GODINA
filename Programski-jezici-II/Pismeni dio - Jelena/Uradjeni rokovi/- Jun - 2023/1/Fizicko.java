import java.util.*;

class Fizicko extends Osoba{
	String jmbg;
	
	Fizicko(List<Vozilo> v){
		super(v);
		jmbg =  generateJMBG();
	}
	
	String generateJMBG(){
		
		int m = Main.rand.nextInt(1,13);
		String jmbg="";
		int dan;
		if(m==2){
			dan = Main.rand.nextInt(1,28);
			
		}else{
			dan = Main.rand.nextInt(1,32);
		}
		if(dan<10){
			jmbg += "0" + dan;
		}else{
			jmbg += dan;
		}
		
		if(m<10){
			jmbg += "0" + m;
		}else{
			jmbg += m;
		}
		
		jmbg += Main.rand.nextInt(1950, 2009) %1000;
		jmbg += Main.rand.nextInt(111111, 999999);
		return jmbg;
	}
}