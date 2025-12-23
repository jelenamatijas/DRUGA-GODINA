import java.lang.Math;
import java.util.Scanner;

public class Krug extends Oblik
{
  private double poluprecnik;
  
  public Krug() {this.poluprecnik = 0;};
  
  public Krug (double r)
  {
    this.poluprecnik = r;
  }
  
  @Override
  public void iscrtaj()
  {
    System.out.println("Ovo je "+this.getClass()+  " i poluprecnik je " + this.poluprecnik + " povrsina je " + povrsina());
  }
  
  @Override
  
  public double povrsina()
  {
    return (Math.PI)*Math.pow(this.poluprecnik,2);
  }
  
}