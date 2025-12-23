package net.etfbl.pj2;

import net.etfbl.pj2.interfaces.GasenjePozaraInterface;
import net.etfbl.pj2.interfaces.PenjanjeInterface;

/**
 *
 * @author igor
 */
public class Vatrogasac extends Osoba implements PenjanjeInterface, GasenjePozaraInterface {

    public Vatrogasac(String ime, String prezime, String oprema, int redNaMapi) {
        super(ime, prezime, oprema, redNaMapi);
    }

    @Override
    public Object penjanje() {
        return null;
    }

    @Override
    public String gasenjePozara() {
        return "Pozar ugasen";
    }

}