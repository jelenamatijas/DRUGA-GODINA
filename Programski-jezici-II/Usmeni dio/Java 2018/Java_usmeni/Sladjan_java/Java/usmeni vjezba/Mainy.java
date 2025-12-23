import java.lang.String;


public class Mainy
{
  public Mainy[][] matrica ;
  public Mainy t;
  static int x = 0;
  
  static {
    x = 1;
  }
  
  public static void main(String[] args)
  {
    Mainy A = new Mainy();
    System.out.println("A.t je "+A.t);
    A.matrica = new Mainy[3][];
    for(int i =0;i<3;i++) A.matrica[i] = new Mainy[2];
    
    //Mainy[] niz = new Mainy[3];
    //for(int i=0;i<3;i++) niz[i] = new Mainy[4]; ne prolazi kompajler, ocekuje tip Mainy ne Mainy[]
    
    
    A.matrica[0][0] = new Mainy();
    for(int i=0; i<3;i++)
      for(int j=0;j<2;j++) System.out.println(A.matrica[i][j]);

  }
  
}