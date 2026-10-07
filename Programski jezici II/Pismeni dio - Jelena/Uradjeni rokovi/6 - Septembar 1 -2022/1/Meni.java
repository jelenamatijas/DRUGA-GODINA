import java.util.*;
import java.util.stream.*;

class Meni{
	List<Stavka> stavke = new ArrayList<>();
	boolean imaVegetarijansko;
	
	Meni(boolean v){
		imaVegetarijansko = v;
		for(int i=0;i<50;i++){
			if(v){
				stavke.add(new Stavka(Main.rand.nextBoolean()));
			}else{
				stavke.add(new Stavka(false));
			}
		}
	}
	
	List<Stavka> getVegetarijanskeStavke(){
		return stavke.stream().filter((Stavka s) -> s.vegetarijansko).toList();
	}
}