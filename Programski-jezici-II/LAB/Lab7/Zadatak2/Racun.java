import java.io.Serializable;
import java.util.Date;

public class Racun extends Cjenovnik implements Serializable{
    Date datumKupovine;
    double ukupnaCijena;

    Racun(){
        super();
        datumKupovine = new Date();
        ukupnaCijena = 0.0;
    }

    public void dodajProizvod(Proizvod p){
        super.dodajProizvod(p);
        
    }

    public void ukloniProizvod(String sifra)throws NepostojeciProizvodException{
        ukupnaCijena -= getProizvodSaSifrom(sifra).getCijena();
        super.ukloniProizvod(sifra);   
    }

    public void ispisProizvoda(){
        super.ispisProizvoda();
    }

    
    public void zakljuciKupovinu(){
        for (Proizvod p : proizvodi) {
            ukupnaCijena +=  p.getCijena();
        }
    }  
    public String toString(){
        return "Datum: " + datumKupovine + "\nProizvodi:\n" + super.toString() + "\n\nCijena: " + ukupnaCijena;
    }
}
