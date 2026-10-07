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
public class Monitori extends Proizvod implements Serializable {
    public String konfiguracija="";
    public String model="";
    
    public Monitori()
    {
        super();
        konfiguracija="nemoznata konfiguracija";
        model="nemoznat model";
    }
    public Monitori(String sifra,double cijena, String naziv, Proizvodjac pr, String konfiguracija, String model)
    {
        super(sifra, cijena,naziv, pr);
        this.konfiguracija=konfiguracija;
        this.model=model;
    }
}
