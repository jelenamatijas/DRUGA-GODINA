package net.etfbl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Samoglasnici {

 public static void main(String[] args) {
  try {
   BufferedReader consoleIn=new BufferedReader(new InputStreamReader(System.in));
   System.out.println("Unesite putanju do datoteke: ");
   String datoteka=consoleIn.readLine();
   File f=new File(datoteka);
   if(f.exists() && f.isFile()) {
    BufferedReader fileIn=new BufferedReader(new FileReader(f));
    int pozicijaEkstenzije=datoteka.lastIndexOf('.');
    String noviNaziv=datoteka.substring(0, pozicijaEkstenzije) + "_samoglasnici." + datoteka.substring(pozicijaEkstenzije+1);
    PrintWriter fileOut=new PrintWriter(new BufferedWriter(new FileWriter(new File(noviNaziv))));
    String linija="";
    while((linija=fileIn.readLine())!=null) {
     for(char c:linija.toCharArray()) {
      if(c=='A' || c=='E' || c=='I' || c=='O' || c=='U' || c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
       fileOut.println(c);
      }
     }
    }
    fileOut.close();
    fileIn.close();
   }
   consoleIn.close();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

}
