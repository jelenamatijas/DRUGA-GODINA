import java.util.Random;

public class Automobil extends Thread{
    public String tipAutomobila;
    public int id;

    public Object[][] staza;
    public int ostaloDoKraja = 0;
    public int red;

    public static Boolean kraj = false;

    public Automobil(Object[][] staza, String tipAutomomobila, int red){
        this.staza = staza;
        this.tipAutomobila = tipAutomomobila;
        this.red = red;

        Random rand = new Random();
        this.id = rand.nextInt(100);
    }

    @Override
    public void run(){
        for(int i=0; i<Autotrka.DUZINA_STAZE; i++){
            if("STOP".equals(staza[red][i])){
                System.out.println("Automobil je zaustavljen na 5s. Podaci o automobilu: " + this);
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            staza[red][i] = this;
            ostaloDoKraja = (Autotrka.DUZINA_STAZE - 1) - i;
            System.out.println("Automobil se krece. Podaci o automobilu: " + this);
            try{
                    sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            if(i == (Autotrka.DUZINA_STAZE-1)){
                synchronized(kraj){
                    if(!kraj){
                        kraj = true;
                        System.out.println("Pobijedio je automobil u traci " + red + ". Podaci o automobilu: " + this);
                        break;
                    }
                }
            }

            if(kraj){
                System.out.println("Automobil nije zavrsio utrku. Podaci o automobilu: " + this);
                break;
            }
        }
    }

    @Override
    public String toString(){
        return "Automobil [tipAutomobila: " + tipAutomobila + ", id: " + id + ", ostaloDoKraja: " + ostaloDoKraja + "]";
    }
}
