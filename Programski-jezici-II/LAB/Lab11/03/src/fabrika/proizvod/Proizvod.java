package src.fabrika.proizvod;

import java.io.Serializable;

public class Proizvod implements Serializable{
    private String naziv;
    private String serijskiBroj;
    private boolean greska;

    public Proizvod(){}

    public Proizvod(String naziv, String serijskiBroj, boolean greska){
        this.naziv = naziv;
        this.serijskiBroj = serijskiBroj;
        this.greska = greska;
    }

    public String getNaziv(){
        return naziv;
    }

    public void setNaziv(String naziv){
        this.naziv = naziv;
    }

    public String getSerijskiBroj(){
        return serijskiBroj;
    }

    public void setSerijskiBroj(String serijskiBroj){
        this.serijskiBroj = serijskiBroj;
    }

    public boolean getGreska(){
        return greska;
    }

    public void setGreska(boolean greska){
        this.greska = greska;
    }

    @Override
    public String toString(){
        return "Proizvod{Naziv: " + naziv + " Serijski broj: " + serijskiBroj + " Greska: " + greska + "}";
    }

}

