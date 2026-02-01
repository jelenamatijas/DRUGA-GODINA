package usmeni.Z40;


import java.io.Serializable;

class A3 {

    static {
        System.out.println("a3-S2");
    }

    public A3() {
        System.out.println("a3()");
    }

    {
        System.out.println("a3-N1");
    }

    public void metoda() {
        new A2();
        System.out.println("metoda-a3()");
    }

    static {
        System.out.println("a3-S1");
    }
}

class A2 extends A3 {
    static A3 a3 = new A3();

    public A2() {
        System.out.println("A2()");
    }

    private void A2() {
    }

    {
        System.out.println("A2-N1");
    }

    public A2(A3 a3) {
        this();
        this.metoda();
        System.out.println("A2(a3)");
    }


    public A2(A3 a3, A2 a2) {
        this(a3);
        System.out.println("A2(a3,A2)");
        method1();

    }

    static public void method1() {
        System.out.println("method1()");
    }

    {
        System.out.println("A2-N2");
    }

    static {
        System.out.println("A2-S");
    }

    public void metoda() {
        super.metoda();
        System.out.println("A2-metoda()");
    }
}

class A4 extends A2 implements Serializable {
    private static A1 a1 = new A1(new A2(new A3()));

    public A4() {
        super();
    }

    {
        System.out.println("A4-N");
    }
}


public class A1 extends A2 {
    public static void main(String[] args) {
        //A1 a1 = new A1();
        Serializable s1 = new A4();
        //a1.metoda();
        //((A2) s1).method1();
    }

    public void metoda() {
        super.metoda();
        System.out.println("A1 metoda()");
    }

    static {
        new A2();
        System.out.println("A1-S");
    }

    public A1() {
        super(new A3(), new A2());
        System.out.println("A1()");
    }

    public A1(A3 a1) {
        super(a1);
        System.out.println("A1(a3)");
    }
}