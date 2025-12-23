/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prodavnica;

/**
 *
 * @author Milan
 */
public class Cjenovnik {
     public static void prikazProizvoda()
     {
         for(Proizvod pr: Proizvod.proizvodi)
             System.out.println("Sifra: "+pr.sifra+" Naziv: "+pr.naziv+" Cijena: "+pr.cijena+"Proizvodjac: "+pr.proizvodjac);
         
     }
     
     public static Proizvod pretragaPoNazivu(String naziv)
     {
         for(Proizvod pr:Proizvod.proizvodi)
             if(naziv.equals(pr.naziv)) return pr;
         return null;
     }
     
     public static Proizvod pretragaPoSifri(String sifra)
     {
         for(Proizvod pr:Proizvod.proizvodi)
             if(sifra.equals(pr.sifra)) return pr;
         return null;
     }
    
}
