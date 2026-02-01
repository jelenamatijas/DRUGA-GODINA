package net.etfbl.labVjezba2;


/* @author Milan
 * @since 31.7.2014.
 * @version 1.1
 */

public class NizovniPodaci{
  public char slova[];
  public int tablicaMnozenja[][];
  
  public void setSlova(char s[]){
    slova=s;
  }
  
  NizovniPodaci(){
    tablicaMnozenja=new int[11][11];
    for (int i=0;i<11;i++)
      for (int j=0;j<11;j++)
      tablicaMnozenja[i][j]=i*j;
    
    slova=new char[]{'*'};
             }
      
  public void ispisSlova(){
    for(char a:this.slova)
      System.out.print(a);
    System.out.println();
  }
  
  public void ispisMatrice(){
    int z=2;
    System.out.println("   -   0   1   2   3   4   5   6   7   8   9  10");
     System.out.print("       "+1);
    for(int[] i:this.tablicaMnozenja){
      
      for(int j:i){
        if(j<10) System.out.print("   ");
        if(j>9&&j<100)System.out.print("  ");
        if(j==100)System.out.print(" ");
          System.out.print(j);
      }
       System.out.print("\n       "+z++);
    }
    }
    
  public static void main(String[] args){
    NizovniPodaci n= new NizovniPodaci();
    n.setSlova(new char[]{'p','o','z'});
    n.ispisSlova();
    n.ispisMatrice();
  }

}