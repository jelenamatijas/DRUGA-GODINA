import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dnevnik {

 private String index;
 private Double god1, god2, god3, god4;

 public String getIndex() {
  return index;
 }

 public void setIndex(String index) {
  this.index = index;
 }

 public Double getGod1() {
  return god1;
 }

 public void setGod1(Double god1) {
  this.god1 = god1;
 }

 public Double getGod2() {
  return god2;
 }

 public void setGod2(Double god2) {
  this.god2 = god2;
 }

 public Double getGod3() {
  return god3;
 }

 public void setGod3(Double god3) {
  this.god3 = god3;
 }

 public Double getGod4() {
  return god4;
 }

 public void setGod4(Double god4) {
  this.god4 = god4;
 }

 public String toString() {
  return index + " " + ((god1 + god2 + god3 + god4) / 4);
 }

 public Dnevnik[] sadrzajDnevnika() throws IOException {
  Dnevnik[] dnevnik;
  BufferedReader citac = new BufferedReader(new FileReader("dnevnik.txt"));
  int brojEl = 0;
  // brojimo koliko linija ima...
  while (citac.readLine() != null) {
   brojEl++;
  }
  citac.close();
  System.out.println("Broj elemenata: " + brojEl);
  // kreiramo onoliko objekata tipa dnevnik koliko linija ima u dokumentu
  // dnevnik.txt
  dnevnik = new Dnevnik[brojEl];
  BufferedReader citac1 = new BufferedReader(
    new FileReader("dnevnik.txt"));

  String tempLine = "";
  Dnevnik jedan;
  int i = 0;
  while ((tempLine = citac1.readLine()) != null) {
   String temp[] = tempLine.split(" ");

   jedan = new Dnevnik();
   jedan.setIndex(temp[0]);
   jedan.setGod1(Double.valueOf(temp[1]));
   jedan.setGod2(Double.valueOf(temp[2]));
   jedan.setGod3(Double.valueOf(temp[3]));
   jedan.setGod4(Double.valueOf(temp[4]));
   dnevnik[i++] = jedan;
  }
  citac1.close();
  return dnevnik;
 }

 public static void main(String args[]) throws IOException {
  Dnevnik dn = new Dnevnik();
  Dnevnik[] list = dn.sadrzajDnevnika();
  for (int i = 0; i < list.length; i++) {
   System.out.println(list[i]);
  }
 }
}
