package pismeni.R_2022_06_29.Z01;

public class VojniAvion extends Avion implements Vojni {
    TipNaoruzanja tip;
    static int c = 0;

    public VojniAvion() {
        super();
        setNaoruzanje(TipNaoruzanja.RAKETA);
        setTransponder(c % 2 == 0 ? true : false); // naizmjenicno
        c++;
    }

    public void setNaoruzanje(TipNaoruzanja tip) {
        this.tip = tip;
    }

    public void setTransponder(boolean t) {
        transponder = t;
    }
}