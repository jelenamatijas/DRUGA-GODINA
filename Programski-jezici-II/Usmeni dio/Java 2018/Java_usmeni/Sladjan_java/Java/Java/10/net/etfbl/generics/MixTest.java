package net.etfbl.generics;

public class MixTest {

 public static void main(String[] args) {
  GenericHolder holder = new GenericHolder<Integer>();
  holder.set("10");
 
//  Integer i = (Integer) holder.get();
  //GenericHolder<Byte> holder2 = holder;
 // Byte s = holder2.get();
  System.out.println(holder.get());
 }
}