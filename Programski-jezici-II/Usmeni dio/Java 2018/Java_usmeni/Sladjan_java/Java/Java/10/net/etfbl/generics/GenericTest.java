package net.etfbl.generics;

class GenericBase<T> {
 private T baseVariable;
 
 public T get() {
  return baseVariable;
 }
 
 public void set(T t){
  baseVariable = t;
 }
}

class GenericSub<T> extends GenericBase<T>{
 private T subVariable;
 
 public T get2(){
  return subVariable;
 }
}

class GenericNumberSub<T extends Number> extends GenericBase<T>{
 public void add(GenericBase<? extends Number> number){
//  ...
 }
}
public class GenericTest{
 public static void main(String args[]){
  GenericBase<Integer> base = new GenericBase<Integer>();
  GenericBase<Integer> sub = new GenericSub<Integer>();
  GenericBase<Integer> sub2 = new GenericNumberSub<Integer>();
//  GenericBase<Integer> sub2 = new GenericSub<String>();
  base.set(3);
  sub.set(5);
//  sub2.set(5);
  System.out.println(base.get());
  System.out.println(sub.get());
//  System.out.println(sub2.get());
  GenericNumberSub<Integer> number = new GenericNumberSub<Integer>();
  number.set(5);
  
 }
}

