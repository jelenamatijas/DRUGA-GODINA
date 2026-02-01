package net.etfbl.lab4.Film.Film;

import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.String;
import java.util.Calendar;


public class Film
{
  private String nazivFilma;
  private int godinaObjavljivanja;
  private double prosjecnaOcjena;
  private String glumci[];
  private int duzinaNiza;
  
  
  protected String EXCEPTION_PORUKA = "Niz glumaca ne moze biti veci od 6!!!";
  protected String EXCEPTION_PORUKA2 = "Godina objavljivanja ne moze biti veca od trenutne";
  public Film()
  {
  }
  
  public Film(String s,int g,double p,String[] h) throws Exception
  {
    int lenght = 0;
    for (String m : h) lenght++;
    
    this.duzinaNiza = lenght;
    glumci = new String [lenght];
    if (h==null || lenght>6)throw new Exception(EXCEPTION_PORUKA);
    else if(g>Calendar.getInstance().get(Calendar.YEAR)) throw new Exception (EXCEPTION_PORUKA2);
    else 
    {
      nazivFilma = s;
      godinaObjavljivanja = g;
      prosjecnaOcjena = p;
      
      for(int i=0;i<lenght;i++) glumci[i] = h[i];
    }
    System.out.println("Naziv filma je: "+nazivFilma);
  }
  
  @Override
  public String toString()
  {
    String s = new String();
    
    System.out.println("Duzina niza je "+duzinaNiza);
    for (int i=0;i<duzinaNiza;i++)
    {
      s+=glumci[i];
      if (i!=duzinaNiza-1) s+=", ";
    }
    
    return "Film: " + nazivFilma + "\nGodina objavljivanja: " + godinaObjavljivanja + "\nProsjecna ocjena: " + prosjecnaOcjena + "\nGlumci: " + s;
    
  }
  
  public boolean daLiUFilmuGlumiGlumac(String s)
  {
    int temp=1;
    for (int i=0;i<duzinaNiza;i++)
    {
      if(glumci[i].equals(s)) temp = 0;
    }
    if(temp == 0) return true;
    else return false;
  }
  
  public boolean daLiUFilmuGlumiGlumacCaseInsensitive(String s)
  {
    int temp=1;
    for (int i=0;i<duzinaNiza;i++)
    {
      if(glumci[i].equalsIgnoreCase(s)) temp = 0;
    }
    if(temp == 0) return true;
    else return false;
  }
  
  public int kolikoJeStarFilm()
  {
    return Calendar.YEAR - godinaObjavljivanja;
  }
  
  public boolean daLiNazivFilmaSadrziRijec (String s)
  {
    return nazivFilma.contains(s);
  }
  
  public String nazivFilma()
  {
    return nazivFilma;
  }
  
  public int godinaObjavljivanja()
  {
    return godinaObjavljivanja;
  }
  
  public String[] glumci()
  {
    return glumci;
  }
  
  public int duzinaNiza ()
  {
    return duzinaNiza;
  }
  
  public double prosjecnaOcjena()
  {
    return prosjecnaOcjena;
  }
    
}