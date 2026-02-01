package net.etfbl;

import java.io.File;
import java.io.FileFilter;

public class FilterDatoteka implements FileFilter {
 private String upit="";
 public FilterDatoteka(String upit) {
  this.upit=upit;
 }
 
 @Override
 public boolean accept(File pathname) {
  if(pathname.getName().toLowerCase().contains(upit.toLowerCase())) return true;
  return false;
 }

}
