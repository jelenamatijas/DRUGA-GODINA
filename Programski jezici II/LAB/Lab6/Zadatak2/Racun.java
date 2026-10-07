import java.util.Date;

public class Racun extends Cjenovnik{
    Date datumKupovine;
    double ukupnaCijena;

    Racun(){
        super();
        datumKupovine = new Date();
        ukupnaCijena = 0.0;
    }

    public void dodajProizvod(Proizvod p){
        super.dodajProizvod(p);
        ukupnaCijena +=  p.getCijena();
    }

    public void ukloniProizvod(String sifra)throws NepostojeciProizvodException{
        ukupnaCijena -= getProizvodSaSifrom(sifra).getCijena();
        super.ukloniProizvod(sifra);   
    }

    public void ispisProizvoda(){
        super.ispisProizvoda();
    }

    
    public void zakljuciKupovinu(){
        if(ukupnaCijena != 0){
            System.out.println("Datum: " + datumKupovine);
            ispisProizvoda();
            System.out.println("Ukupna cijena: " + ukupnaCijena);
        }else{
            System.out.println("Kupovina je zavrsena. Nijedan proizvod nije kupljen.");
        }

        }
        

}
