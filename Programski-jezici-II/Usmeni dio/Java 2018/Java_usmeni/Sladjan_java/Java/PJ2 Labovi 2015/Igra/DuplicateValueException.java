public class DuplicateValueException extends Exception{
 
  public DuplicateValueException(){
    super("Vrijednost je vec izabrana!");
  }
  
  DuplicateValueException(String msg){
    super(msg);
  }
  
}
