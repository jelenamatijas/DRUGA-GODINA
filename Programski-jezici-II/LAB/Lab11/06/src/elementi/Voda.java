package elementi;

public class Voda extends Element{
    private boolean jeSlana;

    public Voda(int jacina, boolean jeSlana){
        super(jacina);
        this.jeSlana = jeSlana;
    }

     public String vodaSlana(){
        return "Voda je " + (jeSlana? "slana" : "slatka");
    }

    public void setJeSlana(boolean jeSlana){
        this.jeSlana = jeSlana;
    }


}
