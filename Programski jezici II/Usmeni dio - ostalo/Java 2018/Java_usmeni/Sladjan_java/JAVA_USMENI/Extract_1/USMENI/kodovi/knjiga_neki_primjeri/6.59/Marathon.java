 
 public class Marathon {
 public static void main(String[] args) throws Exception {
 Jog j1 = new Jog();
 Thread t1 = new Thread(j1);
 t1.start();
 t1.sleep(500);
 System.out.print("pre ");
 t1.interrupt();
 t1.sleep(500);
 System.out.print("post ");
 } }