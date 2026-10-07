package usmeni.Z17;

// E1.java

class E1 extends Thread {

    int id = 0;

    public E1() {
        super();
    }

    public static void main(String[] args) {
        System.out.println("MainThread");
        new E1().start();
    }

    public void run() {
        System.out.println("Start...");
        E1[] niz = {new E2(1), new E2(0), new E3("3"),
                new E2(4), new E3("10")};
        for (E1 e : niz) {
            if (e.isDaemon()) {
                new Thread(e).run();
            } else {
                System.out.println("New thread...");
                e.start();
                try {
                    e.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        System.out.println("End");
    }

}

class E2 extends E1 implements Runnable {

    public E2(int id) {
        this.id = id > 1 ? id : this.id;
        if (this.id == id) {
            setDaemon(true);
        }
    }

    @Override
    public void run() {
        System.out.println("E2 - " + id + ": " + isDaemon());
        for (int i = 1; i < 6; i++) {
            System.out.println("E2 - " + id + ": " + i);
        }
    }

}

class E3 extends E2 {

    public E3(String s) {
        super(new Integer(s + ""));
    }

    public void run() {
        Runnable r = (() -> {
            System.out.println("nit: " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                System.out.println(i * 10);
            }
        });
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}