package net.etfbl.lab2;

public class NizovniPodaci {
  private int [][] tablicaMnozenja;
  private char [] slova;
  
  public NizovniPodaci(){
    tablicaMnozenja = new int [11][11];
    for(int i=0; i<11; i++){
      for(int j=0;j<11; j++){
        tablicaMnozenja[i][j] = i*j;
      }
    }
  }
  
  public int[][] getTablicaMnozenja(){
    return tablicaMnozenja;
  }
  
  public void setTablicaMnozenja(int [][] tablica){
    tablicaMnozenja = tablica;
  }
  
  public char[] getSlova(){
    return slova;
  }
  
  public void setSlova(char [] s){
    slova = s;
  }
  
  public void printSlova(){
    if(slova != null){
      for(int i=0; i<slova.length; i++){
        System.out.print(slova[i]);
      }
    } else {
      System.out.println("Nema podataka!");
    }
    System.out.println();
  }
  
  public void printTablicaMnozenja(){ 
    System.out.print(" * ");
    for(int i=0; i<11; i++){
      System.out.print(String.format("%3d",i) + " ");
    }    
    System.out.println();
    for(int i=0; i<11; i++){
      System.out.print(String.format("%2d",i) + " ");
      for(int j=0;j<11; j++){        
        System.out.print(String.format("%3d", tablicaMnozenja[i][j])+ " ");
      }
      System.out.println();
    }
  }
  
  /*
  public static void main(String args[]){
    NizovniPodaci n = new NizovniPodaci();
    n.printTablicaMnozenja();
    char [] slova = {'j', 'a', 'v', 'a'};
    n.setSlova(slova);
    n.printSlova();
  }*/

}