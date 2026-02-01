package net.etfbl.stanovnik;

import java.lang.String;
import net.etfbl.stanovnik.NeispravanMaticniBrojException.*;
import java.io.*;


abstract public class Stanovnik implements Serializable
{
  private String ime;
  private String prezime;
  private String JMB;
  
  public void setIme(String s)
  {
    ime = s;
  }
  public void setPrezime(String s)
  {
    prezime = s;
  }
  
  
  public void setJMB (String s) throws NeispravanMaticniBrojException
  {
    if (!ispravanMaticniBroj(s)) throw new NeispravanMaticniBrojException("Neispravan maticni broj");
    else JMB = s;
  }
  
  
  
  public String getIme()
  {
    return ime;
  }
  public String getPrezime()
  {
    return prezime;
  }
  public String getJMB()
  {
    return JMB;
  }
  
  public static boolean ispravanMaticniBroj(String s)
  {
    if (s.length() != 13) return false;
    
  
    int jmb[] = new int [13];
    
    for (int i=0;i<13;i++)//pretvaranje u niz int-ova radi racunanja checksum-a
    {
      char c = s.charAt(i);
      int temp = c - '0';
      
      jmb[i] = temp;
    }
    
    //for (int i=0;i<13;i++)System.out.print(jmb[i]);
    
    
    double m = 0;
    
    m = 11 - ((7.0*((double)jmb[0]+(double)jmb[6]) + 6.0*((double)jmb[1]+(double)jmb[7]) + 5.0*((double)jmb[2]+(double)jmb[8]) +
                 4.0*((double)jmb[3] + (double)jmb[9]) + 3.0*((double)jmb[4] + (double)jmb[10]) + 2.0 *((double)jmb[5] + (double)jmb[11]))%11.0);
    
   // System.out.println("M = "+(int)m + " " + m);
    if (m>= 1 && m<=9 && (m == jmb[12])) return true;
    else if((m==10 || m==11) && jmb[12] == 0) return true;
    else return false;
  }
  
  
  public String toStirng()
  {
    return "Ime: " + ime + "\nPrezime: " + prezime + "\nJMB: " + JMB;
  }
}
    
    
  