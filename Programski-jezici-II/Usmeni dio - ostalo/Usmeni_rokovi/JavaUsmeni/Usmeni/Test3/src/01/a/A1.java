// A1.java

import java.io.Serializable;

public class A1 {
    static A1 a1;
    static {
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
}

class A2 extends A1 {
    {
        System.out.println("A2-N");
    }
    
    public A2() {
        super(new A1());
        System.out.println("A2()");
    }
    protected A2(A1 a1) {
        super();
        A1.a1 = a1;
        System.out.println("A2(A1)");
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
        System.out.println("A3: metoda3()");
    }
}

class A3 extends A2 implements Serializable {
    static {
        System.out.println("A3-S");
    }
    {
        System.out.println("A3-N");
    }
    
    public A3() {
        super();
        System.out.println("A3()");
    }
    public A3(A2 a2) {
        super(a2.a1);
        System.out.println("A3(A2)");
    }
    public void metoda3() {
        System.out.println("A3: metoda3()");
    }
}

class A4 extends A3 {
    A3 a3 = new A3(new A2(new A1()));
    
    public A4() {
        System.out.println("A4()");
    }
    public static void main(String[] args) {
        A4 a4 = new A4();
        a4.metoda1();
        a4.metoda2();
        a4.metoda3();
        a4.a3.metoda1();
        a4.a3.metoda2();
        a4.a3.metoda3();
    }
}
