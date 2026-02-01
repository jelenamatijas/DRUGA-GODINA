public class KO
{
  public static int i =5;
  int j = 7;
  
  static {
    int k;
    k=3;
  }
  
  {
    int j;
    int i;
    i=1 + 0;
  }
  
  
  void metoda1()
  {
    int j = 3;
    System.out.println("J : " + j);
    
  }
  
  static void metoda2()
  {
    int i = 4;
    System.out.println("I u metodi  2 je : " + i);
  }
  
  public static void main(String[] args)
  {
    int j = 10;
    int i = 11;
    
    System.out.println("Main " + j + i);
    
    KO q = new KO();
    
    q.metoda1();
    KO.metoda2();
  }
}