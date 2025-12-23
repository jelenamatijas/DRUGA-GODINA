package net.etfbl.generics;

public class GenericHolder<T> {
 private T object;

 public GenericHolder(){
  super();
 }

 public GenericHolder(T t){
  object = t;
 }
 
 public void set(T object) {
  this.object = object;
 }

 public T get() {
  return object;
 }
 
 public String print() {
  return object.toString();
 }

 public static void main(String[] args) {
  GenericHolder<Integer> integerHolder = new GenericHolder<Integer>();
  integerHolder.set(10);
  System.out.println(integerHolder.print());
  integerHolder.set(Integer.valueOf("10"));
  Integer otherInteger = integerHolder.get();
  System.out.println(otherInteger);
 }
}
