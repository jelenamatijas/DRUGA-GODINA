// DI1.java

public interface DI1 {
    public static void main(String[] args) {
        new DI2() {
            public void metoda2() {
                System.out.println("Main: metoda2()");
            }
            void metoda3() {
                System.out.println("Main: metoda3()");
            }
        }.metoda1();
    }
}

abstract interface DI2 extends DI1 {
    default void metoda1() {
        System.out.println("DI2: metoda1()");
    }
    default void metoda2() throws Exception {
        System.out.println("DI2: metoda2()");
    }
}

interface DI3 extends DI2 {
    abstract void metoda1();
    default void metoda2() throws RuntimeException {
        System.out.println("DI3: metoda3()");
    }
    void metoda3();
}

interface DI4 extends DI2, DI3 {
    default void metoda1() {
        System.out.println("DI4: metoda1()");
    }
    void metoda2();
}

