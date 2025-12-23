/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prodavnica;

import java.io.Serializable;

/**
 *
 * @author Milan
 */
public class Racunari extends Proizvod implements Serializable {
    public String konfiguracija="";
    
    public Racunari()
    {
        super();
        konfiguracija="Nepoznata konfiguracija";
    }
    
    public Racunari(String sifra,double cijena, String naziv, Proizvodjac pr, String konfiguracija)
    {
        super(sifra,cijena,naziv, pr);
        this.konfiguracija=konfiguracija;
    }

  
}
