 public class Drip extends Thread {
 public static void main(String[] args) {
 Thread t1 = new Thread(new Drip());
 t1.start();
 try{
 t1.join();
 }
 catch(Exception e){}
 for(int i = 0; i < 1000; i++) // Loop #1
 System.out.print(Thread.currentThread().getName() + " ");
 }
 public void run() {
 for(int i = 0; i < 1000; i++) // Loop #2
 System.out.print(Thread.currentThread().getName() + " ");
 } }