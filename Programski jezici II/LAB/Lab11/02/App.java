import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class App {
    
    public static void main(String []args){
        int m = 5;
        int n = 5;

        for(int i=0; i<args.length; i++){
            if("-bonus".equals(args[i])){
                m = Integer.valueOf(args[i+1]);
            }
            if("-prepreka".equals(args[i])){
                n = Integer.valueOf(args[i+1]);
            }
        }

        Mapa mapa = new Mapa(m, n);

        List<Takmicar> takmicari = new ArrayList<>();

        takmicari.add(new Pilot("Pilot"));
        takmicari.add(new Vozac("Vozac"));
        takmicari.add(new Pjesak("Pjesak"));

        System.out.println("Takmicenje pocinje.");
        for (Takmicar takmicar : takmicari) {
            takmicar.start();;
        }

        try{
            for (Takmicar takmicar : takmicari) {
                takmicar.join();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Svi takmicari su zavrsili svoje kretanje.");
        Collections.sort(takmicari, new Comparator<Takmicar>() {
            @Override
            public int compare(Takmicar t1, Takmicar t2){
                return t2.getBrojBodova().compareTo(t1.getBrojBodova());
            }
        });

        System.out.println(takmicari);
    }
}
