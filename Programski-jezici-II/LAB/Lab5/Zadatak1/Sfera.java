import java.util.Scanner;

public class Sfera extends Predmet {
    protected double r;

    Sfera(double specificnaTezina){
        super(specificnaTezina);
        identifikator = Predmet.id;
    }

    @Override
    public  void print(){
        System.out.println("Podaci o sferi: ");
        System.out.println("Specificna tezina: " + specificnaTezina);
        System.out.println("Identifikator: " + identifikator);
        System.out.println("Poluprecnik: " + r);
    }

    @Override
    public double zapremina(){
        return 4 / 3 * (Math.PI * Math.pow(r, 3));
    }

    @Override
    public String toString(){
        return super.toString() + "Poluprecnik = " + r + ".";
    }

    @Override
    public void read()throws PredmetException{
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Unesi vrijednosti poluprecnika: ");
        r = Double.parseDouble(scanner.nextLine());
        if(r < 1 || r > 100){
            throw new PredmetException();
        }
    }
}
