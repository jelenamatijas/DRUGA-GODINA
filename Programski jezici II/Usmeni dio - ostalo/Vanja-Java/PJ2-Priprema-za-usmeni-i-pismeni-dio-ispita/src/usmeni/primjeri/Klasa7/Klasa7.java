package usmeni.primjeri.Klasa7;

public class Klasa7 {
    public static void main(String[] args) {
        Klasa7 e = new Klasa7();
        Klasa8 f = new Klasa8();
        try {
            f.metoda();
            e.metoda();
        } catch (Exception t) {
            System.out.println("catch 1");
        } finally {
            System.out.println("finally");
        }
        e.metoda2();
    }

    void metoda() throws CE1 {
        throw new CE2("Error 2");
    }

    void metoda2() throws CE3 {
        throw new CE3();
    }
}

class Klasa8 extends Klasa7 {
    void metoda() {
        try {
            throw new CE1();
        } catch (CE1 e) {
            System.out.println("catch 2");
        }
    }
}

class CE1 extends Exception {
    public CE1() {
        System.out.println("CE1 - 1");
    }

    public CE1(String s) {
        System.out.println(s);
    }
}

class CE2 extends CE1 {
    public CE2() {
        System.out.println("CE2 - 1");
    }

    public CE2(String s) {
        System.out.println("CE2 - 2");
    }
}

class CE3 extends RuntimeException {
    public CE3() {
        System.out.println("CE3 - 1");
    }
}