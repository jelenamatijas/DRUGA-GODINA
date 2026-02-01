import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class BankarskiSistem {

 Random generator=new Random();
 
 public void izmijeniIznos(String naziv, HashMap<Partner, Integer> partneri){
  Set<Partner> skup=partneri.keySet();
  Partner part=new Partner();
  
  int iznos=0;
  for(Iterator<Partner> podaci=skup.iterator(); podaci.hasNext();){
   if((part=podaci.next()).getNazivKompanije().equals(naziv)){
    partneri.put(part, iznos=generator.nextInt(10000));
    System.out.println("Partneru "+part.getNazivKompanije()+" je dodijeljena nova cijena ugovora -> "+iznos);
   }
   
  }
  }
 
 public HashMap<Partner, Integer> ocitajPartnere(){
  HashMap<Partner, Integer> partneri=new HashMap<Partner, Integer>();
 
  //pozivamo metodu za ocitavanje partnera
  ArrayList<Partner> izFajla=Partner.listaPartnera();
  
  //popunjavamo ugovore svim partnerima i slucajno generisanim iznosima
  for(int i=0; i<izFajla.size(); i++){
   System.out.println(izFajla.get(i).getNazivKompanije());
   partneri.put(izFajla.get(i), generator.nextInt(1000));
  }
  
  return partneri;
 }
 
 public static void main(String args[]){
  BankarskiSistem dzbs=new BankarskiSistem();
  System.out.println("Ocitavanje podataka o kompanijama");
  System.out.println("KOMPANIJE");
  System.out.println("*******************************");
  //popunimo mapu partnerima
  HashMap<Partner, Integer> sviPartneri=dzbs.ocitajPartnere();
  System.out.println("*******************************");
  Scanner ulaz=new Scanner(System.in);
  System.out.println("Unesite naziv kompanije!");
  dzbs.izmijeniIznos(ulaz.nextLine(), sviPartneri);
  System.out.println("*******************************");
  System.out.println("Kraj");
  System.out.println("*******************************");
 }
}
