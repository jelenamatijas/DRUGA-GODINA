package usmeni.Z38;

import java.io.Serializable;

class A1 {
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
        A4 a4 = new A4();
        A3 a3 = new A3();
        System.out.println(a4.equals(a3));
        System.out.println(a4 == a4);
        System.out.println(a4.equals((Serializable) a3));
    }

    static {
        new A4().metoda2();
        System.out.println("A1-S");
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

    {
        System.out.println("A2-N");
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

    public A3(A1 a1) {
        super(a1);
        System.out.println("a3(A1)");
    }

    public void metoda() {
        System.out.println("metoda a3");
    }
}

class A4 extends A3 implements Serializable {
    static {
        System.out.println("A4-S1");
    }

    public A4() {
        super(new A2());
        System.out.println("A4");
    }

    static {
        System.out.println("A4-S2");
    }
}