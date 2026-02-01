package net.etfbl.exception;

public class ExceptionTest1 {

 public static void main(String[] args) {
  ExceptionTest1 test = new ExceptionTest1();
  test.method1();
 }

 
 public void method1(){
  try {
   method2();
  } catch (Exception e) {
   //e.printStackTrace();
    System.out.println("Exception");
  }
 }
 
 public void method2() throws Exception{
  method3();
 }
 
 public void method3() throws Exception{
  throw new Exception();
 }
}