import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;

public class IstorijaIscrtavanja {
    public List<List<? extends Oblik>> iscrtavanja = new ArrayList<List<? extends Oblik>>();
    public void dodajUListuIscrtavanja(List<? extends Oblik> oblici){
        iscrtavanja.add(oblici);
        for (Oblik oblik : oblici) {
            oblik.iscrtaj();
        }
    }

    public void ispisIscrtavanja(){
        int krugovi = 0, pravougaonici = 0, trouglovi = 0;
        for(int i = 0; i<iscrtavanja.size(); i++){
            if(iscrtavanja.get(i).get(0) instanceof Krug){
                krugovi += iscrtavanja.get(i).size();
            }
            else if(iscrtavanja.get(i).get(0) instanceof Pravougaonik){
                pravougaonici += iscrtavanja.get(i).size();
            }
            else{
                trouglovi += iscrtavanja.get(i).size();
            }
        }

        System.out.println("Iscrtano je " + krugovi + " krugova, " + pravougaonici + " pravougaonika i "
                         + trouglovi + " trouglova.");
    }

    public static void main(String[] args) {
        IstorijaIscrtavanja ii = new IstorijaIscrtavanja();

        List<Krug> krugovi= new ArrayList<Krug>();
        Krug k1 = new Krug(2.5);
        Krug k2 = new Krug(5.2);
        Krug k3 = new Krug(1);
        krugovi.add(k1);
        krugovi.add(k2);
        krugovi.add(k3);
        ii.dodajUListuIscrtavanja(krugovi);

        List<Pravougaonik> pravougaonici = new ArrayList<Pravougaonik>();
        Pravougaonik p1 = new Pravougaonik(2,3);
        Pravougaonik p2 = new Pravougaonik(5,5);
        pravougaonici.add(p1);
        pravougaonici.add(p2);
        ii.dodajUListuIscrtavanja(pravougaonici);

        List<PravougliTrougao> trouglovi = new ArrayList<PravougliTrougao>();
        PravougliTrougao pt = new PravougliTrougao(2,5);
        trouglovi.add(pt);
        ii.dodajUListuIscrtavanja(trouglovi);

        ii.ispisIscrtavanja();
    }
}
