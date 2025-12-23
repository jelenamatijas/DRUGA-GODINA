interface K1
{
  void metoda();
}

interface K2 
{
  int metoda();
}

public abstract class U implements K1,K2
{
  public abstract void metoda();
  public abstract int metoda();
  
}