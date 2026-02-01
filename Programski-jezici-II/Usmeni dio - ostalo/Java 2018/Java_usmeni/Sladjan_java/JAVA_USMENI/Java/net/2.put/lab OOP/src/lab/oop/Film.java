/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab.oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Milan
 */
public class Film {
    protected static  List<Film> filmovi=new ArrayList<Film>();
    protected String nazivFilma="";
    private int godinaObjavljivanja=0;
    private String glumci[]=new String[6];
    protected float prosjecnaOcjena=0f;
    
    
    Film(){
        super();
        filmovi.add(this);
        
    }
    
    Film(String nf, int go, String[] g, float ocjena){
        nazivFilma=nf;
        godinaObjavljivanja=go;
        glumci=g;
        prosjecnaOcjena=ocjena;
        filmovi.add(this);
    }
    
    
   @Override
   public String toString(){
       String pom="";
       pom+="Film: "+nazivFilma;
       pom+="\nGodina objavljivanja: "+(godinaObjavljivanja);
       pom+=String.format("\n%.2f",prosjecnaOcjena);
       pom+="\nGlimci: ";
       for (String glumac : glumci) {
           pom+="\n "+glumac;
           if(glumac!=glumci[glumci.length-1])pom+=",";
       }
       return pom;
   }
   
   public boolean daLiUFilmuGlumiGlumac(String g){
       for(String glumac:glumci){
           if (glumac==g)return true;
       }
       return false;        
   }
   
   public boolean daLiUFilmuGlumiGlumacCaseInssitive(String g){
       for(String glumac:glumci){
           if (glumac.equalsIgnoreCase(g))return true;
       }
       return false;
   }
   
   public int kolikoJeStarFilm(){
       Date date = new Date();
       return Calendar.getInstance().get(Calendar.YEAR)-godinaObjavljivanja;
   }
   
   public boolean daLiNazivFilmaSadrziRijec(String rijec){
       return nazivFilma.contains(rijec);
   }
   
}
