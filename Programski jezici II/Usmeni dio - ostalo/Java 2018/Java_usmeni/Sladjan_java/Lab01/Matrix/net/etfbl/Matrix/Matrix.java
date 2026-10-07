package net.etfbl.Matrix;

import java.lang.Object;
import java.util.Scanner;
  
  public class Matrix 
{
  private int m=0,n=0;
  double matrica[][];
  
  public Matrix (int m,int n)
  {
     matrica = new double [m][n];
  }
  
  public void setData (double matrix[][],int m,int n)
  {
    this.m=m;
    this.n=n;
    matrica = new double [m][n];
    for (int i =0;i<m;i++)
      for (int j=0;j<n;j++) matrica[i][j] = matrix [i][j];
  }
  
  public double[][] getData()
  {
    return matrica;
  }
  
public Matrix multiply (Matrix b) 
  {
    int c=0,d=0,k=0;
     
         
         double multiply[][] = new double[m][b.n];
         double sum=0;
 
         
         for ( c = 0 ; c < m ; c++ )
         {
            for ( d = 0 ; d < b.n ; d++ )
            {   
               for ( k = 0 ; k < b.m ; k++ )
               {
                  sum = sum + matrica[c][k]*b.matrica[k][d];
               }
 
               multiply[c][d] = sum;
               sum = 0;
            }
         }
        
         
          Matrix mc = new Matrix(n,b.m);
          mc.matrica = multiply;
          
      
     return mc;
  }

public void ispisi()
{
  System.out.println("ISPISI>"+m+" "+n);
   for (int i=0;i<m;i++)
      for (int j=0;j<n;j++)
    {
      if (j==n-1) System.out.print(matrica[i][j] + " " + "\n");
      else System.out.print(matrica[i][j] + " ");
    }
}
  
 
      
    
  
      
  
}
  

  
  