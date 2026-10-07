package usmeni.Z29;

// C1.java

import java.io.IOException;

class C1 {

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
        } catch (CE1 e) {
            System.out.println("main 3: " + e);
        } catch (Throwable e) {
            System.out.println("main 4: " + e);
        } finally {
            throw new CE3("kraj");
        }
    }

    int metoda() throws Throwable {
        C2 c2 = new C2();
        try {
            System.out.println(c2.metoda());
            System.out.println("C1: metoda()");
            throw new CE2("RunTime");
        } finally {
            System.out.println("finally");
            throw new CE1("CE22");
        }
    }
}

class C2 {
    C2() {
        System.out.println("C2()");
    }

    int metoda() throws CE1 {
        C3 c3 = new C3();
        try {
            System.out.println("C2: metoda()");
            System.out.println(c3.metoda());
            return 7;
        } finally {
            return 1;
        }
    }
}

class C3 {
    C3() {
        System.out.println("C3()");
    }

    protected int metoda() throws CE1 {
        System.out.println("C3: metoda()");
        try {
            throw new IOException("poruka");
        } finally {
            return 5;

        }
    }
}

class CE1 extends Error {
    String name;

    CE1(String s) {
        super(s);
        System.out.println("CE1: " + name);
    }
}

class CE2 extends CE1 {
    CE2(String s) {
        super(s);
        System.out.println("CE2: " + name);
    }
}

class CE3 extends RuntimeException {
    CE3() {
        System.out.println("CE3()");
    }

    CE3(String message) {
        this();
        System.out.println("CE3(S)");
    }
}