class Jog implements Runnable {
 public void run() {
 for(int i = 0; i < 8; i++) {
 try { Thread.sleep(200); }
 catch (Exception e) { System.out.print("exc "); }
 System.out.print(i + " ");
 } } }