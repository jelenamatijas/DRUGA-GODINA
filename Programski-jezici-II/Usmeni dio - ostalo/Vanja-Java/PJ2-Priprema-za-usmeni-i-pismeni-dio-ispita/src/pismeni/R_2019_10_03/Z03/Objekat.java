package pismeni.R_2019_10_03.Z03;

public class Objekat {
    int key;
    String value;

    public Objekat(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return key + " " + value;
    }

    public String getKey() {
        return "" + key;
    }
}