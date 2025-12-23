import java.util.Scanner;
import java.lang.Math;

public class Pravougaonik extends Oblik
{
  private int a;
  private int b;
  
  public Pravougaonik() {};
  
  public Pravougaonik (int a,int b)
  {
    this.a = a;
    this.b = b;
  }
  
  
  @Override
  public void iscrtaj(){
    System.out.println("Ja sam "+this.getClass()+". Moje stranice su a="+a+", b="+b+" a povrisna je "+povrsina());
    System.out.println("A ovako izgledam: ");
    for(int i=0; i<a; i++){
      for(int j=0; j<b; j++){
      System.out.print("* ");
      }
      System.out.println();
    }
  }
  
  @Override
  public double povrsina()
  {
    return ((double)a)/((double)b);
  }
  
}