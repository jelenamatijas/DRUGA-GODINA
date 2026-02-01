public class MatematickeOperacije 
{
  int operand1 = 0, operand2 = 0;
  
  MatematickeOperacije()
  {
    operand1 = 5;
    operand2 = 3;
  }
  
  MatematickeOperacije(int a,int b)
  {
    operand1 = a;
    operand2 = b;
  }
  
  public double proizvod ()
  {
    return (double)operand1 * (double)operand2;
  }
  
  public double kolicnik ()
  {
    return (double)operand1 / (double)operand2;
  }
  
  public boolean prviJeVeci()
  {
    if (operand1>operand2) return true;
    else return false;
  }
  
  public int sumaPrvih20CijelihBrojeva (int niz[])
  {
    int suma = 0;
    
    for (int i=0;i<20;i++)suma += niz[i];
    return suma;
  }
  
  public int razlikaBrojevaDjeljivihSa3 ()
  {
    int razlika = 100;
    
    for (int i=100;i>-1;i--)
    {
      if (i%3 == 0) razlika -=i;
    }
    
    return razlika;
  }
  
  
  public static void main (String args[])
  {
    MatematickeOperacije op1 = new MatematickeOperacije();
    MatematickeOperacije op2 = new MatematickeOperacije(2,4);
    
    System.out.println ("Proizvod = " + op1.proizvod());
    System.out.println ("Kolicnik = " + op1.kolicnik());
    System.out.println ("Da li je prvi veci = " + op1.prviJeVeci());
    System.out.println ("Razlika brojeva djeljivih sa 3 je: " + op1.razlikaBrojevaDjeljivihSa3());
  }
      
  
}
    