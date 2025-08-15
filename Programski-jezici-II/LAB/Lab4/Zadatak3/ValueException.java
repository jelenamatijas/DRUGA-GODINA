class ValueException extends Exception{
	ValueException(){
		super("Suma za koju treba umanjiti iznos na racunu je prevelika.");
	}
	
	ValueException(String str){
		super(str);
	}
}