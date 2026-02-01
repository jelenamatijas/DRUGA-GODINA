package pismeni.R_2022_06_15.Z01;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Prepravljač extends Stanovnik {
    public final ReentrantLock BUFFER_LOCK = new ReentrantLock();
    private static final String PORUKA = "Izvršeno je %d izmjena za poruku %s jmb pošiljaoca, %s jmb primaoca, %s datuma.";

    public Prepravljač(String ime, String prezime, String jmb) {
        super(ime, prezime, jmb);
    }

    @Override
    public void run() {
        while (!Simulacija.ZAUSTAVI) {
            int brojIzmjena = 0;
            for (Poruka poruka : Simulacija.poruke) {
                for (Map.Entry<String, String> entry : Simulacija.zamjena.entrySet()) {
                    String rijec = entry.getKey();
                    String zamjena = entry.getValue();
                    if (poruka.tekst.contains(rijec)) {
                        poruka.tekst = poruka.tekst.replace(rijec, zamjena);
                        brojIzmjena++;
                    }
                }
                // šalje nadzorniku poruku o broju izvršenih izmjena preko teleekrana
                try {
                    // TODO: Ne znam koji je jmb Primaoca nadzornika
                    Poruka p = new Poruka(jmb, "", String.format(PORUKA, brojIzmjena, poruka.jmbPosiljaoca, poruka.jmbPrimalac, poruka.vrijemeSlanja));
                    Simulacija.telePoruke.put(p);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}