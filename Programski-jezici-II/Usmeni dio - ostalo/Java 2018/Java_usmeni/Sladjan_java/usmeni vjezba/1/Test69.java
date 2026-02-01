public class Test69{
  
  public static int brojac;
  
  public static void metoda(){
    try{
      System.out.println("metoda");
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
  
  public static void main(String [] args){
    try{
      while(true){
        System.out.println("pocetak maina");
        if(brojac%2==0){
          System.out.println("paran brojac");
          brojac++;
          if(brojac>3){
            break;
          }
        }
        else{
          try{
            throw new E2("A");
          }
          catch(E1 e){
            System.out.println(e);
          }
          finally{
            brojac++;
            System.out.println("finally 1");
            Test69.metoda();
          }
        }
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
    finally{
      System.out.println("kraj maina");
      System.out.println(brojac);
    }
  }
}
class E1 extends Exception{
  
  public String name;
  
  public E1(String a){
    name = a;
  }
  
  public String toString(){
    return "E1 - "+name;
  }
}
class E2 extends E1{
  
  public String name;
  
  public E2(String a){
    super(a);
  }
  
  public String toString(){
    return "E2 - "+name;
  }
}