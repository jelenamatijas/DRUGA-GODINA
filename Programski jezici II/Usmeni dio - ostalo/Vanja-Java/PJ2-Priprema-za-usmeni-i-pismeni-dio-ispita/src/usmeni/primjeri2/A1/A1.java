package usmeni.primjeri2.A1;

public class A1 {
    private A1 a1;

    {
        System.out.println("A1-N");
    }

    public A1() {
        System.out.println("A1");
    }

    void metoda() {
        System.out.println("metoda A1");
    }

    public static void main(String[] args) {
        A3 a3 = new A3();
        a3.metoda();
        a3.metoda2();
    }
}

class A2 extends A1 {
    A1 a1;

    public A2() {
        this(new A1());
        System.out.println("A2");
    }

    public A2(A1 a1) {
        this.a1 = a1;
        System.out.println("A2(A1)");
    }

    public void metoda2() {
        System.out.println("metoda2 A2");
    }
}

class A3 extends A2 {
    private A1 a = new A2();
    private A2 a2 = new A2(new A1());

    public A3() {
        a2.metoda();
        System.out.println("a3");
        a.metoda();
    }

    public void metoda() {
        System.out.println("metoda a3");
    }
}