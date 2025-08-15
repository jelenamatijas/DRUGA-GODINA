import java.util.Random;

public class BadCitizen extends Citizen{
    private boolean greenKriptonyte;

    BadCitizen(){
        super();
        setGreenKriptonyte();
        setPositionX();
        setPositionY();
    }

    BadCitizen(String name, Integer x, Integer y, boolean greenKriptonyte){
        super(name, x, y);
        this.greenKriptonyte = greenKriptonyte;
    }

    BadCitizen(boolean greenKriptonyte){
        super();
        this.greenKriptonyte = greenKriptonyte;
    }

    public boolean getGreenKriptonyte(){
        return greenKriptonyte;
    }

    public void setGreenKriptonyte(){
        Random rand = new Random();
        this.greenKriptonyte = rand.nextBoolean();
    }

    @Override
    public String toString(){
        return "Bad Citizen [greenKriptonyte = " +  greenKriptonyte + ", getName() = " + getName() + ", getPositionX() = " + getPositionX()
                + ", getPositionY() = " + getPositionY() + "]";
    }

}
