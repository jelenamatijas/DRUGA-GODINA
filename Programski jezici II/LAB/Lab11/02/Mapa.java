import java.util.Random;

public class Mapa {
    
    public static Polje mapa[] = new Polje[50];
    Random rand = new Random();

    public Mapa(int m, int n){
        for(int i=0; i<50;i++){
            mapa[i] = new Polje();
        }
        postaviPrepreke(n);
        postaviBonuse(m);
    }

    private void postaviPrepreke(int n){
        postaviJedanTipPrepreke(n, new Vatra());
        postaviJedanTipPrepreke(n, new Voda());
        postaviJedanTipPrepreke(n, new Kamen());
    }

    private void postaviJedanTipPrepreke(int n, Prepreka p){
        while(n > 0){
            int i = rand.nextInt(50);
            if(mapa[i].getElement() == null){
                mapa[i].setElement(p);
                n--;
            }
        }
    }

    private void postaviBonuse(int m){
        while(m > 0){
            int i = rand.nextInt(50);
            if(mapa[i].getElement() == null){
                mapa[i].setElement(new Bonus());
                m--;
            }
        }
    }
}
