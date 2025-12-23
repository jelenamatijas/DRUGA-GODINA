/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proizvodi;
import java.io.Serializable;
/**
 *
 * @author Milan
 */
public class Softver extends Proizvod implements Serializable{
    private String opis;

    public Softver() {
    }

    public Softver(String opis, String sifra, double cijena, String naziv, Proizvodjac proizvodjac) {
        super(sifra, cijena, naziv, proizvodjac);
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Softver: " + "opis=" + opis + ' '+super.toString();
    }
    
    
}
