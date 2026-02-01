package usmeni.primjeri.Klasa21;

class A1 {
    private A1 a1;

    public A1() {
        System.out.println("A1");
    }

    public A1(A1 a1) {
        System.out.println("A1(A1)");
        this.a1 = a1;
    }

    void metoda() {
        System.out.println("metoda A1");
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

    private void metoda2() {
        System.out.println("metoda2 A2");
    }
}

class A3 {
    public A3() {
        System.out.println("a3");
    }
}

class Klasa21 extends A3 {
    private A1 a = new A2();
    private A2 a2 = new A2(new A1());

    public Klasa21() {
        a2.metoda();
        System.out.println("A4");
        a.metoda();
    }

    public static void main(String[] args) {
        Klasa21 a4 = new Klasa21();
        a4.metoda();
    }

    protected void metoda() {
        System.out.println("metoda Klasa21");
    }
}