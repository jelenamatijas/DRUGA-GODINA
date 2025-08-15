public class NepostojeciProizvodException extends Exception{
    NepostojeciProizvodException(){
        super("Takav proizvod ne postoji!");
    }

    NepostojeciProizvodException(String mess){
        super(mess);
    }
}
