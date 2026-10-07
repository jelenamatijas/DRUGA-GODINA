package net.etfbl.oop.package1;

public class InterfaceImplementationClass2 implements InterfaceC {

 public void methodA() {
  System.out.println("methodA");
 }

 public void methodB() {
  System.out.println("methodB");
 }

 public void methodC() {
  System.out.println("methodC");
 }

 public void methodD() {
  System.out.println("methodD");
 }

 public void methodE() {
  System.out.println("methodE");
 }

 public void methodF() {
  System.out.println("methodF");
 }

 /*public int methodX() {
  System.out.println("methodX");
 }*/

 public void methodX() {
  System.out.println("methodX");
 }

 public static void main(String[] args) {
  InterfaceA ia = new InterfaceImplementationClass2();
  ia.methodA();
  ia.methodB();
  ia.methodC();
//  ia.methodD();
//  ia.methodE();
//  ia.methodF();
  ia.methodX();
 }
}
