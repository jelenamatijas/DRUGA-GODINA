public class ValueException extends Exception{
	public ValueException(){
		super("Unesena vrijednost nije u dozvoljenom opsegu od 1 do 90.");
	}
	
	public ValueException(String message){
		super(message);
	}
}