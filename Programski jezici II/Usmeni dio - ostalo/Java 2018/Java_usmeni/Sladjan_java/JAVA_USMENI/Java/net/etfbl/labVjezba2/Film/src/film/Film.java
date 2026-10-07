/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package film;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Milan
 */
public class Film {
   public String nazivFilma="";
   public int godinaObjavljivanja=0;
   public String[] glumci=new String[6];
   public float prosjecnaOcjena=0;
   
   public Film(String nazivFilma, int godinaObjavljivanja, String[] glumci,float prosjecnaOcjena)
   {
    this.nazivFilma=nazivFilma;
    this.glumci=glumci;
    this.godinaObjavljivanja=godinaObjavljivanja;
    this.prosjecnaOcjena=prosjecnaOcjena;
   }
   
   public Film()
   {
       this("noName",0,new String[]{"Nepoznat","Nepoznat","Nepoznat","Nepoznat","Nepoznat","Nepoznat"},0);
            
   }
   
   @Override
   public String toString()
   {
       return "-----------------------------------------------------------"+
               "\nFilm: "+nazivFilma+
               "\n Godina objavljivanja: "+godinaObjavljivanja+
               "\n Prosjecna ocjena: "+prosjecnaOcjena+
               "\n Glumci: "+glumci[1]+", "+glumci[2]+", "+glumci[3]+", "+glumci[3]+", "+glumci[4]+", "+glumci[5];
   }
    
   public boolean daLiUFilmuGlumiGlumac(String gl)
   {
       for(String pom:glumci)
           if(pom.equals(gl))return true;
       return false;
   }
   
   public boolean daLiUFilmuGlumiGlumacCaseInsensitive(String gl)
   {
       for(String pom:glumci)
           if(pom.equalsIgnoreCase(gl))return true;
       return false;
   }
   
  public int kolikoJeFilmStar()
  {
      Date date=new Date();
      return date.getYear()- this.godinaObjavljivanja;//Calendar.get(Calendar.YEAR)
  }
  
  public boolean daLiNazivFilmaSadrziRijec(String rijec)
  {
      return nazivFilma.contains(rijec);
  }
  
  public String crtacUnazad()
  {
      return "";
  }
}
