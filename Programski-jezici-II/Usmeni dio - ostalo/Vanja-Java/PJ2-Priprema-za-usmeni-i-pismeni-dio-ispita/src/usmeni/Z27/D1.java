package usmeni.Z27;

// D1.java

public class D1 implements DI3 {
    public D1() {
        System.out.println("D1()");
    }

    public static void main(String... args) {
        DI1 d1 = new D1();
        DI2 d2 = new D1();
        DI3 d3 = new D2();
        D3 dd3 = new D3();
        DI33.DI34 ref = new D3();
        D1 d4 = new D2();
        D2 d5 = new D2();

        d1.metoda1();
        d2.metoda2();
        d3.metoda1();
        d4.metoda2();
        d5.metoda1();
        dd3.metodaR();
        dd3.metoda1();
        if (dd3 instanceof D1) {
            D1 test = (D1) d5;
        }
    }

    public void metoda2() {
        System.out.println("D1.metoda2()");
    }
}

class D2 extends D1 implements DI2 {
    public D2() {
        System.out.println("D2()");
    }

    public void metoda1() {
        System.out.println("D2.metoda1()");
    }

    public void metoda2() {
        System.out.println("D2.metoda2()");
    }
}

class D3 extends D2 implements DI33.DI34 {
    {
        System.out.println("D3-N");
    }

    public D3() {
        System.out.println("D3()");
    }

    public void metoda1() {
        System.out.println("D3.metoda1()");
    }

    public void metoda2() {
        System.out.println("D3.metoda2()");
    }

    void metodaF() {
        System.out.println("D3.metodaF()");
    }

    public void metodaR() {
        System.out.println("D3.metodaR()");
    }
}

interface DI1 {
    public default void metoda1() {
        System.out.println("DI1.metoda1()");
    }
}

interface DI2 extends DI1 {
    void metoda1();

    default void metoda2() {
        System.out.println("DI2.metoda2()");
    }
}

abstract interface DI3 extends DI2 {
    final int c = 10;

    default void metoda1() {
        System.out.println("DI3.metoda1()");
    }

    abstract void metoda2();
}

interface DI33 extends DI3 {

    interface DI34 {
        void metodaF();
    }
}