import java.io.Serializable;

public class Proizvodjac implements Serializable{
    String naziv, drzava, email;

    Proizvodjac(){
        super();
        
    }

    Proizvodjac(String naziv, String drzava, String email){
        this.naziv = naziv;
        this.drzava = drzava;
        this.email = email;
        
    }

    Proizvodjac(Proizvodjac p){
        this.naziv = p.naziv;
        this.drzava = p.drzava;
        this.email = p.email;
    }

    public String getNaziv(){
        return naziv;
    }

    public String getDrzava(){
        return drzava;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString(){
        return "Naziv: " + naziv + " Email: " + email + " Drzava: " + drzava;
    }
}
