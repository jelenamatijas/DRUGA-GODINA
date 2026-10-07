public class H extends Thread{
  
  static String s="string";
  
  public static void main(String[] args){
    H h=new H();
    h.metoda(s);
    System.out.println(s);
  }
  
  public void metoda (String s){
    start();
    s=s+"test";
  }
  
  public void run(){
    for(int i=0;i<10000;i++){
      s=s+" "+i;
      if(i%10000==0)
        System.out.println(i);
    }
  }
}