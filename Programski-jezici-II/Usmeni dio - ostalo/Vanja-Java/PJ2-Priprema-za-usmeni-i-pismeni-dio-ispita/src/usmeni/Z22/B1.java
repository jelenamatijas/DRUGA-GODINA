package usmeni.Z22;

// B1.java
abstract class B1 {
    B1() {
        super();
        System.out.println("B1()");
    }

    public static void main(String[] args) {
        B3 b3 = new B3();
        b3.metoda();
        B2 b2 = b3;
        b2.metoda();
        B1 b1 = b2;
        b1.metoda();
        new B2() {
            public void metoda() {
                System.out.println("Anonimna metoda()...");
            }

            public void m1() {
                System.out.println("Anonimna m1()");
            }
        }.metoda();
        B1 ref = new B3();
        B1 test = (B1) new B3();
        ref.m3();
        test.m2();
        System.out.println(ref instanceof I4);
        if (test instanceof I4) {
            ((I4) test).m1();
        }
    }

    private void metoda() {
        System.out.println("B1 metoda...");
    }

    private void m3() {
        System.out.println("B1 m3()");
    }

    public void m2() {
        System.out.println("B1 m2()");
    }
}

abstract class B2 extends B1 implements I1 {
    B2() {
        System.out.println("B2()");
    }

    abstract protected void metoda();

    void metoda2() {
        System.out.println("B2 metoda...");
    }

    public void m2() {
        System.out.println("B2 m2()");
    }
}

final class B3 extends B2 implements I4 {
    B3() {
        super();
        System.out.println("B3()");
    }

    public void metoda() {
        System.out.println("B3 metoda...");
    }
}

interface I1 {
    String element = "PJ2";

    void m1();
}

interface I4 extends I3 {
    default void m1() {
        System.out.println("I4 m1()");
    }
}

interface I2 extends I1 {
    default void m1() {
        System.out.println("deafult m1()...");
    }

    interface I22 extends I1 {
        String element = "PJ2";

        void m2();

        default void m3() {
            System.out.println("I22 m3()");
        }
    }
}

interface I3 extends I2.I22 {
    String element = "PJ2";

    default void m2() {
        System.out.println("I3 m2()");
    }
}