package Lab07.autotrka;

import java.util.Random;
import java.lang.String;
import Lab07.autotrka.Automobil;



public class AutoTrka
{
  public static void main(String args[])
  {
    int x,y;
    Random ran = new Random();
    Object[][] staza = new Object [3][15];
    
    for(int i=0;i<4;i++)
    {
      x = ran.nextInt(3);
      y = ran.nextInt(15);
      String st = new String();
      st = "STOP";
      staza[x][y] = st;
    }
    
    Automobil a = new Automobil(staza,0,"Audi","1");//obratiti paznju na drugi argument, to je vrsta matrice i pocinje od nulte pozicije!!!
    Automobil b = new Automobil(staza,1,"BMW","2");
    Automobil c = new Automobil(staza,2,"MERCEDES","3");
    
    a.start();
    b.start();
    c.start();
  }
}
      
      