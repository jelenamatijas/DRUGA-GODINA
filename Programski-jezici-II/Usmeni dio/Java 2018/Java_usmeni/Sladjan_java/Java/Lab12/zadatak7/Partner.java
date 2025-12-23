import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;
import java.io.*;


public class Partner
{
  private String naziv;
  private String vlasnik;
  private String adresa;
  private String brojTelefona;
  private String maticniGrad;
  
  public Partner() {};
  
  public Partner(String n,String v,String a,String bt,String mg)
  {
    this.naziv = new String();
    this.vlasnik = new String();
    this.adresa = new String();
    this.brojTelefona = new String();
    this.maticniGrad = new String();
    
    this.naziv = n;
    this.vlasnik = v;
    this.adresa = a;
    this.brojTelefona = bt;
    this.maticniGrad = mg;
    
  }
  
  public void setNaziv(String s)
  {
    this.naziv = s;
  }
  
  public void setVlasnik(String s)
  {
    this.vlasnik = s;
  }
  
  public void setAdresa(String s)
  {
    this.adresa = s;
  }
  
  public void setBrojTelefona(String s)
  {
    this.brojTelefona = s;
  }
  
  public void setMaticniGrad (String s)
  {
    this.maticniGrad = s;
  }
  
  public String getNaziv()
  {
    return this.naziv;
  }
  
  public String getVlasnik()
  {
    return this.vlasnik;
  }
  
  public String getAdresa()
  {
    return this.adresa;
  }
  
  public String getBrojTelefona()
  {
    return this.brojTelefona;
  }
  
  public String getMaticniGrad ()
  {
    return this.maticniGrad;
  }
  
  public void upisi ()
  {
    File f;
    
    try
    {
      f = new File("registrator.txt");
     
      PrintWriter out;
      if (f.exists() && !f.isDirectory() )
            {
       
                   if (!this.imaLiPartner())
                   {
                    
                      out = new PrintWriter (new FileWriter(f,true),true);
                      out.println(this.naziv+"#"+this.vlasnik+"#"+this.adresa+"#"+this.brojTelefona+"#"+this.maticniGrad);
                      out.close();
                   }
                   else System.out.println("Partner vec postoji!!!");
               }
      else
            {
                //f.createNewFile();
                 out = new PrintWriter (new FileWriter(f,true),true);
                  out.println(this.naziv+"#"+this.vlasnik+"#"+this.adresa+"#"+this.brojTelefona+"#"+this.maticniGrad);
                  out.close();
                }
                     
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
   
    
  }
  
  
  public boolean imaLiPartner()
  {
    File f;
    
    String s = new String();
    
    try
    {
      f = new File ("registrator.txt");
      if (f.exists() && (f.getPath()).endsWith(".txt"))
            {   
        
               
                 BufferedReader in = new BufferedReader(new FileReader(f));
                 while ((s = in.readLine()) != null)
                 {
                  
                   if (this.naziv.equals(s.split("#")[0]) && this.vlasnik.equals(s.split("#")[1]) && this.adresa.equals(s.split("#")[2]) 
                         && this.maticniGrad.equals(s.split("#")[4]))
                   {
                     
                     in.close();
                     return true;
                   }
                 }
                 in.close();
              
            }
          else System.out.println("Putanja do direktorijuma u imaLi funkciji ne valja");
          
          }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
    return false;
  }
    
  public static ArrayList<Partner> listaPartnera ()
  {
    ArrayList <Partner> partneri = new ArrayList<Partner>();
    File f;
    //Partner p = new Partner();  PITATI ZASTO NE RADI AKO JE OVDJE DEFINISANO!!!!
    String s = new String();
      try
    {
      f = new File("registrator.txt");
      BufferedReader in = new BufferedReader (new FileReader(f));
      while ((s = in.readLine()) != null)
      {
        System.out.println(s);
        Partner p = new Partner();
        p.setNaziv(s.split("#")[0]);
        p.setVlasnik(s.split("#")[1]);
        p.setAdresa(s.split("#")[2]);
        p.setBrojTelefona(s.split("#")[3]);
        p.setMaticniGrad(s.split("#")[4]);
        partneri.add(p);
      }
      in.close();
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      return partneri;
  }
  
  
  public String toString()
  {
    return "Naziv firme: " +this.naziv +" | Vlasnik firme: "+this.vlasnik+ " | Adresa firme: "+ this.adresa +" | Broj telefona: " + this.brojTelefona +
      " | Grad u kome se firma nalazi: " + this.maticniGrad;
  }
  
  
  public static void main (String[] args)
  {
    Partner p = new Partner("MMG","Pero","Carice milice 26","0038751889741","Banja Luka");
    p.upisi();
    Partner p1 = new Partner ("Drvoprerada","Simo","Cara Dusana 11","992939393","Banja Luka");
    p1.upisi();
    
    ArrayList <Partner> partneri = new ArrayList<Partner> ();
    partneri = Partner.listaPartnera();
   // Pretraga.grad(partneri);
    for (int i=0;i<partneri.size();i++) System.out.println(partneri.get(i).toString());
  }
  
}

                                                                                    
                                                                                    