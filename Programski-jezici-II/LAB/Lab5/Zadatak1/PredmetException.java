public class PredmetException extends Exception{
    PredmetException(){
        super("Unese vrijednosti su van dozvnoljenih granica od 1 do 100.");
    }

    PredmetException(String mess){
        super(mess);
    }
}