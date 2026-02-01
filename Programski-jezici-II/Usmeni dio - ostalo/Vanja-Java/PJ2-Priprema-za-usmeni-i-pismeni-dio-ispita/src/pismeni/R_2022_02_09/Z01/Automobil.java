package pismeni.R_2022_02_09.Z01;

import java.util.ArrayList;

public class Automobil extends Vozilo {

    ArrayList<Supermoc> supermoci;

    public Automobil(String id, Vozac vozac, Motor motor, String konfig, ArrayList<Supermoc> supermoci) {
        super(id, vozac, motor, konfig);
        this.supermoci = supermoci;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + super.toString() + " " + supermoci;
    }
}