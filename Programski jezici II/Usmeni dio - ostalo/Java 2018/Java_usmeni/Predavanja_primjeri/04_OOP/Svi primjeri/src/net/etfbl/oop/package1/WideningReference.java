package net.etfbl.oop.package1;

public class WideningReference extends Base{

 public void methodW(){
  System.out.println("methodW in WideningReference " + b);
 }
 
 public static void main(String[] args) {
  Base b = new WideningReference();
  // WideningReference b = new WideningReference();
  b.methodB();
//  b.methodW();
 }

}
