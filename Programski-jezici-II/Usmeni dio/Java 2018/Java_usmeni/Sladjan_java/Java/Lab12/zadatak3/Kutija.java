package Lab12.zadatak3;

import java.util.Scanner;
import java.lang.String;


public class Kutija <T> 
{
  private T atribut;
  
  
  public void set(T novo)
  {
    this.atribut = novo;
  }
  
  public T get()
  {
    return this.atribut;
  }
  
  public static void main(String[] args)
  {
    Kutija<Integer> k = new Kutija<Integer>();
    Kutija<String> s = new Kutija<String>();
    
    s.set("OOO");
    System.out.println(s.get());
    k.set(5);
    System.out.println(k.get());
    
  }
    
  
  
  
}