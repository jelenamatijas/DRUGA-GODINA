public class CommandNotValidException extends Exception {
    public CommandNotValidException() {
		super("Komanda nije ispravna. Prethodno nije pozvan - WAIT.");
	}
}