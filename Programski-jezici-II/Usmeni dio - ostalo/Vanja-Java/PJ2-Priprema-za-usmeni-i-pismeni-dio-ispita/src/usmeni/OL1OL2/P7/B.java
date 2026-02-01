package usmeni.OL1OL2.P7;

public class B {
    public static void main(String[] args) {
        int i = 1;
        int n = ++i % 5;
        System.out.print(n);
        n = i-- % 4;
        System.out.print(n);
        n = i++ % 2;
        System.out.print(n);
    }
}