/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab.oop;

/**
 *
 * @author Milan
 */
public class AnimiraniFilm extends Film {
    private String crtac="";
    
    AnimiraniFilm(String nf, int go, String[] g, float ocjena, String cr){
        super(nf,go,g,ocjena);
        crtac=cr;
    }
    
    @Override
    public String toString(){
        String pom=super.toString();
        return pom+="\n Crtac: "+crtac;
    }
    
    public String crtacUnazad(){
        String pom="";
        for (int i=crtac.length(); i>0;i--)pom+=crtac.charAt(i-1);
        return pom;
    }
    
}
