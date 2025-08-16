package src.polje;

import src.igrac.Igra;

public class Semafor extends Thread{
    public static final String CRVENO = "Crveno";
    public static final String ZUTO = "Zuto";
    public static final String ZELENO = "Zeleno";

    private String stanje;
    private int id;

    public Semafor(int id){
        this.stanje = ((id % 2) == 0) ? CRVENO : ZUTO;
        this.id = id;
    }

    public String getStanje(){
        return stanje;
    }

    public void setStanje(String stanje){
        this.stanje = stanje;
    }

    @Override
    public void run(){
        try {
            boolean toRed = (id % 2) == 0;
            while (Igra.BROJ_IGRACA > 0) {
                System.out.println(this);
                if(stanje.equals(CRVENO) || stanje.equals(ZELENO)){
                    try{
                        Thread.sleep(1800);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }else{
                    try{
                        Thread.sleep(700);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }

                switch (stanje) {
                    case CRVENO:
                        stanje = ZUTO;
                        toRed = false;
                        break;
                    case ZELENO:
                        toRed = true;
                        stanje = ZUTO;
                        break;
                    case ZUTO:
                        stanje = toRed ? CRVENO : ZELENO;
                        break;
                }

                if(stanje.equals(ZELENO)){
                    for (Igrac igrac : Igra.igraci) {
                        synchronized(igrac){
                            igrac.notify();
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Semafor " + id + " ima upaljeno " + stanje + " svjetlo.";
    }
}
