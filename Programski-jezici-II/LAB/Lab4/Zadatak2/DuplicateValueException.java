public class DuplicateValueException extends Exception{
	public DuplicateValueException(){
		super("Vrijednost vec postoji.");
	}
	
	DuplicateValueException(String message){
		super(message);
	}
}