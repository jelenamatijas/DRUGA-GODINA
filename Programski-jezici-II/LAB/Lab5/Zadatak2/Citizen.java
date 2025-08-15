import java.util.Random;

class Citizen {
    private String name;
    private int x, y;
    private static final Random rand = new Random();

    Citizen() {}

    Citizen(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void setPositionX() {
        x = rand.nextInt(90);
    }

    public void setPositionY() {
        y = rand.nextInt(30);
    }

    public Integer getPositionX() {
        return x;
    }

    public Integer getPositionY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
