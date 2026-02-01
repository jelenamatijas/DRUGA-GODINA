package usmeni.Z24;

class A1 {
    private A1 a1;

    static {
        new A3();
        System.out.println("A1-S");
    }

    public A1() {
        System.out.println("A1");
    }

    {
        System.out.println("A1-N");
    }

    public A1(A1 a1) {
        System.out.println("A1(A1)");
        this.a1 = a1;
        this.metoda();
        new A1();
    }

    void metoda() {
        System.out.println("metoda A1");
    }

    void metoda2() {
        System.out.println("metoda2 A1");
    }
}

class A2 extends A1 {
    A1 a1;

    {
        System.out.println("A2-N");
    }

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

class A3 {
    {
        System.out.println("a3-N1");
    }

    public A3() {
        System.out.println("a3");
    }

    {
        System.out.println("a3-N2");
    }

    public A3(A2 a2, A1 a1) {
        super();
        System.out.println("a3(A2,A1)");
    }
}

class A4 extends A3 {
    private A1 a = new A2();
    private A2 a2 = new A2(new A1());
    private A1 a1 = new A1(new A1());

    public A4() {
        a2.metoda();

        System.out.println("A4");
        a.metoda();
    }

    public static void main(String[] args) {
        A4 a4 = new A4();
        System.out.println("===================");
        a4.metoda();
        System.out.println("===================");
        A5 a5 = new A5();
        System.out.println("===================");
        ((A1) a5).metoda2();
    }

    protected void metoda() {
        System.out.println("metoda Klasa21");
    }
}

class A5 extends A1 {

    A1 a1;

    static {
        new A3(new A2(), new A1());
        System.out.println("A5-S");

    }

    public A5() {
        super();
        {
            System.out.println("A5");
        }
    }

    public void metoda2() {
        System.out.println("A5.metoda2()");
    }
}