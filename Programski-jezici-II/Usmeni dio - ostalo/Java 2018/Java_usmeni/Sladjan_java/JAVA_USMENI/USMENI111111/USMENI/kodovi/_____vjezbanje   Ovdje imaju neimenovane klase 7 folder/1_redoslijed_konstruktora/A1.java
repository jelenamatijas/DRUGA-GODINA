import java.io.*;
import java.util.*;

public class A1{
  
  private A1 a1;
  public A1(){
  System.out.println("A1");
  }
  
  public A1(A1 a1){
  System.out.println("A1(a1)");
  this.a1=a1;
  }
  
  public static void main(String args[]){
  new A4();
  }
  
  void metoda(){
  System.out.println("metoda A1");
  }
}


class A2 extends A1{
A1 a1;
public A2(){
 this(new A1());
 System.out.println("A2");
}

public A2(A1 a1){
this.a1=a1;
System.out.println("A2(a1)");
}
}


class A3 extends A2 implements Serializable{
  public A3(){
  System.out.println("A3");
  }
}

class A4 extends A3{
  
private A1 a=new A2();                 //2
private A2 a2=new A2(new A1(null));    //3
Serializable a3=new A3();              //4
public A4(){
  super();                             // 1
  System.out.println("A4");            //5
  a.metoda();                          //6
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
