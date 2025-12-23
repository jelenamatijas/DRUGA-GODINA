package net.etfbl.lab2.triangle;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Opis TriangleMatrix
 * 
 * @author Igor Dujlovic
 * @version 1.0 March 7, 2014.
 */
public class TriangleMatrix {
  private double [][] matrix;
  
  /**
   * Metoda za dobijanje matrice
   * @return matrica
   */
  public double[][] getMatrix(){
    return matrix;
  }
  
  /**
   * Konstruktor
   */
  public TriangleMatrix() throws TriangleMatrixException {
    System.out.println("Unesite dimenzije matrice: ");
    Scanner scan = new Scanner(System.in);
    System.out.print("m = ");
    int m = scan.nextInt();
    if(m<=0){
      throw new TriangleMatrixException();
    }
    System.out.print("n = ");
    int n = scan.nextInt();
    if(n<=0){
      throw new TriangleMatrixException();
    }
    
    matrix = new double [n][m];
    System.out.println("Unesite vrijednosti matrice:");
    for(int i=0; i<n; i++){
      for(int j=0; j<m; j++){
        System.out.print("Matrica["+i+"]["+j+"] = ");
        matrix[i][j] = scan.nextDouble();
      }
    }
    scan.close();
  }
  /**
   * Metoda za ispis gornje trougaone matrice
   */
  @Override
  public String toString(){
    String ret = "";
    for(int i=0; i<matrix.length; i++){
      for(int j=0; j<matrix[0].length; j++){
        if(j>=i){
          ret += matrix[i][j] + " ";
        } else {
          ret +="    ";
        }
      }
      ret +="\n";
    }
    return ret;
  }
 
  /**
   * Metoda za ispis sortiranih vrijednosti koje se nalaze na glavnoj dijagonali matrice
   */
  public void printSortedDiagonalValues(){
     double [] temp = new double[matrix[0].length];
     for(int i=0; i<matrix.length; i++){
      for(int j=0; j<matrix[0].length; j++){
        if(j==i){
          temp[i] = matrix[i][j];
        }
      }
     }
     //rucno
     for(int i =0; i<temp.length; i++){
       for(int j =i; j<temp.length; j++){
         if(temp[i]>temp[j]){
           double t = temp[j];
           temp[j] = temp[i];
           temp[i] = t;
         }
       }       
     }
     //jedan nacin
    // Arrays.sort(temp);
     for(int i=0; i<temp.length; i++)
       System.out.println(temp[i]);
  }
  
  /**
   * Staticka metoda koja sabira elemente gornje trougaone matrice
   * @param mat matrica cije elemente treba sabrati
   * @return zbir elemenata gornje trougaone matrice
   */
  public static double matrixSum(TriangleMatrix mat){
    double result = 0.0;
    for(int i=0; i<mat.getMatrix().length; i++){
      for(int j=0; j<mat.getMatrix()[0].length; j++){
        if(j>=i){
          result += mat.getMatrix()[i][j];
        }
      }
    }
    return result;
  }
  /*
  public static void main(String parametri[]){
    TriangleMatrix t = new TriangleMatrix();
    System.out.println(t);
    System.out.println("Suma elemenata gornje trougaone matrice je: " + TriangleMatrix.matrixSum(t));
    t.printSortedDiagonalValues();
  }*/
}