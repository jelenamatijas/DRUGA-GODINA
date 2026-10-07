public class Nit implements Runnable{
 String string1="Yes";
 
 public void run(){
  this.string1 = "No"; // ova naredba ce se izvrsiti kad nit dobije procesorsko vrijeme
 }
 
 public static void main(String args[]){
  Nit t=new Nit();
  new Thread(t).start();  // ova nit ne mora odmah dobiti procesorsko vrijeme, moze se nastaviti izvrsavati glavna nit u kojoj je string=yes
  for(int i=0; i<10; i++)
   System.out.println(i+". "+t.string1);
  }
 }