package usmeni.Z2;

// E1.java

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

class E1 extends Thread {
    static List<E1> threads = new ArrayList<>();
    final static Random rand = new Random();
    public E1 e;
    static int c = 1;
    int id;

    public E1() {
        id = (rand.nextDouble() < 1.0) ? c++ : 0;
        if (id % 3 == 0)
            this.setDaemon(true);
        threads.add(this);
        new Thread(this).run();
    }

    public E1(E1 e) {
        if (e != null)
            this.e = e;
    }

    public static void main(String[] args) {
        E1 e = new E1();
        Thread[] niz = {new E1(), new E1(), new Thread(new E1()), new E1(e), new E1(null)};
        System.out.println("Main start");
        int count = 0;
        for (int i = 0; i < niz.length; i++) {
            if (niz[i] instanceof E1) {
                E1 temp = (E1) niz[i];
                if (temp.e != null)
                    temp.e.start();
                System.out.println("Starting thread...");
                niz[i].run();
                count++;
            }
            if (count > 3) {
                System.out.println("Starting all threads...");
                runAll();
                break;
            }
        }
        System.out.println("Main end");
    }

    public void run() {
        Stream.iterate(1, e -> e + 1)
                .limit(5)
                .forEach(e -> System.out.println("E1(" + id + "): " + e));

    }

    public static void runAll() {
        for (var el : threads)
            el.start();
    }

}