import pracenjenaucnihkonferencija.*;
import pracenjenaucnihkonferencija.NaucneKonferencijePackage.*;
import java.util.ArrayList;
import java.io.*;

public class NaucneKonferencijeServant extends NaucneKonferencijePOA{
  Rad radovi[]=new Rad[50];
  Radovi radoviSvi=new Radovi(radovi);
  static int brojacRadova=0;
  
  public boolean dodavanjeRada(Rad rad){
    radoviSvi.radovi[brojacRadova++]=rad;
    return true;
  }
  public Radovi pregledRadova(){
    int duzina=0;
    for(int i=0; i<radoviSvi.radovi.length; i++){
      if(radoviSvi.radovi[i]!=null)
        duzina++;
    }
    Rad[] sviRadovi=new Rad[duzina];
    for(int i=0; i<radoviSvi.radovi.length; i++)
    {
      if(radoviSvi.radovi[i]!=null)
        sviRadovi[i]=radoviSvi.radovi[i];
    }
    Radovi radoviZaNazad=new Radovi(sviRadovi);
    return radoviZaNazad;
  }
  public boolean uploadRada(RadZaUpload rad){
    try{
      File fajl=new File(rad.naziv);
      FileOutputStream fos=new FileOutputStream(fajl);
      fos.write(rad.sadrzaj);
      fos.close();
    }catch(IOException ex){
      return false;
    }
    return true;
  }
}
