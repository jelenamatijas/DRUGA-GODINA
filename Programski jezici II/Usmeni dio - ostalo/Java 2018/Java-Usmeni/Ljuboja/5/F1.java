import java.io.*;
import java.util.*;
public class F1{
static boolean b;
static int counter=0;
public static void main(String args[]){
  try{
    label:
      while(counter==0){
      if(!b){
      System.out.println("C");
      b=true;
      main(new String[]{"D"});
      System.out.println("E");
      }else{
      counter++;
      System.out.println("A");
      System.out.println(args[0]);
      //main((String[])new Object("F")); //ne moze se kastovati niz objekata u niz stringova-bar ne na ovaj nacin IZUZETAK nema ispisa
      if(counter==1){
        continue label;
       // System.out.println("B");
      }
      }
    System.out.println(F1.getF1());
    }
  
  }catch(Exception e){
  System.out.println("exception");
  }

}
public static F1 getF1(){
System.out.println("1");
return new F1();
}
{
System.out.println("F1");
}
}
