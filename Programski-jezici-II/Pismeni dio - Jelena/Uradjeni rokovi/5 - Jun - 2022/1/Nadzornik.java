import java.util.*;
import java.time.LocalDate;

class Nadzornik extends Stanovnik implements Runnable{
	BufferPoruka buffer;
	Teleekran teleekran;
	List<Radnik> radnici;
	volatile boolean radi = true;
	
	Nadzornik(String ime, String prezime, String mBroj, BufferPoruka b, Teleekran t, List<Radnik> r){
		super(ime, prezime, mBroj);
		buffer = b;
		teleekran = t;
		radnici =r;
	}
	
	public List<String> pretrazi(String[] kljucneRijeci){
		List<String> rezultat = new ArrayList<>();
		LocalDate date = LocalDate.now();
		for(Poruka p: buffer.getAll()){
			if(!p.vrijeme.toLocalDate().equals(date))continue;
			for(String k: kljucneRijeci){
				if(p.tekst.contains(k)){
					rezultat.add(p.posiljalac);
				}
			}
		}
		return rezultat;
	}
	
	public void kazniRadnike(List<String> maticniBrojevi){
		Random rand = new Random();
        for (String mBroj : maticniBrojevi) {
            for (Radnik r : radnici) {
                if (r.mBroj.equals(mBroj)) {
					while(Main.pauzirano && !Main.kraj){
						try{ 
							Thread.sleep(100); 
						} catch(InterruptedException e){}
					}
                    double kazna = rand.nextInt(101); 
                    teleekran.prikaz("Nadzornik: Zbog neadekvatnog ponasanja, plata radnika " + 
                                    r.ime + " " + r.prezime + " ce biti umanjena za " + kazna);
                    r.umanjiPlatu(kazna);
                }
            }
        }
	}
	
	public void zaustavi() {
        radi = false;
    }
    
    @Override
    public void run() {
        while (radi) {
            try {
                Thread.sleep(3000);
                String[] kljucneRijeci = {"zlocin", "kontrola", "rat"};
                List<String> nadjeni = pretrazi(kljucneRijeci);
                if (!nadjeni.isEmpty()) {
                    teleekran.prikaz("Nadzornik: Pronadjeno " + nadjeni.size() + " radnika sa sumnjivim porukama");
                    kazniRadnike(nadjeni);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
        teleekran.prikaz("Nadzornik: Zavrsio sa radom");
    }
}