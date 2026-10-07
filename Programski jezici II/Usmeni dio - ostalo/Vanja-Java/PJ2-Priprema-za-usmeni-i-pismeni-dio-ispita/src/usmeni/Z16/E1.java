package usmeni.Z16;

// E1.java

class E1 extends Thread implements EI {
    static int c = 1;

    {
        c++;
    }

    int id;
    E1 e;

    public E1() {
        id = c;
        if (id % 2 == 0)
            setDaemon(true);
        else
            setDaemon(false);
    }

    public E1(E1 e) {
        this();
        this.e = e;
    }

    public static void main(String argv[]) throws Exception {
        E1 prvi = new E1();
        try {
            prvi.start();
            ;
            prvi.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runnable[] niz = {new E1(), new Thread(new E1()),
                new E1(prvi), new Thread(), new Thread()};
        System.out.println("First line");
        for (Runnable r : niz) {
            System.out.println("Checking...");
            if (r instanceof Thread) {
                Thread t = (Thread) r;
                if (t.isDaemon()) {
                    System.out.println("Starting background thread...");
                    new Thread(r);
                } else {
                    if (t instanceof EI) {
                        ((E1) t).run("arg1");
                        E1 tmp = (E1) t;
                        if ((tmp).e != null)
                            tmp.e.start();
                    } else
                        t.run();
                }
            } else {
                new Thread(r).start();
            }
        }
        System.out.println("Last line");
    }

    public void run() {
        for (int i = 1; i < 6; i++)
            System.out.println("E1(" + id + "): " + i);
    }
}

interface EI extends Runnable {
    default void run(String... args) {
        if (args.length > 0) {
            System.out.println(args[0]);
            new Thread(this).start();
        } else
            new Thread().run();
    }
}