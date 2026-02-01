public class H extends Thread {
 static String s = "string"; //bez ovo static javilo bi gresku u linijama koda 6 i 7
                             //jer onda bi iz statickog konteksta pozivali nestaticki clan
 public static void main(String args[]){
  H h=new H();
  h.metoda(s);
  System.out.println(s);
 }
 
 public void metoda(String s){
  start();
  s=s+" test";  //ovo je lokalna pa nikada nece ispisati string test, da je H.s=s+" test" moze...
 }
 
 public void run(){
  for(int i=0; i<10000; i++){
   s=s+" "+i;
   if(i%10000==0)
    System.out.println(i);
   }
  }
 }