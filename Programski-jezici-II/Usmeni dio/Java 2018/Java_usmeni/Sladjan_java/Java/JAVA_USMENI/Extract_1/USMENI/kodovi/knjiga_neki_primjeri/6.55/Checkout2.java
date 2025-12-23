 public class Checkout2 implements Runnable {
 void doStuff() { }
 synchronized void doSynch() {
 try { Thread.sleep(1000); }
 catch (Exception e) { System.out.print("e "); }
 }
 public static void main(String[] args) {
 long start = System.currentTimeMillis();
 new Thread(new Checkout2()).start();
 Thread t1 = new Thread(new Checkout2());
 t1.start();
 try { t1.join(); }
 catch (Exception e) { System.out.print("e "); }
 System.out.println("elapsed: "
+ (System.currentTimeMillis() - start));
 }
 public void run() {
 for(int j = 0; j < 4; j++) {
 doStuff();
 try { Thread.sleep(1000); }
 catch (Exception e) { System.out.print("e "); }
 doSynch();
 } } }