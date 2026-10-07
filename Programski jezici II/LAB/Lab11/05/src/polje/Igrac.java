package src.polje;

import java.util.Date;
import src.igrac.Igra;

public class Igrac extends Thread{
    private String ime;
    private int vrijednost;
    private int lokacija;
    private long vrijeme;
    private boolean status;
    private int brojPoljaZaPreci = 0;

    public Igrac(String ime, int lokacija){
        this.ime = ime;
        this.lokacija = lokacija;
        if(lokacija == 0){
            status = false;
        }else{
            status = true;
        }
    }

    public void setLokacija(){
        if(status){
            lokacija--;
        }else{
            lokacija++;
        }
    }

    @Override
    public String toString(){
        return "Igrac{Ime: " + ime + " Vrijednost novcica: " + vrijednost + " Lokacija: " + lokacija
                 + " Vrijeme: " + vrijeme + "}";
    }

    @Override
    public void run(){
        try{
            vrijeme = (new Date()).getTime();
            while(brojPoljaZaPreci != 20){
                brojPoljaZaPreci++;
                try{
                    sleep(500);
                    System.out.println(this);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                
                synchronized (this) {
                    Object polje = Igra.polje[lokacija];
                    if(polje != null){
                        if(polje instanceof Novcic){
                            Novcic novcic = (Novcic)polje;
                            vrijednost += novcic.getVrijednost();
                            Igra.polje[lokacija] = null;
                            System.out.println("Igrac " + ime + " je pokupio novcic u vrijednosti " + novcic.getVrijednost());
                        }
                        
                        if(polje instanceof Semafor){
                            Semafor semafor = (Semafor)polje;
                            System.out.println("Igrac " + ime + " je na semaforu. Trenutno je " + semafor.getStanje());

                            try{
                                if(!semafor.getStanje().equals(Semafor.ZELENO)){
                                    wait();
                                }
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                            System.out.println("Igrac " + ime + " nastavlja dalje.");
                        }
                    }
                }

                this.setLokacija();
            }
            System.out.println("Igrac " + ime + " je zavrsio simulaciju koja je trala " + (((new Date()).getTime() - vrijeme)/3600) + " minuta.");
            System.out.println(this);
            Igra.BROJ_IGRACA--;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}