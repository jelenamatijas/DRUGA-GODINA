package usmeni.OL1OL2.P1;

public class A {
    B b1 = new B();
    B b2 = new B();

    static {
        System.out.println("static A");
    }

    {
        System.out.println("non - static A ");
    }

    public A() {
        System.out.println("Constructor A");
    }

    public static void main(String[] args) {
        new A();
        new B();
    }
}

class B {
    static {
        System.out.println("static B");
    }

    {
        System.out.println("non - static B ");
    }

    public B() {
        System.out.println("Constructor B");
    }
}