package pismeni.R_2022_06_29.Z01;

public class VojniDron extends Dron implements Vojni {

    TipNaoruzanja tip;
    String kamera;

    public VojniDron() {
        super();
    }

    public void setNaoruzanje(TipNaoruzanja tip) {
        this.tip = tip;
    }

    public void setTransponder(boolean t) {
        transponder = t;
    }
}