 public class Pregnant extends Thread {
 int x = 0;
 public static void main(String[] args) {
 Runnable r1 = new Pregnant();
 new Thread(r1).start();
 new Thread(r1).start();
 }
 public void run() {
 for(int j = 0; j < 3; j++) {
 x = x + 1;
 x = x + 10;
 System.out.println(x + " ");
 x = x + 100;
 } } }