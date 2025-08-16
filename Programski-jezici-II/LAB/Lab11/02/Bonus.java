import java.util.Random;

public class Bonus extends Element{
    private int jacina;

    public Bonus(){
        Random rand = new Random();
        jacina = rand.nextInt(2,6);
    }

    public int getJacina(){
        return jacina;
    }

    public void setJacina(int jacina){
        this.jacina = jacina;
    }

}
