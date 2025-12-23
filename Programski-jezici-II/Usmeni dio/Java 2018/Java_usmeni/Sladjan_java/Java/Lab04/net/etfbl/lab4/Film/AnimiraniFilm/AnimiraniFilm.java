package net.etfbl.lab4.Film.AnimiraniFilm;

import net.etfbl.lab4.Film.Film.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.String;

public class AnimiraniFilm extends Film
{
  private String crtac;
  
  public AnimiraniFilm(String s,int g,double p,String[] h,String t) throws Exception
  {
    super(s,g,p,h);
    this.crtac = t;
  }
  
  @Override
  public String toString()
  {
    String s = new String();
    String noviNiz[] = new String [super.duzinaNiza()];
    noviNiz=super.glumci();
   
    for (int i=0;i<super.duzinaNiza();i++)
    {
      s+=noviNiz[i];
      if (i!=super.duzinaNiza()-1) s+=", ";
    }
    
    return "Film: " + super.nazivFilma() + "\nGodina objavljivanja: " + super.godinaObjavljivanja() + "\nProsjecna ocjena: " + super.prosjecnaOcjena() + "\nGlumci: " + super.glumci() + "\nCrtac: " + this.crtac;
    
  }
  
  public String crtacUnazad()
  {
    String reverse = new String();
    char[] array = this.crtac.toCharArray();
    
    reverse = "";
      
     for(int i=array.length;i>=0;i--)
     {
       reverse+=array[i];
      
     }
     
     return reverse;
  }
  
    public boolean daLiNazivFilmaSadrziRijec (String s)
  {
    return super.daLiNazivFilmaSadrziRijec(s);
  }
  
  public String nazivFilma()
  {
    return super.nazivFilma();
  }
  
  public int godinaObjavljivanja()
  {
    return super.godinaObjavljivanja();
  }
  
  public String[] glumci()
  {
    return super.glumci();
  }
  
  public int duzinaNiza ()
  {
    return super.duzinaNiza();
  }
  
  public double prosjecnaOcjena()
  {
    return super.prosjecnaOcjena();
  }
  
}