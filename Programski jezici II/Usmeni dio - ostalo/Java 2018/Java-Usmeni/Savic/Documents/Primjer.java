public class Primjer extends Thread{
  volatile static int i;
  static Object o = new Object();
  Primjer(){
  }
  
  public synchronized void run(){
  synchronized(o)
  {
  i++;
  }
  System.out.println(i);
  }
  
  public static void main(String args[]) throws Exception{
    int in = 20;
  
    Primjer[] p = new Primjer[100000];
    for(int i = 0; i < 10; ++i){
    p[i] = new Primjer();
    p[i].start();
    }
    Integer o = new Integer(2);
    Integer j = o;
    o++;
    System.out.println(o + " " + j);
  }
}