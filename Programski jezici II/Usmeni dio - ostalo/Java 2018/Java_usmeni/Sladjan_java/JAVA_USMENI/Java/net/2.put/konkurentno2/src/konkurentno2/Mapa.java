/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konkurentno2;

import java.util.Random;

/**
 *
 * @author Milan
 */
public class Mapa 
{
   public Polje[] mapa;
   // Object mapa;
 
   public Mapa(int duzina)
   {
       mapa= new Polje[duzina];
        int pom = new Random().nextInt(duzina);
        mapa[pom].sem = new Semafor();
        while (mapa[pom].sem !=null) 
            pom = new Random().nextInt(duzina);
        mapa[pom].sem = new Semafor();
        
    }
  public Polje[] getMapa()
   {
       return mapa;
   }
   
}
