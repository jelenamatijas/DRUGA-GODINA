/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author Milan
 */
public abstract class Proizvod {
    public int sifra;
    public String naziv;
    public int cijena;
    public Proizvodjac proizvodjac;
    
    @Override
    public abstract String toString();
    
//    @Override
//    public boolean equals(Object o){
//        if(sifra == o.sifra) return true;
//        else return false;
//    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proizvod other = (Proizvod) obj;
        if (this.sifra != other.sifra) {
            return false;
        }
        return true;
    }
}

 class Racunar extends Proizvod{
     public String konfiguracija;
     
     @Override
     public String toString(){
         return "======================"+
                 "RACUNAR\nNaziv: "+naziv+"\nCijena: "+cijena+"\nProizvodjac: "+proizvodjac.naziv+
                 "\nZemlja porijekla: "+proizvodjac.drzava;
     }
}

class Telefon extends Proizvod{
    public String konfiguracija;
    public String model;
    
    @Override
    public String toString(){
        return "======================"+
                 "TELEFON\nNaziv: "+naziv+"\nCijena: "+cijena+"\nProizvodjac: "+proizvodjac.naziv+
                 "\nZemlja porijekla: "+proizvodjac.drzava+"\nKonfiguracija: "+konfiguracija+
                "\nModel: "+model;
    }
}

class Monitor extends Proizvod{
    public String konfiguracija;
    public String model;
    
    @Override
    public String toString(){
        return "======================"+
                 "MONITOR\nNaziv: "+naziv+"\nCijena: "+cijena+"\nProizvodjac: "+proizvodjac.naziv+
                 "\nZemlja porijekla: "+proizvodjac.drzava+"\nKonfiguracija: "+konfiguracija+
                "\nModel: "+model;
    }
}

class Softver extends Proizvod{
    public String opis;
    
      @Override
    public String toString(){
        return "======================"+
                 "SOFTVER\nNaziv: "+naziv+"\nCijena: "+cijena+"\nProizvodjac: "+proizvodjac.naziv+
                 "\nZemlja porijekla: "+proizvodjac.drzava+"\nOpis: "+opis;
    }
}