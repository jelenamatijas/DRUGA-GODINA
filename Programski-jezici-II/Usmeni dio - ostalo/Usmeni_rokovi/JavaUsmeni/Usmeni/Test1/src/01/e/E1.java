// E1.java

public class E1 extends Thread {
    
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
        E1[] niz = {new E2(1), new E2(2), new E3("3"), 
            new E2(4), new E3("5")};
        for (E1 e : niz) {
            if (e.isDaemon()) {
                new Thread(e).start();
            } else {
                System.out.println("New thread...");
                e.run();
                e.start();
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
}