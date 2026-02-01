 public class Stone implements Runnable {
 static int id = 1;
 public void run() {
 try {
 id = 1 - id;
 //System.out.println(id);
 if(id == 0) { pick(); } else { release(); }
 } catch(Exception e) { }
 }
 private static synchronized void pick() throws Exception {
 System.out.print("P "); System.out.print("Q ");
 }
 private synchronized void release() throws Exception {
 System.out.print("R "); System.out.print("S ");
 }
 public static void main(String[] args) {
 Stone st = new Stone();
 new Thread(st).start();
 new Thread(st).start();
 } }