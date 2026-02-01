package usmeni.Z8;

public class Klasa20 extends Thread {
    public Klasa20() {
        System.out.println("Klasa20()");
    }

    public static void main(String x[]) {
        System.out.println("Start");
        Thread2.temp.start();
        new Klasa20().start();
        System.out.println("End");
    }

    public void run() {
        System.out.println("first");
        Thread2 niz[] = {new Thread2(1), new Thread2(), new Thread3(3), new Thread2()};
        for (Thread2 e : niz) {
            try {
                if (e instanceof Thread3) {
                    e.run();
                } else {
                    e.start();
                    e.join();
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("last");
    }
}

class Thread2 extends Thread {
    public static Thread temp = new Thread();
    static int c = -5;
    int id;

    public Thread2() {
        this(0);
    }

    Thread2(int id) {
        System.out.println("Thread2()");
        this.id = (id > 0) ? id : c++;
        if (id % 2 == 0)
            this.setDaemon(true);
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 - " + id + ": " + i);
        }
        if (this.id != 0 && this.id % 3 == 0)
            temp.start();
    }
}

class Thread3 extends Thread2 implements Runnable {
    Thread3(int id) {
        super(id);
        System.out.println("Thread3()");
    }
}