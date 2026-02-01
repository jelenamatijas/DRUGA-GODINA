package usmeni.primjeri.Klasa0;

public class Klasa0 {
    static {
        int x = 5;
    }

    static int x, y;

    public static void main(String args[]) {
        x--;
        System.out.println(x + " " + y);
        metoda();
        System.out.println(x + " " + y);
        System.out.println(++x + x++);
        System.out.println(++Klasa0.x);
    }

    public static void metoda() {
        y = ++x;
    }
}