import java.util.*;

class Skladiste{
	List<Predmet> predmeti = new ArrayList<>();
	List<Predmet> obradjeniPredmeti;
	
	Skladiste(){
		for(int i=0;i<5;i++){
			predmeti.add(new Kutija());
			predmeti.add(new Ploca());
			predmeti.add(new InoxSajla());
			predmeti.add(new CelicnaSajla());
		}
		obradjeniPredmeti = new ArrayList<>();
	}
}