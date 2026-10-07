package usmeni.Z25;

// DI1.java
public interface DI1 {
    public static void main(String[] args) {
        D d1 = new D(5);
        DI2[] arr = {d1, new DI3.DI33() {
            public void metoda1() {
                System.out.println("DI33: metoda1()");
            }
        }, new D(10)};
        arr[0].metoda1();
        arr[1].metoda1();
        arr[2].metoda1();
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

    interface DI33 extends DI2 {
        void metoda1();
    }
}

interface DI4 extends DI3 {
    default void metoda1() {
        System.out.println("DI4: metoda1()");
    }

    void metoda2();

    interface DI44 extends DI4, DI3.DI33 {
        default void metoda2() {
            System.out.println("DI44: metoda2()");
        }

        default void metoda1() {
            System.out.println("DI44: metoda1()");
        }
    }
}

class D implements DI4.DI44 {
    int x;

    private D() {
        System.out.println("D()");
    }

    public D(int x) {
        this();
        this.x = x;
    }

    @Override
    public void metoda1() {
        System.out.println("D: metoda1()");
    }
}