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
public class Telefoni extends Proizvod implements Serializable{
    public String konfiguracija="";
    public String model="";
     
    public Telefoni()
    {
        super();
        konfiguracija="nepoznata konfiguracija";
        model="nemoznat model";
    }
    public Telefoni(String sifra,double cijena, String naziv, Proizvodjac pr, String konfiguracija,String model)
    {
        super( sifra,cijena, naziv, pr);
        this.konfiguracija=konfiguracija;
        this.model=model;
    }


    
}
