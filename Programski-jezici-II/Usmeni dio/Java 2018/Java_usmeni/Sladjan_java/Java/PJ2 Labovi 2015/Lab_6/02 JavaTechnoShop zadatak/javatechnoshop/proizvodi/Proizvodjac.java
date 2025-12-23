package javatechnoshop.proizvodi;
import java.io.Serializable;
/**
 *
 * @author Igor
 */
public class Proizvodjac implements Serializable{
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
