import java.util.Date;
import java.util.AbstractMap.SimpleImmutableEntry;

public class Osoba extends Thread{
    private String ime;
    private String visina;
    private String oprema;
    private int redNaMapi;
    public boolean naPauzi = false;
    private long vrijemeKretanja;

    public Osoba(String ime, String visina, String oprema, int redNaMapi){
        this.ime = ime;
        this.visina = visina;
        this.oprema = oprema;
        this.redNaMapi = redNaMapi;
    }

    public String getIme(){
        return ime;
    }

    public void setIme(String ime){
        this.ime = ime;
    }

    public String getVisina(){
        return visina;
    }

    public void setVisina(String visina){
        this.visina = visina;
    }

    public String getOprema(){
        return oprema;
    }

    public void setOprema(String oprema){
        this.oprema = oprema;
    }

    public int getRedNaMapi(){
        return redNaMapi;
    }

    public void setRedNaMapi(int redNaMapi){
        this.redNaMapi = redNaMapi;
    }

    public long getVrijemeKretanja(){
        return vrijemeKretanja;
    }

    public void setVrijemeKretanja(long vrijemeKretanja){
        this.vrijemeKretanja = vrijemeKretanja;
    }

    @Override
    public String toString(){
        return "{Ime: " + ime + " Visina: " + visina + " Oprema: " + oprema + "}";
    }

    @Override
    public void run(){
        long pocetak = new Date().getTime();
        for(int i=0; i<20; i++){
            System.out.println("Osoba " + this + " je na poziciji" + i);
            if(naPauzi){
                synchronized(this){
                try{
                    wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            }
            try{
                sleep(1000);
                if(Simulacija.mapa[redNaMapi][i] instanceof Prepreka){
                    String vrsta = ((Prepreka)Simulacija.mapa[redNaMapi][i]).getVrsta();
                    if(Prepreka.STIJENA.equals(vrsta) && this instanceof PenjanjeInterface){
                        Simulacija.mapa[redNaMapi][i] = ((PenjanjeInterface) this).penjanje();
                    }else if(Prepreka.VATRA.equals(vrsta) && this instanceof GasenjePozaraInterface){
                        Simulacija.mapa[redNaMapi][i] = ((GasenjePozaraInterface) this).gasenjePozara();
                    }else if(Prepreka.VODA.equals(vrsta) && this instanceof PlivanjeInterface){
                        Simulacija.mapa[redNaMapi][i] = ((PlivanjeInterface) this).plivanje();
                    }else{
                        System.out.println("Osoba u redu " + redNaMapi + " ne moze da savlada prepreku " + vrsta);
                        sleep(3000);
                    }
                    
                }
                
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}