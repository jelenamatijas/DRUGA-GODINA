/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konkurentno2;

import static java.lang.Thread.sleep;


/**
 *
 * @author Milan
 */
public class Konkurentno2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Mapa mapa=new Mapa(20);
        
        Igrac ig1= new Igrac("IG1",mapa,false);
        Igrac ig2= new Igrac("IG2",mapa,true);
        
        ig1.start();
        ig2.start();
        
        while (!ig1.kraj && !ig1.kraj)
        {
            for(Polje p:mapa.getMapa())
            {
                System.out.print(p);
            }
            System.out.println();
            try 
            {
                sleep(500);
            } 
            catch (InterruptedException ex)
            {
                System.out.println("Greska u ispisu u main");
            }
        }
    }
    
}
//TODO staviti da se samo na 5 polja stavlja novac
//Popraviti gresku.
