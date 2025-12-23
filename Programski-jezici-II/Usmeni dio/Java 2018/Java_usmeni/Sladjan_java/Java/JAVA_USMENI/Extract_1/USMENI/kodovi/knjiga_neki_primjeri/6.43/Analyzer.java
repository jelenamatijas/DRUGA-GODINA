 import java.util.*;
 public class Analyzer {
 static List<Exception> me;
 Exception myEx;
 public static void main(String[] args) {
 Analyzer[] aa = {new Analyzer(), new Analyzer()};
 me = new ArrayList<Exception>();
 for(int i = 0; i < 2; i++) {
 try {
 if(i == 0) throw new Exception();
 if(i == 1) throw new MyException();
 }
 catch(Exception e) {
 me.add(e);
 aa[i].myEx = e;
 }
 }
 System.out.println(aa.length + " " + me.size());
 aa = null; me = null;
 // do more stuff
 } }
 class MyException extends Exception { }