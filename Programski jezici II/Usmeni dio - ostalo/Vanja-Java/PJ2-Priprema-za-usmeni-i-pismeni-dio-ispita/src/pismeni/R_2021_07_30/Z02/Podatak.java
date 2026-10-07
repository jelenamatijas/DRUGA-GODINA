package pismeni.R_2021_07_30.Z02;

public abstract class Podatak implements Comparable<Podatak> {
    public abstract void akcija();

    public int compareTo(Podatak p) {
        return Integer.compare(hashCode(), p.hashCode());
    }
}