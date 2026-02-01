package usmeni.Z37;

// A1.java

import java.io.Serializable;

class A1 {
    static A1 a1;

    static {
        new A2(new A1()); // JAKO BITNO: U ovom slučaju se prvo izvršava new A2, jer je A u procesu kreiranja trenutno, zbog main metode
        System.out.println("A1-S");
    }

    {
        System.out.println("A1-N");
    }

    protected A1() {
        System.out.println("A1()");
    }

    protected A1(A1 a1) {
        this.a1 = a1;
        System.out.println("A1(A1)");
    }

    protected void metoda1() {
        System.out.println("A1: metoda1()");
    }

    private void metoda2() {
        System.out.println("A1: metoda2()");
    }

    public static void main(String[] args) {
        System.out.println("Prva linija");
        A4 a4 = new A4();

        /*a4.metoda1();
        a4.metoda2();
        a4.metoda3();
        A1 a5 = new A5();
        a5.metoda2();
        System.out.println(a5.equals(a4));
        System.out.println(((A2) ((Serializable) (new A4()))) == a4);
        System.out.println(a5 != a4);*/
    }

}

class A2 extends A1 {
    {
        System.out.println("A2-N1");
    }

    public A2() {
        super(new A1());
        System.out.println("A2()");
    }

    private void A2() {
    }

    {
        System.out.println("A2-N2");
    }

    protected A2(A1 a1) {
        this();
        A1.a1 = a1;
        System.out.println("A2(A1)");
    }

    static {
        new A3();
        System.out.println("A2-S");
    }

    public void metoda1() {
        super.metoda1();
        System.out.println("A2: metoda1()");
    }

    public void metoda2() {
        System.out.println("A2: metoda2()");
    }

    protected void metoda3() {
        metoda1();
        System.out.println("a3: metoda3()");
    }
}

class A3 extends A2 implements Serializable {
    static {
        System.out.println("a3-S1");
    }

    {
        System.out.println("a3-N");
    }

    public A3() {
        super();
        System.out.println("a3()");
    }

    public A3(A2 a2) {
        super(a2.a1);
        System.out.println("a3(A2)");
    }

    static {
        System.out.println("a3-S2");
    }

    public void metoda3() {
        System.out.println("a3: metoda3()");
    }
}

class A4 extends A3 {
    A3 a3 = new A3(new A2(new A1()));

    public A4() {
        System.out.println("A4()");
    }

    static {
        System.out.println("A4-S");
    }
}

class A5 extends A4 {
    protected A5 a5 = new A5();

    static {
        System.out.println("A5-S");
    }

    public A5() {
        super();
        System.out.println("A5()");
    }

    public void metoda3() {
        System.out.println("A5:metoda3()");
    }


}