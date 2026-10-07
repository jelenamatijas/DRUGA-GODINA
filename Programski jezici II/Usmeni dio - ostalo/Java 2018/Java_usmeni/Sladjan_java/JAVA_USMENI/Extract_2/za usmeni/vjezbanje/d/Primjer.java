

class Primjer1 extends Primjer{
  public Primjer1(){
  }
  
  public Primjer1 metoda(){
    System.out.println("Metoda iz primjer1!");
    return new Primjer1();
  }
}

public class Primjer{
  public Primjer(){
    System.out.println("Primjer1()");
  }
  public Primjer metoda(){
    System.out.println("Metoda iz primjer!");
    return new Primjer();
  }                    
  
  public static void main(String [] args){
    Primjer p=new Primjer1();
  }
}