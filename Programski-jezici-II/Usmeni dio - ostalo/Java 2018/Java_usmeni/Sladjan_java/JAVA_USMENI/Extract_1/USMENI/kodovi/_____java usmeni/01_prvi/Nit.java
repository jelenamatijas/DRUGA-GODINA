public class Nit implements Runnable{
  String string="Yes";
  
  public void run(){
    this.string="No";
  }
  public static void main(String[] args){
    Nit t=new Nit();
    new Thread(t).start();
    for(int i=0;i<10;i++)
      System.out.print(t.string);
  }
}