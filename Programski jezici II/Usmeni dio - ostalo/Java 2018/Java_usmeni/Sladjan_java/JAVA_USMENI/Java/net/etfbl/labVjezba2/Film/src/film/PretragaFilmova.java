/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package film;
import film.AnimiraniFilm;
import java.util.Scanner;

/**
 *
 * @author Milan
 */
public class PretragaFilmova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Film f1=new Film("Transformers 4",2014,new String[]
        {"Neki tamo glumac",
            "Neki tamo glumac",
            "Neki tamo glumac",
            "Neki tamo glumac",
            "Neki tamo glumac",
            "Neki tamo glumac",
        },(float)3.5);
        AnimiraniFilm f2= new AnimiraniFilm ("Ko to tamo peva",1996,new String[]
        {
            "Ckalja",
            "Fico",
            "Kico",
            "Pero",
            "Peti glumac",
            "Sesti glumac"
        }, (float)4.5,"Kusturica");
       
        Film f3=new Film();
        AnimiraniFilm f4=new AnimiraniFilm();
        
        Film[] filmovi={f1,f2,f3,f4};
        for(Film f:filmovi)
        {
            System.out.println("Da li u filmu "+f.nazivFilma.split(" ")[0]+" glumi glumac Fico"+
            f.daLiUFilmuGlumiGlumac("Fico"));
            
            System.out.println("Da li u filmu "+f.nazivFilma.split(" ")[0]+" glumi glumac fico"+
            f.daLiUFilmuGlumiGlumacCaseInsensitive("Fico"));
            
            System.out.println("Film "+f.nazivFilma+" je star"+f.kolikoJeFilmStar());
            System.out.println("Naziv filma \""+f.nazivFilma+"\" sadrzi rijec 4?"+f.daLiNazivFilmaSadrziRijec("4"));
            
            if(f instanceof AnimiraniFilm)
                System.out.println("Naziv crtaca unazad je "+f.crtacUnazad());
        }
        Scanner sc= new Scanner(System.in);
        String pom="";
        do{
            try
            {
            
            System.out.println("Unesite < ili > pa razmak i ocjenu pomocu koje vrsite pretragu. Unesite 0 za kraj!");
            pom=sc.next();
            if(pom.contains("<"))
                {
                for(Film f:filmovi)
                    if (f.prosjecnaOcjena<Integer.parseInt(pom))
                        System.out.println(f);
                
                }
            else
            {
                for(Film f:filmovi)
                    if (f.prosjecnaOcjena>Integer.parseInt(pom))
                        System.out.println(f);
                
            }
            }catch (Exception e)
            {
                System.out.println("Desila se greska, pokusajte opet!"+e);
            }
            
        }while(!pom.equals("0"));
    }
    
}
