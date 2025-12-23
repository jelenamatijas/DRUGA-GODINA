public class Test64{
  
  public static int a = 5;
  
  public void metoda(Test64 o){
    o.a=7;
     System.out.println(o.a);
  }
  
  public static void main(String [] args){
    final Test64 t = new Test64();
    t.metoda(t);
    System.out.println(t.a);
  }
}