/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab.oop;

import java.util.Scanner;

/**
 *
 * @author Milan
 */
public class LabOOP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Film f1=new Film(new String("Svordfish"),(int)2001,
                new String[] {"HeliBeri","Mett Dejmon","Morgan Fiman"},(float)4.5);
        
        AnimiraniFilm f2= new AnimiraniFilm(new String("Pcelica maja"),(int)2014,
            new String[]{"Pcelica Maja","druge pcelice","prelci"}, 2.54f, new String("Dragana Boric"));
        
        System.out.println("Prvi film je \n"+f1);
        System.out.println("\n\n\nDrugi film je \n"+f2);
        
        System.out.println("Prvi film je star: "+f1.kolikoJeStarFilm());
        System.out.println("Da li u drugom filmu glumi Pcelica maja?"+f2.daLiUFilmuGlumiGlumac("Pcelica maja"));
        System.out.println("Crtac Unazad "+f2.crtacUnazad());
        
        System.out.println("Pra rijec nazica drugog filma je: "+f2.nazivFilma.split(" ")[0]);
        
        Scanner sc=new Scanner(System.in);
        //TODO traziti od korisnika da prvo unese znak pa onda sa nextFloat ucitati ocjenu;Ovako ne radi
        System.out.println("Unesi broj ocjenu pomocu koje vrsis pretragu. Moguce koristiti"
                + "i znakove > i <\n");
        String unos=sc.nextLine();
        String unos1=unos;
        unos1.replace("<", "");
        unos1.replace(">", "");
        float pom;
        pom = Float.parseFloat(unos1);
        if(unos.contains("<"))
            for (Film f:Film.filmovi){
                if(f.prosjecnaOcjena<pom)
                    System.out.println(""+f);
            }
         if(unos.contains(">"))
            for (Film f:Film.filmovi){
                if(f.prosjecnaOcjena>pom)
                    System.out.println(""+f);
            }
    }
    
}
