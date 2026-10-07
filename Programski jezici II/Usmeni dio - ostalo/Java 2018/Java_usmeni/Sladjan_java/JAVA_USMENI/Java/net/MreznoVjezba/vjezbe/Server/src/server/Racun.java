/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author Milan
 */
public class Racun {
    public Proizvod[] proizvodi;
    public int cijena;
    
    public void dodajProizvod(Proizvod proizvod){
        Proizvod[] proizvodiSaNovim= new Proizvod[proizvodi.length+1];
        for (int i=0;i<proizvodi.length;i++){
            proizvodiSaNovim[i]=proizvodi[i];
        }
        proizvodiSaNovim[proizvodi.length]=proizvod;
        proizvodi=proizvodiSaNovim;
    }
    
    public void ukloniProizvod(Proizvod prozvod){
        
    }
    
}
