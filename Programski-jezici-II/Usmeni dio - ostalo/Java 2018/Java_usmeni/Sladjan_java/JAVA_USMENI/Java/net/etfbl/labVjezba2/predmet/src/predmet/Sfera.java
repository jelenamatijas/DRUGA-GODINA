/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package predmet;

import izuzetak.PredmetException;
import java.util.Scanner;

/**
 *
 * @author Milan
 */
public class Sfera extends Predmet {
   public double poluprecnik=0;
    
   public Sfera(double a)
   {
       //System.out.println("Unesite specificnu tezinu sfere");
       super(a);
   }
   
   @Override
   public void print()
   {
       System.out.println("Ovo je Predmet Sfera "+this.identifikator+". sa poluprecnikom "+this.poluprecnik+" i specificnom tezinom "+this.specificnaTezina);
   }
   
    @Override
   public void read() throws Exception
   {
     Scanner sc= new Scanner(System.in);
     System.out.println("Unesite poluprecnik sfere");
     this.poluprecnik=sc.nextDouble();
     if(poluprecnik>100||poluprecnik<1)throw new PredmetException("nisu dobre dimenzije");
    }
   
   @Override
   public double zapremina()
   {
       return (this.poluprecnik)*(0.75)*(3.14)*(this.poluprecnik)*(this.poluprecnik);
   }
   
}
