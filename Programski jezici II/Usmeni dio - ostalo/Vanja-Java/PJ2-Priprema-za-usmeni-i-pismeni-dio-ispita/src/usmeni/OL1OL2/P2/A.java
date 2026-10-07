package usmeni.OL1OL2.P2;

public class A {
    static {
        int x = 5;
    }

    static int x, y;

    public static void main(String args[]) {
        x--;
        System.out.println(x);
        System.out.println(y);
        metoda();
        System.out.println(x);
        System.out.println(y);
        System.out.println(++x + x++); // B
        System.out.println(++A.x); // C
    }

    public static void metoda() {
        y = ++x;
    }
}