public enum Enumeracija
{
    PONEDELJAK(1, 11.0), UTORAK(2), SRIJEDA(3), CETVRTAK(4), PETAK(5), SUBOTA;
    
    public int x;
    public double y;
    private static int count;
    
    Enumeracija()
    {
    
    }
    Enumeracija(int x)
    {
      this.x = x;
    }
    
    public int getX()
    {
      return x;
    }
    public static void main(String [] args)
    {
 Enumeracija x = Enumeracija.PONEDELJAK;
 System.out.println(x);
 Enumeracija y = Enumeracija.PONEDELJAK;
 K a = K.G;
 if (x != y) System.out.println("YES");
 System.out.println(a.a);
 System.out.println(y.y);
 for(Enumeracija e : Enumeracija.values())
 {
   System.out.println(e);
 }
    }

}
interface Brzine
{
  double gBrzina();
}
enum K implements Brzine
{
   G(9.81), M;
  double a;
  K()
  {
  
  }
  K(double a)
  {
    this.a = a;
  }
  public double gBrzina()
  {
    return a;
  }
}