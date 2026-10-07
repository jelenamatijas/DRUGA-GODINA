public class E{
  public static void main(String[] args){
    F f1=new F("jedan");
    f1.run(); //poziva se metoda run a ne start()
    //System.out.println("------------------");
    F f2=new F("dva");
    f2.run();
  }
}

class F extends Thread{
  private String naziv="";
  
  F(String s){
    naziv=s;
  }
  
  public void run(){
    for(int i=0;i<20;i++){
      try{
        //sleep(1000);
      }catch(Exception e){}
      //yield();
      System.out.println(naziv);
    }
  }
}
  