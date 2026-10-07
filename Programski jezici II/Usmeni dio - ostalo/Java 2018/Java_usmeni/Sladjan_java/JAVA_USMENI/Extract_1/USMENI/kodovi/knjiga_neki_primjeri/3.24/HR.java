 import java.util.*;
 public class HR {
 public static void main(String[] args) {
 List<Integer> i = new Vector<Integer>();
 i.add(3); i.add(2); i.add(5);
 int ref = 1;
 doStuff(ref);
 //System.out.println(ref);
 System.out.println(i.get(ref));
 }
 static int doStuff(int x) {
 /* ++x;
  System.out.println(ref);*/
  return ++x;
 } }