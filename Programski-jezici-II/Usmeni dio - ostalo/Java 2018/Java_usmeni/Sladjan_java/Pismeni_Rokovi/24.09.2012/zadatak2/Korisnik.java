import java.io.*;

public class Korisnik implements Serializable
{
   public String br;
   public String pin;
   public double racun;
   
   public Korisnik(){}
   
   
   public Korisnik(String br,String pin,double racun)
   {
     this.br = br;
     this.pin = pin;
     this.racun = racun;
   }
   
  
   public boolean equals(Korisnik k)
   {
     if( this.br.equalsIgnoreCase(k.br) && this.pin.equalsIgnoreCase(k.pin) && this.racun == k.racun) return true;
     else return false;
   }
}