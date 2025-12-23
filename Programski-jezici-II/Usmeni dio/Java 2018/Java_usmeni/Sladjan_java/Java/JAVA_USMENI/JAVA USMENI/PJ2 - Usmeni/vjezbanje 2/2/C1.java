import java.io.*;
import java.util.*;

public class C1{
  public static void main(String args[]){
    C1 c1=new C1();
    C2 c2=new C2();
    try{
      System.out.println(c2.metoda(c1));
      System.out.println(c2.metoda(c2));                                         //za c1.metoda(c1); c1.metoda(c2); ispisace metoda 1 metoda 1 finally
    }catch(CE1 e){
      System.out.println("exception 1");
    }finally{
      System.out.println("finally");
    }
  }
  
  Object metoda(C1 c) throws CE1{                                                   //ako su u obje object klase c2 c nece radit,a ako su c1 radice jer kad prosledimo c2 ona sadrzi c1 obratno ne vrijedi
    if(c instanceof C1){
      System.out.println("metoda");
    }else{
      throw new CE2();
    }
    return 1;
  }
}
class C2 extends C1{
  
  Object metoda (C1 c) throws CE1{
    if(errorCheck()&& c instanceof C2){
      throw new CE2("error 2");
    }else if(c instanceof C2){
      throw new CE1();
    }else{
      return new String("abc");
    }
  }
  boolean errorCheck(){
    return true;                                  // u drugom slucaju true
  }
}
class CE1 extends Throwable{
  public CE1(){
    System.out.println("ce1 - 1");
  }
  public CE1(String s){
    super(s);
    System.out.println("ce1 - 2");
  }
}
class CE2 extends RuntimeException {
  
  public CE2(){
    System.out.println("ce2 - 1");
  }
  public CE2(String s){
    this();
    System.out.println("ce2 - 2");
  }
  
}


