import java.util.Scanner;

public class Kvadar extends Predmet {
    protected double a,b,c;

    Kvadar(double specificnaTezina){
        super(specificnaTezina);
        identifikator = Predmet.id;
    }

    @Override
    public void print(){
        System.out.println("Podaci o kvadru: ");
        System.out.println("Specificna tezina: " + specificnaTezina);
        System.out.println("Identifikator: " + identifikator);
        System.out.println("Stranice a = : " + a + ", b = " + b + ", c = " + c);
    }

    @Override
    public void read()throws PredmetException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesi vrijednosti stranica a, b i c: ");
        System.out.print("a = ");
        a = Double.parseDouble(scanner.nextLine());
        System.out.print("b = ");
        b = Double.parseDouble(scanner.nextLine());
        System.out.print("c = ");
        c = Double.parseDouble(scanner.nextLine());

        if(a < 1 || b < 1 || c < 1 || a > 100 || b > 100 || c > 100){
            throw new PredmetException();
        }
    }

    @Override
    public double zapremina(){
        return a * b * c;
    }

    @Override
    public String toString(){
        return super.toString() + "Stranice a = : " + a + ", b = " + b + ", c = " + c;
    }

}
