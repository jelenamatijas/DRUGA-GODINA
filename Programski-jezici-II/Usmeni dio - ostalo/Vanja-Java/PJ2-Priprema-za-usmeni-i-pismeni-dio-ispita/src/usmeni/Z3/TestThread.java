package usmeni.Z3;


public class TestThread {
    public static void main(String[] args) {
        new T1().start();
        new T2().start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class T3 extends Thread {
    @Override
    public void run() {
        System.out.println("T3:" + this.isDaemon());
    }
}


class T1 extends Thread {
    public T1() {
        this.setDaemon(true);
    }

    @Override
    public void run() {
        System.out.println("T1:" + this.isDaemon());
        new T3().start();
    }
}

class T2 extends T1 {
    @Override
    public void run() {
        System.out.println("T2:" + this.isDaemon());
    }
}