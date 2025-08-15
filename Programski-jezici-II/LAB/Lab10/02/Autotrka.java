import java.util.Random;

public class Autotrka {
    
    public static final int DUZINA_STAZE = 15;
    public static final int BROJ_STOP_ZNAKOVA = 4;
    public static void main(String args[]){
        Random rand = new Random();
        Object [][]staza = new Object[3][DUZINA_STAZE];

        int brojZnakova = 0;
        while(brojZnakova != BROJ_STOP_ZNAKOVA){
            int red = rand.nextInt(3);
            int kolona = rand.nextInt(DUZINA_STAZE);

            if(staza[red][kolona] == null){
                staza[red][kolona] = "STOP";
                brojZnakova++;
            }
        }

        System.out.println("*************** UTRKA POCINJE! ***************");
        Automobil a1 = new Automobil(staza, "Citroen", 0);
        Automobil a2 = new Automobil(staza, "Peugeot", 1);
        Automobil a3 = new Automobil(staza, "BMW", 2);
        a1.start();
        a2.start();
        a3.start();
    }
}
