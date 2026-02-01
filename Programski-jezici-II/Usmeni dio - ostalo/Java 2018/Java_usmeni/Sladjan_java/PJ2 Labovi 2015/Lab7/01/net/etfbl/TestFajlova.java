package net.etfbl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestFajlova {

 public static void main(String[] args) {
  try {
   BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
   System.out.println("Unesite putanju: ");  
   String unos=in.readLine();
   System.out.println("Unesite dio naziva datoteke: ");
   String upit=in.readLine();
   PretragaFajlova pf=new PretragaFajlova();
   pf.pretragaSvihNaziva(new File(unos), upit);
   //pf.ispisSvihNaziva(new File(unos));
   in.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
  
 }

}
