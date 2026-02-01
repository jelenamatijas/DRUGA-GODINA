package usmeni.Z32;

// C1.java
class C1 {
    public static boolean k = false;

    C1() {
        System.out.println("C1()");
    }

    public static void main(String[] args) {
        C1 c1 = new C1();
        try {
            c1.metoda();
            System.out.println("main 1");
        } catch (CE2 e) {
            System.out.println("main 2: " + e);
        } catch (CE3 e) {
            System.out.println("main 3: " + e);
        } catch (CE1 e) {
            System.out.println("main 4: " + e);
        } catch (Throwable e) {
            System.out.println("main 5: " + e);
        }
        try {
            C2 c2 = new C2();
            c2.metoda();
        } catch (CE1 e) {
            System.out.println("Last catch.");
        } finally {
            System.out.println("Rezultati " + (C1.k ? "ce" : "nece") + " biti danas objavljeni");
        }
    }

    void metoda() throws Throwable {
        try {
            C2 c2 = new C2();
            c2.metoda();
            System.out.println("C1: metoda()");
        } catch (CE3 ce3) {
            System.out.println("catch C1");
            new C1().metoda();
        } finally {
            System.out.println("finally");
            return;
        }
    }
}

class C2 {
    C2() {
        System.out.println("C2()");
        if (C1.k = !C1.k)
            throw new CE3();
    }

    void metoda() throws CE1 {
        C3 c3 = new C3();
        System.out.println("C2: metoda()");
        c3.metoda();
    }
}

class C3 {
    C3() {
        System.out.println("C3()");
    }

    protected void metoda() throws CE1 {
        System.out.println("C3: metoda()");
        throw new CE2("CE2");
    }
}

class CE1 extends Exception {
    private String name;

    CE1(String s) {
        super(s);
        this.name = s;
        System.out.println("CE1: " + name);
    }
}

class CE2 extends CE1 {
    String name;

    CE2(String s) {
        super(s);
        System.out.println("CE2: " + name);
    }
}

class CE3 extends Error {
    CE3() {
        System.out.println("CE3");
    }
}