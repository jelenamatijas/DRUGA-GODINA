import java.util.*;

class Student{
	String ime, prezime;
	int brojIndeksa, godinaRodjenja, godinaStudija;
	double prosjecnaOcjena;
	static int id = 1;
	
	Character[] slova = {'A','B','C','D','E','F','G','H','I','J','K','L','M', 'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	Student(){
		ime = slova[Main.rand.nextInt(0,26)] + "_ime";
		prezime = slova[Main.rand.nextInt(0,26)] + "_prezime";
		brojIndeksa = id;
		godinaStudija = Main.rand.nextInt(1, 5);
		godinaRodjenja = Main.rand.nextInt(1996,2008);
		prosjecnaOcjena = Main.rand.nextDouble(6.0, 10.0);
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime + " " + brojIndeksa + " " + godinaRodjenja + " " + godinaStudija + " " + prosjecnaOcjena;
	}
}