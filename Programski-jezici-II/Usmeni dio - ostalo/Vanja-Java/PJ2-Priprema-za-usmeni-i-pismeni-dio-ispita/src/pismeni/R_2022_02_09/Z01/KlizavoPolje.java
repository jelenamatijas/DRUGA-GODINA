package pismeni.R_2022_02_09.Z01;

public class KlizavoPolje extends Polje implements Klizavo {
    int mogucnost;

    public KlizavoPolje(int mogucnost) {
        super();
        this.mogucnost = mogucnost;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + mogucnost;
    }
}