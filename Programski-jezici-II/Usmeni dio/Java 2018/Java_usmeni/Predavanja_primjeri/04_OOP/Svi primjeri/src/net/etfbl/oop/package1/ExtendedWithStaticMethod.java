package net.etfbl.oop.package1;



public class ExtendedWithStaticMethod extends BaseWithStaticMethod{
 public static void method(){
  System.out.println("ExtendedWithStaticMethod - method");
 }
 
// public void method2(){ 
//  
// }
 
 public static void method3(){
  System.out.println("ExtendedWithStaticMethod - method3");
 }
 
 public static void main(String[] args) {
  BaseWithStaticMethod.method();
 }
}


class BaseWithStaticMethod {
 public static void method(){
  System.out.println("BaseWithStaticMethod - method");
 }
 public static void method2(){
  System.out.println("BaseWithStaticMethod - method2");
 }
 public void method3(){
  System.out.println("BaseWithStaticMethod - method3");
 }
}