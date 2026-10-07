import java.util.Random;

public abstract class Prepreka extends Element{
    private int jacina;

    public Prepreka(){
        Random rand = new Random();
        jacina = rand.nextInt(2,5);
    }

    public int getJacina(){
        return jacina;
    }

    public void setJacina(int jacina){
        this.jacina = jacina;
    }

}
