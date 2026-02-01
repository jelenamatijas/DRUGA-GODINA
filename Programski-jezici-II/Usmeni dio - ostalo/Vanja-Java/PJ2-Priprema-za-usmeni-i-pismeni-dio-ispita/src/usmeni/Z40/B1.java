package usmeni.Z40;

public class B1 {

    static {
        System.out.println("B-S2");
    }

    public B1() {
        System.out.println("B1()");
    }

    static {
        System.out.println("B-S1");
    }
}

class B2 extends B1 {
    static B1 b1 = new B1();

    public B2() {
        System.out.println("B2()");
    }

    static {
        System.out.println("B2-S");
    }
}

class B3 extends B2 {
    public B3() {
        System.out.println("B3()");
    }

    static {
        System.out.println("B3-S");
    }
}

class B4 extends B2 {
    public B4() {
        System.out.println("B4");
    }

    public static void main(String[] args) {

        //new B4();
        new B3();
        new B4();
    }

    static {
        System.out.println("B4-S");
    }
}