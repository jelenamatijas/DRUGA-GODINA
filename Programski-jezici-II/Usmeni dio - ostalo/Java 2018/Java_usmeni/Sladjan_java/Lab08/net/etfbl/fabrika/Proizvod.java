package net.etfbl.fabrika;

import java.util.Scanner;
import java.lang.String;
import java.util.Random;
import java.io.Serializable;

public class Proizvod implements Serializable
{
  private String naziv;
  private String SB;
  private boolean greska = false;
  
  public Proizvod() {};
  
  public Proizvod(String naziv,String serijskiBroj)
  {
    this.naziv = naziv;
    this.SB = serijskiBroj;
    
    Random ran = new Random();
    int p;
    
    p = ran.nextInt(100+1);
    if (p>=0 && p<8) this.greska = true;
  }
  
  @Override
  public String toString()
  {
    String s = new String();
    if (greska) s = "ima GRESKU ";
    else s = "";
    return this.naziv + " serijski broj " + this.SB + " " + s;
  }
}