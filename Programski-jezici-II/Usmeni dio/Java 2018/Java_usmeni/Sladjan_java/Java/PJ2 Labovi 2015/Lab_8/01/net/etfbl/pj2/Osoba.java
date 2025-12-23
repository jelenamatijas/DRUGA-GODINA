package net.etfbl.pj2;

import java.util.Date;
import net.etfbl.pj2.interfaces.GasenjePozaraInterface;
import net.etfbl.pj2.interfaces.PenjanjeInterface;
import net.etfbl.pj2.interfaces.PlivanjeInterface;

/**
 *
 * @author igor
 */
public class Osoba extends Thread {

    private String ime;
    private String prezime;
    private String oprema;
    private int redNaMapi;
    public boolean pause = false;
    private long vrijemeKretanja;

    public Osoba() {
    }

    public Osoba(String ime, String prezime, String oprema, int redNaMapi) {
        this.ime = ime;
        this.prezime = prezime;
        this.oprema = oprema;
        this.redNaMapi = redNaMapi;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOprema() {
        return oprema;
    }

    public void setOprema(String oprema) {
        this.oprema = oprema;
    }

    public int getRedNaMapi() {
        return redNaMapi;
    }

    public void setRedNaMapi(int redNaMapi) {
        this.redNaMapi = redNaMapi;
    }

    public long getVrijemeKretanja() {
        return vrijemeKretanja;
    }

    @Override
    public String toString() {
        return "Osoba{" + "ime=" + ime + ", prezime=" + prezime + ", oprema=" + oprema + '}';
    }

    public void run() {
        long pocetak = new Date().getTime();
        for (int i = 0; i < 20; i++) {
            System.out.println("Osoba " + this + " je na poziciji " + i);
            if (pause) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            try {
                sleep(1000);
                if (Simulacija.mapa[redNaMapi][i] instanceof Prepreka) {
                    String vrsta = ((Prepreka) Simulacija.mapa[redNaMapi][i]).getVrsta();
                    if (Prepreka.STIJENA.equals(vrsta) && this instanceof PenjanjeInterface) {
                        Simulacija.mapa[redNaMapi][i] = ((PenjanjeInterface) this).penjanje();
                    } else if (Prepreka.VODA.equals(vrsta) && this instanceof PlivanjeInterface) {
                        Simulacija.mapa[redNaMapi][i] = ((PlivanjeInterface) this).plivanje();
                    } else if (Prepreka.VATRA.equals(vrsta) && this instanceof GasenjePozaraInterface) {
                        Simulacija.mapa[redNaMapi][i] = ((GasenjePozaraInterface) this).gasenjePozara();
                    } else {
                        System.out.println("Osoba u redu " + redNaMapi + " ne moze da savlada prepreku "
                                + ((Prepreka) Simulacija.mapa[redNaMapi][i]).getVrsta());
                        sleep(3000);
                    }

                }
                if(Simulacija.mapa[redNaMapi][i] instanceof String == false){
                    Simulacija.mapa[redNaMapi][i] = this;
                }
                if (i > 0 && Simulacija.mapa[redNaMapi][i-1] instanceof String == false) {
                    Simulacija.mapa[redNaMapi][i-1] = null;

                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
        vrijemeKretanja = new Date().getTime() - pocetak;
        System.out.println(this + ". Vrijeme kretanja " + vrijemeKretanja);
    }

}
