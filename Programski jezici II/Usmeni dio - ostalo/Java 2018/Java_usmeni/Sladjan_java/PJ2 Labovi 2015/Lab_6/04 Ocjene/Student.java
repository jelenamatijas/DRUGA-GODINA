import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Student {

 private String brojIndeksa;

 public String getBrojIndeksa() {
  return brojIndeksa;
 }

 public void setBrojIndeksa(String brojIndeksa) {
  this.brojIndeksa = brojIndeksa;
 }

 // ocitavanje podataka o studentu sa komandne linije koriscenjem
 // BufferedStreamReader klase
 public void ocitajPodatkeOStudentu() throws IOException {
  BufferedReader citac = new BufferedReader(new InputStreamReader(
    System.in));
 
  System.out.println("Broj indeksa:");
  do {
   this.brojIndeksa = citac.readLine();
  } while (!brojIndeksa.matches("[0-9]{4}[-][0-9]{2}"));
  
  PrintWriter pisac = new PrintWriter(new File("studenti" + File.separator + this.brojIndeksa + ".txt"));
  pisac.write("Broj indeksa: "
    + this.brojIndeksa);
  pisac.flush();
  pisac.close();
 }


 public void dodajProsjecnuOcjenu(String index, Double ocjena)
   throws FileNotFoundException {
  File studenti = new File("studenti");
  String[] temp = studenti.list();
  for (int i = 0; i < temp.length; i++) {
   if (temp[i].contains(index)) {
    try {
     FileWriter pisac = new FileWriter(new File("studenti" + File.separator 
       + temp[i]), true);
     pisac.append("\nProsjecna ocjena: "+ocjena.toString());
     pisac.close();
    } catch (IOException e) {
     e.printStackTrace();
    }

   }
  }
 }


 public static void main(String[] args) throws IOException {
  System.out.println("POCETAK SIMULACIJE");
  Student student = new Student();
  System.out.println("Unos podataka o novom studentu");
  student.ocitajPodatkeOStudentu();
 

  System.out.println("Trenutno se vrsi upis ocjena svim studentima iz dnevnika!!!");
  Dnevnik dn = new Dnevnik();
  Dnevnik[] list = dn.sadrzajDnevnika();
  for (int i = 0; i < list.length; i++) {
   student.dodajProsjecnuOcjenu(
     list[i].getIndex(),
     ((list[i].getGod1() + list[i].getGod2() + list[i].getGod3() + list[i]
       .getGod4()) / 4));

  }
  System.out.println("KRAJ SIMULACIJE");
 }

}
