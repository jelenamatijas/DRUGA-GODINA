/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prodavnica;

import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Milan
 */
public class Racun implements Serializable {
    public Proizvod[] proizvodi;
    public Date datum;
    public double ukupnaCijena=0;
    
    public Racun()
    {
        this.datum=new Date();
        ukupnaCijena=0;
    }
    
    public void dodatiProizvod(Proizvod pr)
    {
        if(pr!=null)
        {
            int brojProizvoda=0;
            if(proizvodi!=null)brojProizvoda=proizvodi.length;
            Proizvod[] pom= new Proizvod[brojProizvoda+1];
            for(int i=0;i<brojProizvoda;i++)
                pom[i]=proizvodi[i];
           pom[brojProizvoda]=pr;
           proizvodi=pom;            
        }
        //else System.out.println("Neuspjesna kupovina-prizvod ne postoji!");
    }
    public void dodatiProizvod(String sifra)
    {
        dodatiProizvod(Cjenovnik.pretragaPoSifri(sifra));
        dodatiProizvod(Cjenovnik.pretragaPoNazivu(sifra));
        
    }
    
    public void pregledProizvoda()
    {
        int i=0;
        for(Proizvod pr:proizvodi)
            System.out.println((++i)+"Prizvod"+pr.toString());
    }
    public void izbrisatiProizvod()
    {
        System.arraycopy(proizvodi,0,proizvodi,0,proizvodi.length-2);
    }
    
    public void zakljucaj() throws IOException, ClassNotFoundException
    {
        pregledProizvoda();
        double pom=0;
        for(Proizvod pr:proizvodi)
            pom+=pr.cijena;
        System.out.println("Ukupna cijena vase kupovine je:"+pom);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy_hh_mm_ss");
        
        try (ObjectOutputStream oas = new ObjectOutputStream (
                new FileOutputStream("./racuni/"+sdf.format(new Date()).toString()+".etf"
                ))) {
            oas.writeObject(this);
        }
        
    }
    
    
    @Override
public String toString()
{
   String pom="";
   for (Proizvod pr:proizvodi)
       pom+=pr.toString();
   return pom;
}
}
