package usmeni.primjeri.P4;

public class A {
    static int
            x = 3; // C

    public static void main(String[] args) {
        new A();
    }

    A() {
        A(2); // D
    }

    A(int
              x) {
        System.out.println(
                x);
    }
}