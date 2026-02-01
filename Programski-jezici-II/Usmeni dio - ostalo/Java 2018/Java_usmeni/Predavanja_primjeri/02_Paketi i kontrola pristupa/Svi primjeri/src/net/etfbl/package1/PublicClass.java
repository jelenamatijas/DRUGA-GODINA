package net.etfbl.package1;

public class PublicClass {

 final int a;
 
 {
  //a = 1;
 }
 
 public PublicClass() {
  a = 5;
 }
 
 public void method(){
  System.out.println("Method from PublicClass");
 }
 
 public static void main(String[] args) {
  PublicClass publicClass = new PublicClass();
  PackageClass packageClass = new PackageClass();
  AbstractClass abstractClass = new X();
  FinalClass finalClass = new FinalClass();
  publicClass.method();
  packageClass.method();
  finalClass.method();
  abstractClass.method();
 }

}

class PackageClass {

 public void method(){
  System.out.println("Method from PackageClass");
 }
 
}

abstract class AbstractClass {

 public void method(){
  System.out.println("Method from AbstractClass");
 }
}

class X extends AbstractClass{
 
}

final class FinalClass{

 public void method(){
  System.out.println("Method from FinalClass");
 }
}


//class FinalClass2 extends FinalClass {
// 
//}