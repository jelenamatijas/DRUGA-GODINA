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
public abstract class Predmet  {
    protected int identifikator=0;
    private static int brojac=1;
    protected double specificnaTezina=0;
    
    public Predmet  (double s) 
    {
        specificnaTezina=s;
        identifikator=brojac++;
    }
    
    abstract public void print();
    abstract public void read()throws Exception;
    abstract public double zapremina();
    
    public double tezina()
    {
        return this.specificnaTezina*this.zapremina();
    }
    
    public String poredjenje(Predmet p)
    {
        if(this.zapremina()==p.zapremina())return "jednaki";
        if(this.zapremina()>p.zapremina()) return "veci"; else
            return "manji";
    }
    
}
