import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PretragaRegistra {

 public static ArrayList<Partner> partneri = Partner.listaPartnera();

 // a.
 public static void pretragaPoGradovima(String grad) {
  HashMap<Integer, String> gradovi = new HashMap<Integer, String>();

  for (int i = 0; i < partneri.size(); i++) {
   if (partneri.get(i).getGrad().contains(grad)) {
    if (!gradovi.containsValue(partneri.get(i).getGrad())) {
     gradovi.put(i, partneri.get(i).getGrad());
    }
   }
  }

  for (int i = 0; i < partneri.size(); i++) {
   if (gradovi.get(i) != null)
    System.out.print(i + "-" + gradovi.get(i) + "  ");
  }

  System.out
    .println("UNESITE REDNI BROJ ISPRED GRADA, KAKO BISTE DOBILI SPISAK PARTNERA IZ TOG GRADA!");
  Scanner ulaz = new Scanner(System.in);
  int tmp = ulaz.nextInt();
  String gradTmp = partneri.get(tmp).getGrad();
  System.out.println("Partnerske firme u gradu - " + gradTmp);

  for (int i = 0; i < partneri.size(); i++) {
   if (partneri.get(i).getGrad().contains(gradTmp)) {
    System.out.println(partneri.get(i).getNazivKompanije() + "  "
      + partneri.get(i).getVlasnikKompanije() + "  "
      + partneri.get(i).getBrojTelefona() + "  "
      + partneri.get(i).getAdresa());
   }
  }

 }

 // b.
 public static void pretragaPoAdresi(String adresa) {
  HashMap<Integer, String> adrese = new HashMap<Integer, String>();
  for (int i = 0; i < partneri.size(); i++) {
   if (partneri.get(i).getAdresa().contains(adresa)) {
    if (!adrese.containsValue(partneri.get(i).getAdresa())) {
     adrese.put(i, partneri.get(i).getAdresa());
    }
   }
  }

  for (int i = 0; i < partneri.size(); i++) {
   if (adrese.get(i) != null)
    System.out.print(i + "-" + adrese.get(i) + "  ");
  }

  System.out
    .println("UNESITE REDNI BROJ ISPRED ADRESE, KAKO BISTE DOBILI SPISAK PARTNERA NA TOJ ADRESI!");
  Scanner ulaz = new Scanner(System.in);
  int tmp = ulaz.nextInt();
  String adresaTmp = partneri.get(tmp).getAdresa();
  System.out.println("Partnerske firme u gradu - " + adresaTmp);

  for (int i = 0; i < partneri.size(); i++) {
   if (partneri.get(i).getAdresa().contains(adresaTmp)) {
    System.out.println(partneri.get(i).getNazivKompanije() + "  "
      + partneri.get(i).getVlasnikKompanije() + "  "
      + partneri.get(i).getBrojTelefona() + "  "
      + partneri.get(i).getGrad());
   }
  }

 }

 // c.
 public static void pretragaPoNazivu(String naziv) {
  HashMap<Integer, String> adrese = new HashMap<Integer, String>();
  for (int i = 0; i < partneri.size(); i++) {
   if (partneri.get(i).getNazivKompanije().contains(naziv)) {
    if (!adrese.containsValue(partneri.get(i).getNazivKompanije())) {
     adrese.put(i, partneri.get(i).getNazivKompanije());
    }
   }
  }

  for (int i = 0; i < partneri.size(); i++) {
   if (adrese.get(i) != null)
    System.out.print(i + "-" + adrese.get(i) + "  ");
  }

  System.out
    .println("UNESITE REDNI BROJ ISPRED NAZIVA, KAKO BISTE VIDJELI SVE PODATKE!");
  Scanner ulaz = new Scanner(System.in);
  int tmp = ulaz.nextInt();
  String adresaTmp = partneri.get(tmp).getNazivKompanije();
  System.out.println("Partnerske firme koje imaju *" + adresaTmp
    + "*  u nazivu... ");

  for (int i = 0; i < partneri.size(); i++) {
   if (partneri.get(i).getNazivKompanije().contains(adresaTmp)) {
    System.out.println(partneri.get(i).getNazivKompanije() + "  "
      + partneri.get(i).getVlasnikKompanije() + "  "
      + partneri.get(i).getBrojTelefona() + "  "
      + partneri.get(i).getAdresa() + "  "
      + partneri.get(i).getGrad());
   }
  }

 }

 // d. sortiranje
 public static void sort() {
  //koristenje interfejsa Comparator
  List<Partner> partneri=Partner.listaPartnera();
  Collections.sort(partneri, new PoredjenjePartnera());
  for (int i = 0; i < partneri.size(); i++)
   System.out.println(partneri.get(i).partnerKaoString());
 }

 // e. top 5

 public static void topPet() {
  //kreiramo kolekciju koju cemo kasnije sortirati
  List<ZaTopPet> zaTop5=new ArrayList<ZaTopPet>();
  // prvo napunimo HashMap svim gradovima i koliko se puta pojavljuju
  HashMap<String, Integer> gradovi = new HashMap<String, Integer>();
  
  // key je naziv grada, a value broj pojavljivanja
  for (int i = 0; i < partneri.size(); i++) {
   if (!gradovi.containsKey(partneri.get(i).getGrad())) {
    gradovi.put(partneri.get(i).getGrad(), 1);
    zaTop5.add(new ZaTopPet(partneri.get(i).getGrad(), 0));
   } else {
    gradovi.put(partneri.get(i).getGrad(), 1
      + gradovi.get(partneri.get(i).getGrad()));
   }

  }
  //azuriramo broj kompanija po gradovima
  for (int i = 0; i < zaTop5.size(); i++) {
   if(gradovi.containsKey(zaTop5.get(i).getGrad())){
    //System.out.println("Grad: "+zaTop5.get(i).getGrad()+"  Broj kompanija: "+gradovi.get(partneri.get(i).getGrad()));
    zaTop5.get(i).setBrojKompanija(gradovi.get(partneri.get(i).getGrad()));
   }
  }
  //sortiramo
  Collections.sort(zaTop5, new PoredjenjeZaTopPet());
  //ispisemo prvih 5
  for (int i = 0; i < zaTop5.size(); i++){ 
   if(i==5) break;
   else
   System.out.println(zaTop5.get(i));
   
  }
 }

 // test Pretraga registara

 public static void main(String args[]) {
  System.out.println("Testiranje pretrage po nazivu grada za tekst \"grad\" ");
  PretragaRegistra.pretragaPoGradovima("grad");
  System.out.println("************************************\n");
  System.out.println("Testiranje pretrage po adresi za tekst \"Slavnih\" ");
  PretragaRegistra.pretragaPoAdresi("Slavnih");
  System.out.println("************************************\n");
  System.out.println("Testiranje pretrage po nazivu kompanije za tekst \"a\" ");
  PretragaRegistra.pretragaPoNazivu("a");
  System.out.println("************************************\n");
  System.out.println("Sortirane firme po nazivu...\n");
  PretragaRegistra.sort();
  System.out.println("************************************");
  System.out.println("Top 5 gradova po broju kompanija\n");
  PretragaRegistra.topPet();
 }
}
