/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package film;

/**
 *
 * @author Milan
 */
public class AnimiraniFilm extends Film{
    public String crtac="";
    
    public AnimiraniFilm(String nazivFilma, int godinaObjavljivanja, String[] glumci,float prosjecnaOcjena,String crtac)
    {
        super (nazivFilma,godinaObjavljivanja,glumci,prosjecnaOcjena);
        this.crtac=crtac;
    }
    
    public AnimiraniFilm()
    {
        super();
        this.crtac="Nedostupan Crtac";
    }

   
    
    @Override
    public String toString()
    {
        return super.toString()+"\n Crtac: "+crtac;
    }
    
    public String crtacUnazad()
    {
        String pom="";
        for (int i=crtac.length();i>0;i--)
            pom+=crtac.charAt(i-1);
        return pom;            
    }
}
