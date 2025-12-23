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
class Polje {
    public  int novac;
    public Semafor sem;
    public Igrac ig;
    
    public Polje()
    {
        novac= new Random().nextInt(100);
       // sem=new Semafor();
    }
    
    @Override
    public String toString()
    {
        return "||"+novac+sem+ig+"||";
                
    }
}
