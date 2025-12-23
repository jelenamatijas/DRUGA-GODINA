/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konkurentno2.pkg2;

import java.util.Random;

/**
 *
 * @author Milan
 */
public class Konkurentno22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Polje[] mapa= new Polje[20];
        for (int i=0;i<20;i++)
            mapa[i]=new Polje();
        Random slucajna=new Random();
        int pom=slucajna.nextInt(20);
        Semafor se1=new Semafor();
        mapa[pom].setSe(se1);
        Semafor se2= new Semafor();
        pom=slucajna.nextInt(20);
        while(mapa[pom].getSe()!=null)
        pom=slucajna.nextInt(20);
        mapa[pom].setSe(se2);
        se1.start();
        se2.start();
        
        for (int i=0;i<5;i++){
            pom=slucajna.nextInt(20);
            while (mapa[pom].getNovac()!=0)
                pom=slucajna.nextInt(20);
            mapa[pom].setNovac(slucajna.nextInt(100));
        }
        
        Igrac ig1=new Igrac(mapa,false);
        Igrac ig2=new Igrac (mapa,true);
        
        for (Polje polje:mapa){
            System.out.print(polje);
        }
        ig1.start();
        ig2.start();
        System.out.println();
//        while(Igrac.kraj!=2)
//            try{
//                System.out.println();
//                Thread.sleep(2000);
//                for (Polje polje:mapa){
//                    System.out.print(polje);
//                };
//                System.out.println();
//        
//            }catch (Exception e){
//                e.printStackTrace();
//                
//            }
//        System.out.println();
//        System.out.println(ig1);
//        System.out.println(ig2);
        
//        for (Polje polje:mapa){
//            System.out.print(polje);
//        };
    }
     
    
}
