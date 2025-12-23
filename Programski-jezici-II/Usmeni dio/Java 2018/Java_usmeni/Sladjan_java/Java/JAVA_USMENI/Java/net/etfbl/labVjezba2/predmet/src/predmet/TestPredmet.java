/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package predmet;

/**
 *
 * @author Milan
 */
public class TestPredmet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Sfera s1=new Sfera(2);
        Sfera s2=new Sfera(1.5);
        
        Kvadar k1=new Kvadar(2);
        Kvadar k2=new Kvadar(1.5);
        
        Predmet[] p={s1,s2,k1,k2};
        for (Predmet pom:p)
        {
            try{
            pom.read();
            }
            catch(Exception e)
            {
                System.out.println("Desila se greska - nisu dobre dimnezije");
            }
            pom.print();
            System.out.println("Tezina predmeta "+pom.identifikator+" iznosi "+pom.tezina());
        }
        
        System.out.println("Svera  "+s1.identifikator+"je "+s1.poredjenje(s2)+" od sfere "+s2.identifikator);
        System.out.println("kvadar  "+k1.identifikator+"je "+k1.poredjenje(k2)+" od sfere "+k2.identifikator);
        
    }
    
}
