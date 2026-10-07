public class StanjeNaRacunuException extends Exception{
  public StanjeNaRacunuException(){
    super("Na racunu nema dovoljno novca");
  }
  
  public StanjeNaRacunuException(String msg){
    super(msg);
  }
}