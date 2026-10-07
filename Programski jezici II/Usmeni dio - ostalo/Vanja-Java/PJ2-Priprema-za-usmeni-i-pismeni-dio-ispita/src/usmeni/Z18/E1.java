package usmeni.Z18;

// E1.java

public class E1 extends Thread {

    int id = 0;
    E2 e2 = null;

    public E1() {
        super();
    }


    public static void main(String[] args) {

        try {
            System.out.println("MainThread");
            E2 e2 = new E2(101);
            e2.start();
            e2.join();
            E1 e1 = new E1();
            e1.start();
            e1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main end");
    }

    public void run() {
        System.out.println("Start...");
        E1[] niz = {new E2(1), new E2(e2), new E3("3"),
                new E2(4), new E3("5")};
        for (E1 e : niz) {
            if (e instanceof E1 && e.e2 != null) {
                try {
                    e.join();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

            if (e.isDaemon()) {
                Thread t = new Thread(e);
                try {
                    t.start();
                    t.join();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            } else {
                System.out.println("New thread...");
                e.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
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

    public E2(E2 e2) {
        this.e2 = e2;
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
}