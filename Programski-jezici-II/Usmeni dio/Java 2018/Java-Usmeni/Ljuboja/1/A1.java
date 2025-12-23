import java.io.*;
import java.util.*;

public class A1
{
  int x = 1;
  private A1 a1;
  public A1()
  {
  System.out.println("A1");
  }
  public A1(A1 a1)
  {
    
  System.out.println("A1(a1)");
  this.a1=a1;
  }
  public static void main(String args[])
  {
  new A4();
  }
// public void metoda(){
 // System.out.println("metoda A1");
  //}
}

class A2 extends A1
{
 static int x = 2;
A1 a1;
public A2(){
 this(new A1());
 System.out.println("A2");
}
public A2(A1 a1){
this.a1=a1;
System.out.println("A2(a1)");
}

public void metoda()
  {
    System.out.println("met12313ic");
  }
}

class A3 extends A2 implements Serializable
{
  int x = 3;
  public A3(){
  System.out.println("A3");
  }
  
  public  void metoda()
  {
    System.out.println("metic");
  }
}

class A4 extends A3
{
private A3 a=new A3();                 //2
private A2 a2=new A2(new A1(null));    //3
Serializable a3=new A3();              //4
public A4(){
  super();                             // 1
  System.out.println("A4");            //5
  a.metoda();
  System.out.println(a.x);//6
}
}
/*
A1 
A1 
A2(a1) 
A2 
A3 
A1 
A1 
A2(a1) 
A2 
A1(a1) 
A1 
A2(a1) 
A1 
A1 
A2(a1) 
A2 
A3 
A4 
metoda A1 
**/
