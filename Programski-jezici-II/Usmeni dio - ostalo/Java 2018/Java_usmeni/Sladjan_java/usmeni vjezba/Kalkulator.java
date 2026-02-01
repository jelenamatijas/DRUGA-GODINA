public class Kalkulator
{
  public int op1;
  private int op2;
  private static int p;
  
  Kalkulator(){};
  
  Kalkulator(int a, int b)
  {
  }
  
  public static void main(String[] args)
  {
    ProsireniKalkulator p = new ProsireniKalkulator();
    Kalkulator k =p;
    k.metoda();
    Kalkulator q = new Kalkulator2();
    System.out.println(q.op1);
  }
  
  void metoda()
  {
    System.out.println(p);
  }
 
  
}

class ProsireniKalkulator extends Kalkulator
{
  int c;
  
  ProsireniKalkulator(){}
  
  ProsireniKalkulator(int a,int b, int c)
  {
  }
  
  public void metoda()
  {
    super.metoda();
  }
  
  public void metoda1(){};
  
 
}

class Kalkulator2 extends ProsireniKalkulator
{
}
  