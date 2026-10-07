import java.util.Scanner;
import java.lang.Math;


public class PravougliTrougao extends Oblik
{
  private int kateta;
  private int visina;
  
  public PravougliTrougao () {};
  
  public PravougliTrougao (int a,int b)
  {
    this.kateta = a;
    this.visina = b;
  }
  
  @Override
  public void iscrtaj()
  {
    System.out.println("Ovo je " + this.getClass() + ", kateta je " + this.kateta + ", visina je " + this.visina + " povrsina je " + povrsina());
  }
  
  @Override
  public double povrsina()
  {
    return (this.kateta * this.visina)/((double)2);
  }
}