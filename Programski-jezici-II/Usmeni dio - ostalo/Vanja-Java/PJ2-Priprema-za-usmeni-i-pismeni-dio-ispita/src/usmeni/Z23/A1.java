package usmeni.Z23;

// A1.java

import java.io.Serializable;

class A1 {

    static {
        new A2(); // TODO: staviti new a3(), new A1() unutra
        System.out.println("A1-S");
    }

    {
        System.out.println("A1-N1");
    }

    private A1 a1;

    public A1() {
        System.out.println("A1()");
    }

    public A1(A1 a1) {
        this();
        System.out.println("A1(A1)");
        this.a1 = a1;
        new A2(a1);
    }

    public void metoda1() {
        new A1();
        System.out.println("A1.metoda1()");
    }

    {
        System.out.println("A1-N2");
    }
}

class A2 extends A1 implements Serializable {

    static {
        System.out.println("A2-S");
    }

    {
        System.out.println("A2-N");
    }

    protected A2() {
        System.out.println("A2()");
        this.metoda1();
    }

    public A2(A1 a1) {
        System.out.println("A2(A1)");
        a1.metoda1();
    }

    @Override
    public void metoda1() {
        super.metoda1();
        System.out.println("A2.metoda1()");
    }

    public void metoda2() {
        System.out.println("A2.metoda2()");
    }
}

class A3 extends A2 {

    A2 a2 = null;

    static {
        System.out.println("a3-S");
    }

    {
        System.out.println("a3-N1");
    }

    public A3() {
        super();
        System.out.println("a3()");
    }

    public A3(A2 a2) {
        this();
        this.a2 = a2;
        System.out.println("a3(A2)");
    }

    {
        System.out.println("a3-N2");
    }

    public A3(A1 a1, A2 a2) {
        this(a2);
        System.out.println("a3(A1,A2)");
    }

    public void metoda2() {
        System.out.println("a3.metoda()");
    }
}

class A4 extends A3 {

    A1 a1 = new A1();
    A3 a2 = new A3(new A1(new A1()), new A2(a1));
    Serializable a3 = new A3();

    static {
        System.out.println("A4-S");
    }

    public A4() {
        super();
        System.out.println("A4()");
        super.metoda1();
    }

    {
        System.out.println("A4-N");
    }

    public static void main(String[] args) {
        A4 a4 = new A4();
        System.out.println("============================");
        a4.metoda1();
        System.out.println("============================");
        a4.metoda2();
        System.out.println("============================");
        ((A2) a4).metoda1();
        System.out.println("============================");
        ((A2) a4.a3).metoda2();
        System.out.println("============================");
        A5 a5 = new A5();
        System.out.println("============================");
        ((A3) ((A1) new A5())).metoda2();

    }
}

class A5 extends A1 {
    static {
        System.out.println("A5-S");
    }

    public A5() {
        super();
        System.out.println("A5()");
    }

    {
        System.out.println("A5-N");
    }
}