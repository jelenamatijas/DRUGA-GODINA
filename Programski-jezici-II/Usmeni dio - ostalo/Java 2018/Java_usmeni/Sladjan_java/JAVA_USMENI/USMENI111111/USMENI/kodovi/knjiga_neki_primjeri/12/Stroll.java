 public class Stroll {
 public static void main(String[] args) throws Exception {
 Thread t1 = new Thread(new Mosey());
/* t1.setPriority(1);
new Mosey().run();
t1.start();*/
 t1.setPriority(8);
t1.start();
new Mosey().run();
 }
 }