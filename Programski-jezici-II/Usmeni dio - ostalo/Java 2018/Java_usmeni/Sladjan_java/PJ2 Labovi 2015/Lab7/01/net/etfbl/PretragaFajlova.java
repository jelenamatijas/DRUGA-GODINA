package net.etfbl;

import java.io.File;

public class PretragaFajlova {

 public void ispisSvihNaziva(File pocetni) {
  if(pocetni.exists()) {
   if(pocetni.isDirectory()) {
    System.out.println("*" + pocetni.getName() + "*");
    File[] sadrzaj=pocetni.listFiles();
    for(File f:sadrzaj) ispisSvihNaziva(f);
   }
   else {
    System.out.println(pocetni.getName());
   }
  }
 }
 
 public void pretragaSvihNaziva(File pocetni, String upit) {
  if(pocetni.exists()) {
   if(pocetni.isDirectory()) {
    File[] sadrzaj=pocetni.listFiles(new FilterDatoteka(upit));
    for(File f:sadrzaj) pretragaSvihNaziva(f,upit);
   }
   else {
    System.out.println(pocetni.getName());
   }
  }
 }
}
