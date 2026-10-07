import java.util.List;
import java.util.ArrayList;
class IstorijaIscrtavanja{
  public List<List<? extends Oblik>> iscrtavanja = new ArrayList<List<? extends Oblik>>();
  
  public void dodajUListuIscrtavanja(List<? extends Oblik> oblici){
    iscrtavanja.add(oblici);
    for(Oblik o:oblici)
      o.iscrtaj();
  }
  
  public void ispisIscrtavanja(){
    int krugova=0, pravougaonika=0, trouglova=0;
    for(int i=0; i<iscrtavanja.size(); i++){
      if(iscrtavanja.get(i).get(0) instanceof Krug)
        krugova+=iscrtavanja.get(i).size();
      else if(iscrtavanja.get(i).get(0) instanceof Pravougaonik)
        pravougaonika+=iscrtavanja.get(i).size(); 
      else
        trouglova+=iscrtavanja.get(i).size(); 
    }
    System.out.println("Iscrtano je "+krugova+" krugova, "+pravougaonika+" pravougaonika i "+trouglova+" trouglova." );
  }
  
  public static void main(String args[]){
    IstorijaIscrtavanja ii=new IstorijaIscrtavanja();
    //krugovi
    List<Krug> krugovi=new ArrayList<Krug>();
    Krug k1=new Krug();
    k1.poluprecnik=26.5;
    krugovi.add(k1);
    Krug k2=new Krug();
    k2.poluprecnik=56.5;
    krugovi.add(k2);
    ii.dodajUListuIscrtavanja(krugovi);
    ii.ispisIscrtavanja();
    //pravougli trouglovi
    List<PravougliTrougao> trouglovi=new ArrayList<PravougliTrougao>();
    PravougliTrougao p1=new PravougliTrougao();
    p1.a=4;
    p1.b=5;
    trouglovi.add(p1);
    PravougliTrougao p2=new PravougliTrougao();
    p2.a=3;
    p2.b=7;
    trouglovi.add(p2);
    ii.dodajUListuIscrtavanja(trouglovi);
    ii.ispisIscrtavanja();
    //pravougaonici
    List<Pravougaonik> pravougaonici=new ArrayList<Pravougaonik>();
    Pravougaonik pr1=new Pravougaonik();
    pr1.a=4;
    pr1.b=5;
    pravougaonici.add(pr1);
    Pravougaonik pr2=new Pravougaonik();
    pr2.a=3;
    pr2.b=7;
    pravougaonici.add(pr2);
    ii.dodajUListuIscrtavanja(pravougaonici);
    ii.ispisIscrtavanja();
  }
}