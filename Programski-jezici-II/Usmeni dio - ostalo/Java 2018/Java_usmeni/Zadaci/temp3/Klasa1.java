public class Klasa1{
  static Klasa1 k1 = new Klasa1();
  Klasa1 k2;
  
  public Klasa1(){
    System.out.println("Konstruktor");
  }
  
  public static void main(String[] args){
    Klasa1 kk1 = new Klasa1();
    Klasa1 kk2 = new Klasa1();
  }
}