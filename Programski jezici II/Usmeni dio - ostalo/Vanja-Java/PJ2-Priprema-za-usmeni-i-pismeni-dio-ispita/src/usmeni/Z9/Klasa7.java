package usmeni.Z9;


// Klasa7.java
class Klasa7 {
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
        try {
            throw new CE2("Error 2");
        } finally {
            System.out.println("finally");
            throw new CE3();
        }
    }

    int metoda2() throws CE3 {
        int x = 4;
        System.out.println("Metoda 2 pocetak");
        try {
            System.out.println("metoda2()");
            throw new CE3();
        } finally {
            System.out.println("finally metoda2");
            return 10;
        }
        // Compile Error Unreachable statement -> zakomentarisano zbog vjezbe ispisa zadatka
        //System.out.println("metoda2() kraj");
        //throw new CE3();
        //return 11;
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