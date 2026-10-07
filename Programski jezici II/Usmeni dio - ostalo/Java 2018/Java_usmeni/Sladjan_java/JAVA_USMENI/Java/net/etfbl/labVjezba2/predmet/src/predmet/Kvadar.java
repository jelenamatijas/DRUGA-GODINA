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
public class Kvadar extends Predmet {
    public double a,b,c;
    
    public Kvadar(double s)
    {
        super (s);
    }
    
    @Override
    public void print()
    {
        System.out.println("Dimenzije kvadra "+this.identifikator+" su "+this.a+"; "+this.b+"; "+this.c+" a njegova specificna tezina je "+this.specificnaTezina);
    }
    
    @Override
    public void read()throws Exception
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Unesite dimenzije kvadra - pritisnite enter poslije svake dimenzije");
        this.a=sc.nextDouble();
        if(a>100||a<1)throw new PredmetException("nisu dobre dimenzije");
        this.b=sc.nextDouble();
        if(b>100||b<1)throw new PredmetException("nisu dobre dimenzije");
        this.c=sc.nextDouble();
        if(c>100||c<1)throw new PredmetException("nisu dobre dimenzije");
    }
    
    @Override
    public double zapremina()
    {
        return a*b*c;
    }
}
