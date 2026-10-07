package elementi;

public class Stijena extends Element{
    private int visina;

    public Stijena(int jacina, int visina){
        super(jacina);
        this.visina = visina;
    }

    public int getVisina(){
        return visina;
    }

    public void setVisina(int visina){
        this.visina = visina;
    }
    
}
