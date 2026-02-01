public class ThreadD extends Thread {
 private String name;
 public ThreadD(String name) {
  this.name = name;
 }
 public void run() {
  Runnable r = new Runnable() {
    public void run() {
   for(int i=0; i<10; i++){
    System.out.println(name + "1: " + i);
    }
   }
  };
  new Thread(r).start();
  
for(int i=0; i<10; i++){
   System.out.println(name + "2: " + i);
 
  }
 }
 public static void main(String args[]){
  ThreadD a = new ThreadD("A");
  ThreadD b = new ThreadD("B");
  a.start();
  b.start();
 }
}
