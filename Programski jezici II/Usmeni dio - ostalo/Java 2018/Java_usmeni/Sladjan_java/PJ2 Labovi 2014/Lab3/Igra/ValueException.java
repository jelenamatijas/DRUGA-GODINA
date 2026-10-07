public class ValueException extends Exception{
  
  public ValueException(){
    super("Vrijednost treba da bude u opsegu od 1 do 90");
  }
  
  public ValueException(String msg){
    super(msg);
  }
 
}
