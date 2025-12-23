package net.etfbl.lab4.izuzetak;

public class PredmetException extends Exception{
 
  public PredmetException(){
    super("Dimenzije predmeta moraju biti u opsegu od 1 do 100");
  }
  
  public PredmetException(String msg){
    super(msg);
  }
    
}
