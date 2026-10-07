package javatextfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Igor
 */
public class AnalizaProdaje {

    /*
     ULAZ
     racunar#pentium#1#1000;
     monitor#lg#2#200;
     racunar#amd#2#1050;
     ...
    
     IZLAZ
     monitor 400
     racunar 3100
     ...
     */
   
    public static void main(String[] args) {
      double monitori = 0;
      double racunari = 0;
      double telefoni = 0;  
      try {       
        
        PrintWriter outRacunari = new PrintWriter(new BufferedWriter(new FileWriter("prodajaRacunari.txt")));
        PrintWriter outMonitori = new PrintWriter(new BufferedWriter(new FileWriter("prodajaMonitori.txt")));
        PrintWriter outTelefoni = new PrintWriter(new BufferedWriter(new FileWriter("prodajaTelefoni.txt")));
        
        BufferedReader in = new BufferedReader(new FileReader("prodaja.txt"));
        String s;
        while ((s = in.readLine()) != null) {
          int kolicina = Integer.parseInt(s.split("#")[2]);
          double cijena = Double.parseDouble(s.split("#")[3]);
          String kategorija = s.split("#")[0];
          
          System.out.println("Proizvod " + s.split("#")[1] + ", kategorija "
                               + kategorija + ", cijena " + cijena + ", kolicina " + kolicina);
          if ("monitor".equals(kategorija)) {
            monitori += kolicina * cijena;
            outMonitori.println(s);
          } else if ("telefon".equals(kategorija)) {
            telefoni += kolicina * cijena;
            outTelefoni.println(s);
          } else if ("racunar".equals(kategorija)) {
            racunari += kolicina * cijena;
            outRacunari.println(s);
          }
        }
        
        outRacunari.close();
        outMonitori.close();
        outTelefoni.close();
        
        //upis u fajl
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prodajaSumarno.txt")));
        out.println("Monitori " + monitori);
        out.println("Racunari " + racunari);
        out.println("Telefoni " + telefoni);            
        out.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

}
