package net.etfbl.lab2.triangle;

public class TriangleMatrixException extends Exception{
  
  public TriangleMatrixException(){
    super("Dimenzija mora biti veca od 0");
  }
  
  public TriangleMatrixException(String msg){
    super(msg);
  }  
 
}
