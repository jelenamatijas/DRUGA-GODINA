package usmeni.Z4;

class T5 {
    private void m1() {
        System.out.println("1");
    }

    public static void main(String[] args) {
        new TA() {
            public void m1() {
                System.out.println("1");
            }
        }.m2();
        System.out.println("=1=");
        TB tb = new TB();
        tb.m1();
        System.out.println("=2=");
    }
}


interface TI {
    void m1();

    void m2();
}

interface T1 extends TI {
    abstract void m3();

    interface T11 extends TI, AutoCloseable {
        default void m1() {
            System.out.println("T11.m1()");
        }

        default void close() throws Exception {
            System.out.println("T11.close");
        }

        abstract void m2();
    }
}

interface T2 extends T1 {
    default void m3() {
        System.out.println("T2.m3()");
    }
}

abstract class TA implements T2 {
    public void m2() {
        System.out.println("2");
    }


}

class TB extends TA {
    @Override
    public void m1() throws IndexOutOfBoundsException {
        int j = 5;
        try (T11 func = () -> {
            for (int i = 1; i < 2; i++)
                System.out.println("Test" + j);
        };) {
            func.m2();
        } catch (Exception e) { // dodan je catch clause da ne bude compile error
            throw new RuntimeException(e);
        }
    }


}