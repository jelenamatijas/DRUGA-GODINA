 class Jiggy extends Thread {
 Jiggy(String n) { super(n); }
 public void run() {
 for(int i = 0; i < 100; i++) {
 if("t1".equals(Thread.currentThread().getName()) && i == 5) {
 new Jiggy("t3").start();
 throw new Error();
 }
 if("t2".equals(Thread.currentThread().getName()) && i == 5) {
 new Jiggy("t4").start();
 throw new Error();
 }
 System.out.print(Thread.currentThread().getName() + "-");
 }
 }
 public static void main(String[] args) {
 Thread t1 = new Jiggy("t1");
 Thread t2 = new Jiggy("t2");
 t1.setPriority(1); t2.setPriority(9);
 t2.start(); t1.start();
 } }