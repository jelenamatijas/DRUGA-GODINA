package net.etfbl.lab4.Film.PretragaFilmova;

import net.etfbl.lab4.Film.Film.*;
import net.etfbl.lab4.Film.AnimiraniFilm.*;

import java.util.Scanner;
import java.lang.String;
import java.lang.Exception;

public class PretragaFilmova
{
  public static void main (String args[])
  {
    Film[] nizFilmova = new Film[2];
    
    String[] prviNizGlumaca = {"Vin Diesel","Ridley Scott"};
    String[] drugiNizGlumaca = {"Deni De Vito","Michael Corleone"};
    String s = new String();
    
    try
    {
      nizFilmova[0]= new Film("Riddick",2004,8.5,prviNizGlumaca);
      nizFilmova[1]= new Film("Kum",1997,9.5,drugiNizGlumaca);
      
      
    }catch (Exception Ex)
    {
    }
    
   for (int i=0;i<2;i++)System.out.println(nizFilmova[i].toString());
   
   String glumac = new String();
   System.out.println("Unesite ime glumca za pretragu: ");
   Scanner scan = new Scanner (System.in);
   glumac = scan.nextLine();
   if (nizFilmova[0].daLiUFilmuGlumiGlumac(glumac))System.out.println("DA");
   else System.out.println("NE");
   System.out.println(glumac);
    
  }
  
}