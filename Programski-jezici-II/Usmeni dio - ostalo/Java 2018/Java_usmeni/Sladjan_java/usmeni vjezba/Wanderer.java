class Wanderer implements Runnable {
public void run() {
for(int i = 0; i < 2; i++)
System.out.print(Thread.currentThread().getName() + " ");
 }
}
 public class Wander {
 public static void main(String[] args) {
Wanderer w = new Wanderer();
 Thread t1 = new Thread();
Thread t2 = new Thread(w);
Thread t3 = new Thread(w, "fred");
 t1.start(); t2.start(); t3.start(); //prvi i drugi nist ne ispisuju
 } }
