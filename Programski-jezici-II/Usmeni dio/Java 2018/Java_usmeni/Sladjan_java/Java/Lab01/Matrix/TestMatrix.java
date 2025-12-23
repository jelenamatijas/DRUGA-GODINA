package Matrix;

import net.etfbl;
  
public class TestMatrix
{
   public static void main (String args[]) throws MatrixException
  {
    Scanner sc = new Scanner (System.in);
    double mm[][] = new double [3][3];
    int m = 3, n =3;
    Matrix objekat1 = new Matrix(3,3);
    Matrix objekat2 = new Matrix(2,2);
    
    
    for (int i=0;i<m;i++)
      for (int j=0;j<n;j++) mm[i][j] = sc.nextDouble();
    
    for (int i=0;i<m;i++)
      for (int j=0;j<n;j++)
    {
      if (j==n-1) System.out.print(mm[i][j] + " " + "\n");
      else System.out.print(mm[i][j] + " ");
    }
    
    objekat1.setData(mm,3,3);
    
    double aa[][] = new double [m][n];
    aa = objekat1.getData();
    
    System.out.println("Ovo je matrica aa");
     for (int i=0;i<m;i++)
      for (int j=0;j<n;j++)
    {
      if (j==n-1) System.out.print(aa[i][j] + " " + "\n");
      else System.out.print(aa[i][j] + " ");
    }
    //objekat1.ispisi();
    
     
    int p=0,q=0;
    System.out.println ("Unesite dimenzije druge matrice");
    System.out.print ("p= ");
    p = sc.nextInt();
    System.out.print ("q= ");
    q = sc.nextInt();
    double nn1[][]= new double [p][q];
    for (int i=0;i<p;i++)
      for (int j=0;j<q;j++) nn1[i][j] = sc.nextDouble();
    objekat2.setData (nn1,p,q);
    objekat2.ispisi();
    System.out.println ("p = " + p + " q= " + q);
    if (objekat1.n!=objekat2.m) throw new MatrixException();
    else
    {
    Matrix objekat3 = new Matrix (objekat1.m,objekat2.n);
    objekat3  = objekat1.multiply(objekat2);
    objekat3.ispisi();
    }
  }
}