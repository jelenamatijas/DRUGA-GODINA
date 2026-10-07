package usmeni.Z14;

import java.util.Arrays;

public class F1<T extends Runnable> extends F2<Runnable> implements FI {
    public static int c = 1;

    public static void main(String args[]) {
        FI f1 = new F1<Thread>();
        F1<F1> f2 = new F1<>();
        Runnable f3 = new F1<F1>();
        F2<Runnable> container = new F2<>(f1, f2, f3);
        System.out.println(container.toString());
        container.runAll();
        F3 f4 = new F3<Thread, Integer>(new Thread(new FI() {
            public void run() {
                System.out.println("Run " + c++);
            }
        }), 5);
        f4.startRunning();
        System.out.println("End");
    }

    public String toString() {
        return "T1";
    }

    ;
}

class F2<T extends Runnable> implements FI {
    T[] arr;

    public F2(T... elements) {
        arr = elements;
    }

    public void runAll() {
        for (T e : arr) {
            new Thread(e).run();
        }
    }

    public String toString() {
        return Arrays.asList(arr).toString();
    }
}

class F3<H extends Thread, G extends Integer> {
    G iter;
    H runner;

    public F3(H runner, G number) {
        iter = number;
        runner = runner;
    }

    public void startRunning() {
       /* for (int i = 0; i < iter; i++)
            new Thread(runner).run();*/
    }
}

interface FI extends Runnable {
    default void run() {
        System.out.println("Running...");
    }
}