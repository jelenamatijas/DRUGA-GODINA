package net.etfbl.pj2;

import net.etfbl.pj2.interfaces.GasenjePozaraInterface;
import net.etfbl.pj2.interfaces.PenjanjeInterface;

/**
 *
 * @author igor
 */
public class Planinar extends Osoba implements PenjanjeInterface, GasenjePozaraInterface {

    public Planinar(String ime, String prezime, String oprema, int redNaMapi) {
        super(ime, prezime, oprema, redNaMapi);
    }

    @Override
    public Object penjanje() {
        return null;
    }

    @Override
    public String gasenjePozara() {
        return "Planinar je ugasio pozar";
    }

}
