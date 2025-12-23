import java.util.*;

public class Banka{
  public Klijent [] klijenti = new Klijent[5];
  
  public Banka(){
    for(int i=0; i<5; i++){
      klijenti[i] = new Klijent("Ime"+i, "Prezime"+i, "012345678901"+i, i*10+5);
    }
  }
  
  public static void main(String [] args){
    Banka b = new Banka();
    Random rand = new Random();
    for(int i = 0;i<b.klijenti.length;i++){
      System.out.println(b.klijenti[i]);
      try{
        b.klijenti[i].umanjiStanje(rand.nextDouble()*100);
      }catch(StanjeNaRacunuException ex){
        System.out.println(ex.getMessage());
      }
    }
    
    
  }
}