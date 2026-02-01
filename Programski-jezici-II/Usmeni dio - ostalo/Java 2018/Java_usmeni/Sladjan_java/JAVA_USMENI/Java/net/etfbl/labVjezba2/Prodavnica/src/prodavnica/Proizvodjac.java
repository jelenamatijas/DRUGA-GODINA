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
class Proizvodjac implements Serializable {
    public String naziv="";
    public String drzava="";
    public String email="";
    
    public Proizvodjac(String naziv, String drzava, String email)
    {
        this.naziv=naziv;
        this.drzava=drzava;
        this.email=email;
    }
    public Proizvodjac()
    {
        this("Nepoznat naziv","Nepoznata drzava","Nepoznat email");
    }
    @Override
    public String toString()
    {
        return "Naziv: "+naziv;
    }
}
