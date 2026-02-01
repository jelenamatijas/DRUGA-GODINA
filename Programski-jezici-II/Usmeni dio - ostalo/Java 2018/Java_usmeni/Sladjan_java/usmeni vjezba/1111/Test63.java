public class Test63{
  
  public static int a = 5;
  
  public void metoda(){
    System.out.println(++a);
    a=a-2;
    System.out.println(a);
  }
  
  public static void main(String[] args){
    Test63 t = new Test63();
    t.metoda();
    System.out.println(Test63.a);
  }
}