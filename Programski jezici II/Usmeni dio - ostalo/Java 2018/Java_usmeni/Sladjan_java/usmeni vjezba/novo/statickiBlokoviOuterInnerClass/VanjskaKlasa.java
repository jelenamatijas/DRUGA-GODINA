public class VanjskaKlasa
{
  public static String vanjskiString;
  
  static{
    System.out.println("Staticki blok, VANJSKE klase");
  };
  
  
  public class UnutrasnjaKlasa
  {
   // public static String o;
//    static{
//      System.out.println("Staticki blok, UNUTRASNJE klase");
//    }
    
  }
  
  public static void main(String[] args)
  {
    VanjskaKlasa vanjskaKlasa = new VanjskaKlasa();
    VanjskaKlasa.UnutrasnjaKlasa unutrasnjaKlasa = vanjskaKlasa. new UnutrasnjaKlasa();
  }
  
}