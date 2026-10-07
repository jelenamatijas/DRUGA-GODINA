import java.util.*;

class Prepravljac extends Stanovnik implements Runnable{
	BufferPoruka buffer;
	Teleekran teleekran;
	Map<String, String> zamjene;
	volatile boolean radi = true;
	
	Prepravljac(String ime, String prezime, String mBroj, BufferPoruka b, Teleekran t){
		super(ime, prezime, mBroj);
		buffer = b;
		teleekran = t;
		zamjene = new HashMap<>();
		zamjene.put("sloboda", "kontrola");
        zamjene.put("istina", "laz");
        zamjene.put("mir", "rat");
        zamjene.put("znanje", "neznanje");
        zamjene.put("ljubav", "strah");
        zamjene.put("misao", "zlocin");
        zamjene.put("narod", "partija");
	}
	
	public void zaustavi() {
        radi = false;
    }
	
	@Override
	public void run() {
        while (radi) {
            try {
                Thread.sleep(1000);
                List<Poruka> poruke = buffer.getAll();
                for (Poruka p : poruke) {
                    int izmjene = 0;
                    String tekst = p.tekst;
                    for (String rijec : zamjene.keySet()) {
                        if (tekst.contains(rijec)) {
                            tekst = tekst.replace(rijec, zamjene.get(rijec));
                            izmjene++;
                        }
                    }
                    
                    if (izmjene > 0) {
                        p.tekst = tekst;
                        teleekran.prikaz("Prepravljac: izvrseno " + izmjene + " izmjena u poruci od " + p.posiljalac);
                    }
                }
				while(Main.pauzirano && !Main.kraj){
					try{ 
						Thread.sleep(100); 
					} catch(InterruptedException e){}
				}
            } catch (InterruptedException e) {
                break;
            }
        }
        teleekran.prikaz("Prepravljac: Zavrsio sa radom");
    }
}