public class E1{
  public static void main(String[] argd){
    F1 f1=new F1("Jedan");
    f1.run();
    
    F1 f2=new F1("Dva");
    f2.run();
    f2.yield();
  }
}

class F1 extends Thread{
  private String naziv="";
  
  F1(String s){
    naziv=s;
  }
  
  public void run(){
    for(int i=0;i<20;i++){
      try{
        //sleep(100);
      }catch(Exception e){}
      yield();
      System.out.println(naziv);
    }
  }
}