package pismeni.R_2022_06_29.Z01;

import java.util.Random;

public class VojniHelikopter extends Helikopter implements Vojni {

    TipNaoruzanja tip;

    public VojniHelikopter() {
        super();
        setNaoruzanje(TipNaoruzanja.MITRALJEZ);
        endPos = new Random().nextInt(Simulacija.DIM);
    }

    public void setNaoruzanje(TipNaoruzanja tip) {
        this.tip = tip;
    }

    public void setTransponder(boolean t) {
        transponder = t;
    }
}