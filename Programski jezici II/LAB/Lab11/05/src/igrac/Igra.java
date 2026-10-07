package src.igrac;

import java.util.Random;

import src.polje.*;

public class Igra {
    public static Object polje[] = new Object[20];
    public static int BROJ_IGRACA = 2;
    public static Igrac[] igraci = new Igrac[2];

    public static void postaviSemafore(){
        Random rand = new Random();
        int brojSemafora = 2;
        while(brojSemafora > 0){
            int x = rand.nextInt(19);
            if(polje[x] == null){
                polje[x] = new Semafor(brojSemafora-1);
                ((Semafor)polje[x]).start();
                brojSemafora--;
            }
        }
    }

    public static void postaviNovcice(){
        Random rand = new Random();
        int brojNovcica = 5;
        while(brojNovcica > 0){
            int x = rand.nextInt(19);
            if(polje[x] == null){
                polje[x] = new Novcic(rand.nextInt(0,101));
                brojNovcica--;
            }
        }
    }

    public static void prikaziMapu(){
        for(int i=0; i< polje.length; i++){
            if(polje[i] != null){
                if(polje[i] instanceof Novcic){
                    System.out.print(((Novcic)polje[i]).getVrijednost() + " ");
                }else if(polje[i] instanceof Semafor){
                    System.out.print(((Semafor)polje[i]).getStanje() + " ");
                }
            }else{
                System.out.print("* ");
            }
            System.out.print("|");
        }
        System.out.println();
    }
    public static void main(String []args){
        postaviNovcice();
        postaviSemafore();
        prikaziMapu();

        igraci[0] = new Igrac("Igrac 1", 0);
        igraci[1] = new Igrac("Igrac 2", polje.length-1);

        for (Igrac igrac : igraci) {
            igrac.start();
        }
        
        System.out.println("Igra pocinje.");
    }
}
