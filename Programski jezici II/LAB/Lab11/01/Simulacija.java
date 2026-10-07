import java.util.Random;
import java.util.Scanner;

public class Simulacija {

    private static Random rand = new Random();
    public static Object mapa[][] = new Object[3][20];

    public static void postavljanjePrepreka(String vrsta){
        int brojPrepreka = 4;
        while(brojPrepreka>0){
            int x = rand.nextInt(3);
            int y = rand.nextInt(20);
            if(mapa[x][y] == null){
                mapa[x][y] = new Prepreka(vrsta);
                brojPrepreka--;
            }
        }
    }

    private static void printMap(){
        for(int i=0; i<3; i++){
            for(int j=0; j<20; j++){
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String []args){
        postavljanjePrepreka(Prepreka.STIJENA);
        postavljanjePrepreka(Prepreka.VODA);
        postavljanjePrepreka(Prepreka.VATRA);

        Plivac plivac = new Plivac("Plivac", "visina", "oprema", 0);
        Vatrogasac vatrogasac = new Vatrogasac("Vatrogasac","Visina", "oprema",1);
        Planinar planinar = new Planinar("Planinar","visina", "oprema", 2);

        Scanner scanner = new Scanner(System.in);
        String opcija = "";
        plivac.start();
        vatrogasac.start();
        planinar.start();

        while(!"END".equals(opcija)){
            opcija = scanner.nextLine();
            if("PAUZA".equals(opcija)){
                plivac.naPauzi = true;
                vatrogasac.naPauzi = true;
                planinar.naPauzi = true;
            }
            if("NASTAVAK".equals(opcija)){
                plivac.naPauzi = false;
                vatrogasac.naPauzi = false;
                planinar.naPauzi = false;

                try{
                    synchronized(plivac){
                        plivac.notify();
                    }
                    synchronized(planinar){
                        planinar.notify();
                    }
                    synchronized(vatrogasac){
                        vatrogasac.notify();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        try{
            planinar.join();
            plivac.join();
            vatrogasac.join();
        }catch(Exception e){
            e.printStackTrace();
        }

        printMap();
        scanner.close();

    }
    
}
