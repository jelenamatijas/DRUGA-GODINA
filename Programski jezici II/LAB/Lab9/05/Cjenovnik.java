import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Cjenovnik{
    private HashMap<String, Proizvod> proizvodi = new HashMap<String, Proizvod>();

    Cjenovnik(){
        init();
    }

    public void prikazCjenovnika() {
        System.out.println("Svi proizvodi:");
        Iterator p = proizvodi.values().iterator();
        while(p.hasNext()){
            System.out.println(p.next());
            System.out.println("-----------------------------------------------");
        }
    }

    public ArrayList sviProizvodi(){
        return new ArrayList(proizvodi.values());
    }


    public boolean pretragaProizvodaPoProizvodu(Proizvod p){
        return proizvodi.containsValue(p);
    }

    public Proizvod pretragaProizvodaPoSifri(String sifra){
        return proizvodi.get(sifra);
    }

    public void dodaj(Proizvod p){
        proizvodi.put(p.getSifra(), p);
    }

    public void init(){
        
        Proizvodjac hp = new Proizvodjac("HP", "Njemacka", "hp@email.com");
        Proizvodjac nokia = new Proizvodjac("Nokia", "Japan", "lenovo@japan.yahoo");
        
        Racunar p1 = new Racunar("1111", 1230.15, "HP Victus", hp, "R5, 8GB RAM, 512 SSD");
        Monitor p2 = new Monitor("2222", 546.28, "Lenovo 2800", nokia, "Full HD", "v1.03");
        Telefon p3 = new Telefon("3333",120.28, "Lenovo mobile", nokia, "Toucsh screen", "v1.03" );
        Softver p4 = new Softver("4444",200.15, "HP office", hp, "Word, Excel, PP");
        
        Proizvod p[] = new Proizvod[]{p1,p2,p3,p4};
        for(Proizvod pr : p){
            proizvodi.put(pr.getSifra(), pr);
        }
    }

    public static void main(String[] args) {
        Cjenovnik cjenovnik = new Cjenovnik();
        cjenovnik.prikazCjenovnika();

        cjenovnik.dodaj(new Monitor("5555", 891.26, "Lenovo monitor", new Proizvodjac("Lenovo", "Njemacka", "lenovo@gmail.com"), "Full HD", "V4A"));
        cjenovnik.prikazCjenovnika();

        System.out.println(cjenovnik.pretragaProizvodaPoSifri("1111"));
        System.out.println(cjenovnik.pretragaProizvodaPoSifri("6666"));

        System.out.println(cjenovnik.pretragaProizvodaPoProizvodu(new Monitor("5555", 891.26, "Lenovo monitor", new Proizvodjac("Lenovo", "Njemacka", "lenovo@gmail.com"), "Full HD", "V4A")));
        System.out.println(cjenovnik.pretragaProizvodaPoProizvodu(new Monitor("6666", 900.26, "Lenovo monitor", new Proizvodjac("Lenovo", "Njemacka", "lenovo@gmail.com"), "Full HD", "V4A")));
        
        System.out.println(cjenovnik.sviProizvodi().toString());
    }

}
