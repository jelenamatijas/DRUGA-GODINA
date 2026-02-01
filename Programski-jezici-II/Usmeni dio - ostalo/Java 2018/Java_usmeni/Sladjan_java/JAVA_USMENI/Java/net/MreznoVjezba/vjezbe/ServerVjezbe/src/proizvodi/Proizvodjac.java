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
public class Proizvodjac implements Serializable {
    private String naziv;
    private String drzava;
    private String email;

    public Proizvodjac() {
    }

    public Proizvodjac(String naziv, String drzava, String email) {
        this.naziv = naziv;
        this.drzava = drzava;
        this.email = email;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Proizvodjac: " + "naziv=" + naziv + ", drzava=" + drzava + ' ';
    }
    
    
    
}
