
package net.etfbl.pj2;

import net.etfbl.pj2.interfaces.PenjanjeInterface;
import net.etfbl.pj2.interfaces.PlivanjeInterface;

/**
 *
 * @author igor
 */
public class Plivac extends Osoba implements PenjanjeInterface, PlivanjeInterface {

    public Plivac(String ime, String prezime, String oprema, int redNaMapi) {
        super(ime, prezime, oprema, redNaMapi);
    }

    
    @Override
    public Object penjanje() {
        return null;
    }

    @Override
    public Object plivanje() {
        return null;
    }

}