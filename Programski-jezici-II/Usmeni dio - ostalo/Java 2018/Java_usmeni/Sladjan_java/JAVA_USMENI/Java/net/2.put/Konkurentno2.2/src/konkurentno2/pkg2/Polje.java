/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konkurentno2.pkg2;

/**
 *
 * @author Milan
 */
public class Polje {
    public Igrac ig;
    public Semafor se;
    public volatile int novac;
    
    
    public Igrac getIg() {
        return ig;
    }

    public void setIg(Igrac ig) {
        this.ig = ig;
    }

    public Semafor getSe() {
        return se;
    }

    public void setSe(Semafor se) {
        this.se = se;
    }

    public int getNovac() {
        return novac;
    }

    public void setNovac(int novac) {
        this.novac = novac;
    }

    @Override
   public String toString(){
       if (se==null)
           return "|"+novac+"|";
       else
           return "|"+se+novac+"|";
   }
    
    
}
