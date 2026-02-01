/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prodavnica;

import java.io.*;
/**
 *
 * @author Milan
 */
public class AnalizaProdaje  {
    
    public AnalizaProdaje() throws Exception{
    File[] files = new File ("./racuni/").listFiles();
    Racun rac=null;
    double suma=0;
    for(File fl:files)
    {
        ObjectInputStream ois= new ObjectInputStream (new FileInputStream(fl));
        rac=(Racun)ois.readObject();
      System.out.println("./racuni/"+fl.getName()+"\n"+rac);  
      suma+=rac.ukupnaCijena;
      
    }
    System.out.println("Ukupan iznos svih racuna je "+suma+"\n---------------");
   // BufferedReader in= new BufferedReader(new FileReader("*.etf"))
    }
}
